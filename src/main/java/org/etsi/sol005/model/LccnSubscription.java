package org.etsi.sol005.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


/**
 * This type represents a subscription related to notifications about NS lifecycle changes.  It shall comply with the provisions defined in Table 6.5.2.4-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class LccnSubscription   {
  private String id;

  private LifecycleChangeNotificationsFilter filter;

  private URI callbackUri;

  private LcmOpOccNotificationVerbosityType verbosity;

  private LccnSubscriptionLinks links;
}

