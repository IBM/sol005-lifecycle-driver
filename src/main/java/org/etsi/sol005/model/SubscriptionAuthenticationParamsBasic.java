package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Parameters for authentication/authorization using BASIC. Shall be present if authType is \&quot;BASIC\&quot; and the contained information has not been provisioned out of band. Shall be absent otherwise. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SubscriptionAuthenticationParamsBasic   {

  private String userName;

  private String password;
}

