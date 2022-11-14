package org.etsi.sol005.lifecyclemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.SubscriptionAuthentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Represents a subscription request related to notifications about NS lifecycle changes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a subscription request related to notifications about NS lifecycle changes.")
public class LccnSubscriptionRequest {

    @Schema(name = "Notifications Filter", description = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter.")
    private LifecycleChangeNotificationsFilter filter;
    @Schema(name = "Callback URI", required = true, type = "URI", description = "The URI of the endpoint to send the notification to.")
    private String callbackUri;
    @Schema(name = "Authentication Parameters", description = "Authentication parameters to configure the use of Authorization when sending notifications corresponding to this subscription. This attribute shall only be present if the subscriber requires authorization of notifications")
    private SubscriptionAuthentication authentication;

}
