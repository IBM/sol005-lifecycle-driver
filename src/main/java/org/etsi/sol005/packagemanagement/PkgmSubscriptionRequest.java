package org.etsi.sol005.packagemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.SubscriptionAuthentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



import lombok.Data;

/**
 * Represents a subscription request related to VNF package management notifications about VNF package onboarding
 * or changes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a subscription request related to VNF package management notifications about VNF package onboarding or changes.")
public class PkgmSubscriptionRequest {

    @Schema(name = "Filter", description = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter.")
    private PkgmNotificationsFilter filter;
    @Schema(name = "Callback Uri", required = true, description = "The URI of the endpoint to send the notification to.")
    private String callbackUri;
    @Schema(name = "Authentication Parameters", description = "Authentication parameters to configure the use of Authorization when sending notifications corresponding to this subscription, as defined in clause 4.5.3.4. This attribute shall only be present if the subscriber requires authorization of notifications.")
    private SubscriptionAuthentication authentication;
}
