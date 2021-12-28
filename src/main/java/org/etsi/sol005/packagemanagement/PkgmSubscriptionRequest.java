package org.etsi.sol005.packagemanagement;

import org.etsi.sol005.common.SubscriptionAuthentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a subscription request related to VNF package management notifications about VNF package onboarding
 * or changes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a subscription request related to VNF package management notifications about VNF package onboarding or changes.")
public class PkgmSubscriptionRequest {

    @ApiModelProperty(name = "Filter", notes = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter.")
    private PkgmNotificationsFilter filter;
    @ApiModelProperty(name = "Callback Uri", required = true, notes = "The URI of the endpoint to send the notification to.")
    private String callbackUri;
    @ApiModelProperty(name = "Authentication Parameters", notes = "Authentication parameters to configure the use of Authorization when sending notifications corresponding to this subscription, as defined in clause 4.5.3.4. This attribute shall only be present if the subscriber requires authorization of notifications.")
    private SubscriptionAuthentication authentication;
}
