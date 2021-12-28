package org.etsi.sol005.packagemanagement;

import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a subscription related to notifications about VNF package management.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a subscription related to notifications about VNF package management.")
public class PkgmSubscription {

    @ApiModelProperty(name = "Identifier", required = true, notes = "Identifier of this subscription resource.")
    private String id;
    @ApiModelProperty(name = "Filter", notes = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter.")
    private PkgmNotificationsFilter filter;
    @ApiModelProperty(name = "Callback Uri", required = true, notes = "The URI of the endpoint to send the notification to.")
    private String callbackUri;
    @ApiModelProperty(name = "Links", required = true, notes = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Links to resources related to this resource.")
    public static class Links {

        @ApiModelProperty(name = "Self", required = true, notes = "URI of this resource.")
        private Link self;

    }
}
