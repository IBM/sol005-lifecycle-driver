package org.etsi.sol005.lifecyclemanagement;

import org.etsi.sol005.common.SubscriptionAuthentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a subscription request related to notifications about NS lifecycle changes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a subscription request related to notifications about NS lifecycle changes.")
public class LccnSubscriptionRequest {

    @ApiModelProperty(name = "Notifications Filter", notes = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter.")
    private LifecycleChangeNotificationsFilter filter;
    @ApiModelProperty(name = "Callback URI", required = true, dataType = "URI", notes = "The URI of the endpoint to send the notification to.")
    private String callbackUri;
    @ApiModelProperty(name = "Authentication Parameters", notes = "Authentication parameters to configure the use of Authorization when sending notifications corresponding to this subscription. This attribute shall only be present if the subscriber requires authorization of notifications")
    private SubscriptionAuthentication authentication;

}
