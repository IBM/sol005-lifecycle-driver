package org.etsi.sol005.packagemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a subscription related to notifications about VNF package management.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a subscription related to notifications about VNF package management.")
public class PkgmSubscription {

    @Schema(name = "Identifier", required = true, description = "Identifier of this subscription resource.")
    private String id;
    @Schema(name = "Filter", description = "Filter settings for this subscription, to define the subset of all notifications this subscription relates to. A particular notification is sent to the subscriber if the filter matches, or if there is no filter.")
    private PkgmNotificationsFilter filter;
    @Schema(name = "Callback Uri", required = true, description = "The URI of the endpoint to send the notification to.")
    private String callbackUri;
    @Schema(name = "Links", required = true, description = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Links to resources related to this resource.")
    public static class Links {

        @Schema(name = "Self", required = true, description = "URI of this resource.")
        private Link self;

    }
}
