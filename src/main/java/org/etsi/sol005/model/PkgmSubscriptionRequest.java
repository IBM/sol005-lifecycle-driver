package org.etsi.sol005.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents a subscription request related to VNF package management  notifications about VNF package on boarding or changes. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class PkgmSubscriptionRequest   {

  private PkgmNotificationsFilter filter;

  private URI callbackUri;

  private SubscriptionAuthentication authentication;
}

