package org.etsi.sol005.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Parameters for authentication/authorization using OAUTH2_CLIENT_CREDENTIALS. Shall be present if authType is \&quot;OAUTH2_CLIENT_CREDENTIALS\&quot; and the contained information has not been provisioned out of band. Shall be absent otherwise. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SubscriptionAuthenticationParamsOauth2ClientCredentials   {

  private String clientId;

  private String clientPassword;

  private URI tokenEndpoint;
}

