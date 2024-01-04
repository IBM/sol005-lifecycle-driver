package com.ibm.nfvodriver.service;

import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_ACCESS_TOKEN_URI;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_CLIENT_ID;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_CLIENT_SECRET;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_GRANT_TYPE;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_PASSWORD;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_PASSWORD_TOKEN_NAME;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_SCOPE;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_TYPE;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_URL;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_USERNAME;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_USERNAME_TOKEN_NAME;
import static com.ibm.nfvodriver.config.NFVODriverConstants.NFVO_SERVER_URL;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.ibm.nfvodriver.model.alm.ResourceManagerDeploymentLocation;
import com.ibm.nfvodriver.security.CookieAuthenticatedRestTemplate;
import com.ibm.nfvodriver.security.CookieCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.ibm.nfvodriver.config.NFVODriverProperties;
import com.ibm.nfvodriver.config.OAuthClientCredentialsRestTemplateInterceptor;
import com.ibm.nfvodriver.driver.NFVOResponseErrorHandler;
import com.ibm.nfvodriver.model.AuthenticationType;
import com.ibm.nfvodriver.utils.DynamicSslCertificateHttpRequestFactory;

import lombok.RequiredArgsConstructor;

// import com.ibm.nfvodriver.config.RestTemplateConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
// import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
@Service("AuthenticatedRestTemplateService")
public class AuthenticatedRestTemplateService {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticatedRestTemplateService.class);
    public static final String OAUTH_WEBCLIENT = "OAUTH_WEBCLIENT";
    private final RestTemplateBuilder restTemplateBuilder;
    private final Map<ResourceManagerDeploymentLocation, RestTemplate> cachedRestTemplatesByDLs = new ConcurrentHashMap<>();
    private final Map<String, RestTemplate> cachedRestTemplatesByServerUrl = new ConcurrentHashMap<>();
    
    @Autowired
    public AuthenticatedRestTemplateService(RestTemplateBuilder restTemplateBuilder, NFVOResponseErrorHandler nfvoResponseErrorHandler, NFVODriverProperties nfvoDriverProperties) {
        logger.info("Initialising RestTemplate configuration");
        this.restTemplateBuilder = restTemplateBuilder.errorHandler(nfvoResponseErrorHandler)
                .requestFactory(DynamicSslCertificateHttpRequestFactory.class)
                .setConnectTimeout(nfvoDriverProperties.getRestConnectTimeout())
                .setReadTimeout(nfvoDriverProperties.getRestReadTimeout());
    }

    public RestTemplate getRestTemplate(ResourceManagerDeploymentLocation deploymentLocation) {
        if (cachedRestTemplatesByDLs.containsKey(deploymentLocation)) {
            return cachedRestTemplatesByDLs.get(deploymentLocation);
        }

        // Double-check we haven't got a cached entry of the same "name", but different properties. If so, remove it.
        cachedRestTemplatesByDLs.keySet()
                                .stream()
                                .filter(dl -> Objects.equals(dl.getName(), deploymentLocation.getName()))
                                .findFirst()
                                .ifPresent(cachedRestTemplatesByDLs::remove);

        // Check there's a URL defined
        Map<String,String> authenticationProperties = deploymentLocation.getProperties().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String)e.getValue()));
        checkProperty(authenticationProperties, NFVO_SERVER_URL);

        final RestTemplate restTemplate = getRestTemplate(authenticationProperties);
        cachedRestTemplatesByDLs.put(deploymentLocation, restTemplate);
        return restTemplate;
    }

    public RestTemplate getRestTemplate(String serverUrl, Map<String, String> authenticationProperties) {
        if (cachedRestTemplatesByServerUrl.containsKey(serverUrl)) {
            return cachedRestTemplatesByServerUrl.get(serverUrl);
        }

        final RestTemplate restTemplate = getRestTemplate(authenticationProperties);
        cachedRestTemplatesByServerUrl.put(serverUrl, restTemplate);
        return restTemplate;
    }
     
    private RestTemplate getRestTemplate(Map<String, String> authenticationProperties) {
        final String authenticationTypeString = authenticationProperties.getOrDefault(AUTHENTICATION_TYPE, AuthenticationType.NONE.toString());
        final AuthenticationType authenticationType = AuthenticationType.valueOfIgnoreCase(authenticationTypeString);
        if (authenticationType == null) {
            throw new IllegalArgumentException(String.format("Invalid authentication type specified [%s]", authenticationTypeString));
        }
      
        final RestTemplate restTemplate;
        switch (authenticationType) {
        case BASIC:
            checkProperty(authenticationProperties, AUTHENTICATION_USERNAME);
            checkProperty(authenticationProperties, AUTHENTICATION_PASSWORD);
            restTemplate = getBasicAuthenticatedRestTemplate(authenticationProperties);
            break;
        case OAUTH2:
            checkProperty(authenticationProperties, NFVO_SERVER_URL);
            checkProperty(authenticationProperties, AUTHENTICATION_ACCESS_TOKEN_URI);
            checkProperty(authenticationProperties, AUTHENTICATION_CLIENT_ID);
            checkProperty(authenticationProperties, AUTHENTICATION_CLIENT_SECRET);
            restTemplate = getOAuth2AuthenticatedRestTemplate(authenticationProperties);
            break;
            
        case COOKIE:
            checkProperty(authenticationProperties, AUTHENTICATION_URL);
            checkProperty(authenticationProperties, AUTHENTICATION_USERNAME);
            checkProperty(authenticationProperties, AUTHENTICATION_PASSWORD);
            restTemplate = getCookieAuthenticatedRestTemplate(authenticationProperties);
            break;
        default:
            restTemplate = getUnauthenticatedRestTemplate();
        }

        return restTemplate;
    } 
    private void checkProperty(Map<String, String> authenticationProperties, String propertyName) {
        if (StringUtils.isEmpty(authenticationProperties.get(propertyName))) {
            throw new IllegalArgumentException(String.format("Authentication properties must specify a value for [%s]", propertyName));
        }
    }

    private RestTemplate getUnauthenticatedRestTemplate() {
        logger.info("Configuring unauthenticated RestTemplate.");
        return restTemplateBuilder.build();
    }

    private RestTemplate getBasicAuthenticatedRestTemplate(final Map<String, String> authenticationProperties) {
        logger.info("Configuring Basic Authentication RestTemplate.");
        return restTemplateBuilder.basicAuthentication(authenticationProperties.get(AUTHENTICATION_USERNAME),
                                                       authenticationProperties.get(AUTHENTICATION_PASSWORD))
                .build();
    }

    private static AuthorizationGrantType mapStringToGrantType(String grantTypeString, AuthorizationGrantType defaultGrantType) {
        switch (grantTypeString) {
            case "client_credentials":
                return AuthorizationGrantType.CLIENT_CREDENTIALS;
            case "authorization_code":
                return AuthorizationGrantType.AUTHORIZATION_CODE;
            case "password":
                return AuthorizationGrantType.PASSWORD;
            case "refresh_token":
                return AuthorizationGrantType.REFRESH_TOKEN;
            default:
                return defaultGrantType;
        }
    }
    private static AuthorizationGrantType getOrDefaultForGrantType(final Map<String, String> properties, String grantTypeKey, AuthorizationGrantType defaultGrantType) {

        String grantTypeStr = properties.get(grantTypeKey);
        if (grantTypeStr == null) {
            return defaultGrantType;
        }

        return mapStringToGrantType(grantTypeStr, defaultGrantType);
    }
    
    private RestTemplate getOAuth2AuthenticatedRestTemplate(final Map<String, String> authenticationProperties) {
        ClientRegistration.Builder clientRegistrationBuilder = ClientRegistration.withRegistrationId(authenticationProperties.get(AUTHENTICATION_CLIENT_ID))
                .clientId(authenticationProperties.get(AUTHENTICATION_CLIENT_ID))
                .clientSecret(authenticationProperties.get(AUTHENTICATION_CLIENT_SECRET))
                .authorizationGrantType(getOrDefaultForGrantType(authenticationProperties, AUTHENTICATION_GRANT_TYPE, AuthorizationGrantType.CLIENT_CREDENTIALS ))
                .tokenUri(authenticationProperties.get(AUTHENTICATION_ACCESS_TOKEN_URI));

        if (StringUtils.hasText(authenticationProperties.get(AUTHENTICATION_SCOPE))) {
                    clientRegistrationBuilder.scope(Arrays.asList(authenticationProperties.get(AUTHENTICATION_SCOPE).split(",")));
                }  
        ClientRegistration clientRegistration = clientRegistrationBuilder.build();
        return restTemplateBuilder
                .additionalInterceptors(new OAuthClientCredentialsRestTemplateInterceptor(authorizedClientManager(clientRegistration), clientRegistration))
                .build();

        }
        
    private OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistration clientRegistration) {
        var authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();
                
        ClientRegistrationRepository clientRegistrationRepository = clientRegistrationRepository(clientRegistration);
        OAuth2AuthorizedClientService oAuth2AuthorizedClientService = authorizedClientService(clientRegistrationRepository);
        var authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
        }
      
    private OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {

        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
        }

        public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration clientRegistration) {
            return new InMemoryClientRegistrationRepository(clientRegistration);
        }
    

    private RestTemplate getCookieAuthenticatedRestTemplate(final Map<String, String> authenticationProperties) {
        CookieCredentials cookieCredentials = new CookieCredentials();
        cookieCredentials.setAuthenticationUrl(authenticationProperties.get(AUTHENTICATION_URL));
        cookieCredentials.setUsernameTokenName(authenticationProperties.getOrDefault(AUTHENTICATION_USERNAME_TOKEN_NAME, "IDToken1"));
        cookieCredentials.setPasswordTokenName(authenticationProperties.getOrDefault(AUTHENTICATION_PASSWORD_TOKEN_NAME, "IDToken2"));
        cookieCredentials.setUsername(authenticationProperties.get(AUTHENTICATION_USERNAME));
        cookieCredentials.setPassword(authenticationProperties.get(AUTHENTICATION_PASSWORD));

        logger.info("Configuring Cookie authenticated RestTemplate.");
        return restTemplateBuilder.configure(new CookieAuthenticatedRestTemplate(cookieCredentials));
    }

}
