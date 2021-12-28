package org.etsi.sol005.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Parameters for authentication/authorization using OAuth 2.0. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class UploadVnfPkgFromUriRequestParamsOauth2ClientCredentials   {

  private String clientId;

  private String clientPassword;

  private URI tokenEndpoint;
}

