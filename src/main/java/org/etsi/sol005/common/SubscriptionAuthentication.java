package org.etsi.sol005.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents authorization requirements.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents authorization requirements.")
public class SubscriptionAuthentication {

    @Schema(name = "Authentication Type", required = true, description = "Defines the type of Authentication / Authorization to use when sending a notification.")
    private AuthType authType;
    @Schema(name = "Basic Authentication Parameters", description = "Parameters for authentication/authorization using BASIC. Shall be present if authType is \"BASIC\" and the contained information has not been provisioned out of band. Shall be absent otherwise.")
    private BasicParameters paramsBasic;
    @Schema(name = "OAuth2 Client Credential Parameters", description = "Parameters for authentication/authorization using OAUTH2_CLIENT_CREDENTIALS. Shall be present if authType is \"OAUTH2_CLIENT_CREDENTIALS\" and the contained information has not been provisioned out of band. Shall be absent otherwise.")
    private OAuth2ClientCredentialParameters paramsOauth2ClientCredentials;

    public enum AuthType {
        /**
         * The API producer shall pass its client credentials in every POST
         * request that sends a notification.
         */
        BASIC,
        /**
         * The API producer shall include that access token as a Bearer token
         * in every POST request that sends a notification.
         */
        OAUTH2_CLIENT_CREDENTIALS,
        /**
         * The API producer (client) shall use its TLS certificate to create a
         * mutually authenticated TLS session with the API consumer (server)
         * and further the API consumer will do the authorization based on the
         * API producer's certificate.
         */
        TLS_CERT
    }

    /**
     * Parameters for authentication/authorization using BASIC.
     */
    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Parameters for authentication/authorization using BASIC.")
    public static class BasicParameters {

        @Schema(name = "Username", description = "Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band.")
        private String username;
        @Schema(name = "Password", description = "Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band.")
        private String password;

    }

    /**
     * Parameters for authentication/authorization using OAUTH2_CLIENT_CREDENTIALS.
     */
    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Parameters for authentication/authorization using OAUTH2_CLIENT_CREDENTIALS.")
    public static class OAuth2ClientCredentialParameters {

        @Schema(name = "Client Id", description = "Client identifier to be used in the access token request of the OAuth 2.0 client credentials grant type. Shall be present if it has not been provisioned out of band.")
        private String clientId;
        @Schema(name = "Client Password", description = "Client password to be used in the access token request of the OAuth 2.0 client credentials grant type. Shall be present if it has not been provisioned out of band.")
        private String clientPassword;
        @Schema(name = "Token Endpoint", type = "URI", description = "The token endpoint from which the access token can be obtained. Shall be present if it has not been provisioned out of band.")
        private String tokenEndpoint;

    }

}
