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
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
// import org.springframework.security.oauth2.client.OAuth2RestTemplate;
// import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.ibm.nfvodriver.config.NFVODriverProperties;
import com.ibm.nfvodriver.driver.NFVOResponseErrorHandler;
import com.ibm.nfvodriver.model.AuthenticationType;
import com.ibm.nfvodriver.utils.DynamicSslCertificateHttpRequestFactory;
// import com.ibm.nfvodriver.config.RestTemplateConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;


@Service("AuthenticatedRestTemplateService")
public class AuthenticatedRestTemplateService {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticatedRestTemplateService.class);

    private final RestTemplateBuilder restTemplateBuilder;
    private final Map<ResourceManagerDeploymentLocation, RestTemplate> cachedRestTemplatesByDLs = new ConcurrentHashMap<>();
    private final Map<String, RestTemplate> cachedRestTemplatesByServerUrl = new ConcurrentHashMap<>();
    @Autowired
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    @Autowired
    public AuthenticatedRestTemplateService(RestTemplateBuilder restTemplateBuilder, NFVOResponseErrorHandler nfvoResponseErrorHandler, NFVODriverProperties nfvoDriverProperties, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        logger.info("Initialising RestTemplate configuration");
        this.restTemplateBuilder = restTemplateBuilder.errorHandler(nfvoResponseErrorHandler)
                .requestFactory(DynamicSslCertificateHttpRequestFactory.class)
                .setConnectTimeout(nfvoDriverProperties.getRestConnectTimeout())
                .setReadTimeout(nfvoDriverProperties.getRestReadTimeout());
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
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
     
    public WebClient getOAUth2WebClientTemplate(ResourceManagerDeploymentLocation deploymentLocation ){
        // if (cachedRestTemplatesByDLs.containsKey(deploymentLocation)) {
        //     return cachedRestTemplatesByDLs.get(deploymentLocation);
        // }

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
        checkProperty(authenticationProperties, AUTHENTICATION_ACCESS_TOKEN_URI);
        checkProperty(authenticationProperties, AUTHENTICATION_CLIENT_ID);
        checkProperty(authenticationProperties, AUTHENTICATION_CLIENT_SECRET);
        WebClient webClient = getOAuth2WebClient(authenticationProperties);
        return webClient;
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

    private WebClient getOAuth2WebClient(final Map<String, String> authenticationProperties) {
        // final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        // resourceDetails.setAccessTokenUri(authenticationProperties.get(AUTHENTICATION_ACCESS_TOKEN_URI));
        // resourceDetails.setClientId(authenticationProperties.get(AUTHENTICATION_CLIENT_ID));
        // resourceDetails.setClientSecret(authenticationProperties.get(AUTHENTICATION_CLIENT_SECRET));
        // resourceDetails.setGrantType(authenticationProperties.getOrDefault(AUTHENTICATION_GRANT_TYPE, "client_credentials"));
        // if (StringUtils.hasText(authenticationProperties.get(AUTHENTICATION_SCOPE))) {
        //     resourceDetails.setScope(Arrays.asList(authenticationProperties.get(AUTHENTICATION_SCOPE).split(",")));
        // }

        // logger.info("Configuring OAuth2 authenticated RestTemplate.");
        // return restTemplateBuilder.configure(new OAuth2RestTemplate(resourceDetails));
        
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId(authenticationProperties.get(AUTHENTICATION_CLIENT_ID))
                .clientId(authenticationProperties.get(AUTHENTICATION_CLIENT_ID))
                .clientSecret(authenticationProperties.get(AUTHENTICATION_CLIENT_SECRET))
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .tokenUri(authenticationProperties.get(AUTHENTICATION_ACCESS_TOKEN_URI))
                .build();
        // return oAuth2AuthorizedClientService.build(clientRegistration);
        ClientRegistrationRepository clientRegistrationRepository = clientRegistrationRepository(clientRegistration);
        OAuth2AuthorizedClientManager authorizedClientManager = authorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientService);
        WebClient webClient = webClient(authorizedClientManager);
        return webClient;
    }

    @Bean
    public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2Client.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .apply(oauth2Client.oauth2Configuration())
                .build();
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,OAuth2AuthorizedClientService authorizedClientService) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
    @Bean
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
