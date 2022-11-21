package org.etsi.sol005.packagemanagement;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents a VNF package management notification, which informs the receiver of the on-boarding of a VNF package.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a VNF package management notification, which informs the receiver of the on-boarding of a VNF package.")
public class VnfPackageOnboardingNotification {

    @Schema(name = "Identifier", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"VnfPackageOnboardingNotification\" for this notification type.")
    private String notificationType;
    @Schema(name = "Subscription Id", description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "Onboarded VNF Package Identifier", required = true, description = "Identifier of the on-boarded VNF package. This identifier is allocated by the NFVO.")
    private String onboardedVnfPkgId;
    @Schema(name = "VNFD Identifier", required = true, description = "This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It's copied from the VNFD of the on-boarded VNF package.")
    private String vnfdId;

    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private PkgmLinks links;

}
