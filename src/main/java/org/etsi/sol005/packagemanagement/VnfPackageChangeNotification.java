package org.etsi.sol005.packagemanagement;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents a VNF package management notification, which informs the receiver of a change of the status in a
 * VNF package. Only changes in operationalState and/or deletionPending attributes will be reported. Change in
 * usageState attribute is not reported.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a VNF package management notification, which informs the receiver of a change of the status in a VNF package. Only changes in operationalState and/or deletionPending attributes will be reported. Change in usageState attribute is not reported.")
public class VnfPackageChangeNotification {

    @Schema(name = "Identifier", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"VnfPackageChangeNotification\" for this notification type.")
    private String notificationType;
    @Schema(name = "Subscription Identifier", description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "Onboarded VNF Package Identifier", required = true, description = "Identifier of the on-boarded VNF package. This identifier is allocated by the NFVO.")
    private String onboardedVnfPkgId;
    @Schema(name = "VNFD Identifier", required = true, description = "Identifier of the VNFD contained in the VNF package, which also identifies the VNF package. This identifier is allocated by the VNF provider and copied from the VNFD.")
    private String vnfdId;
    @Schema(name = "Change Type", required = true, description = "The type of change of the VNF package.")
    private PackageChangeType changeType;
    @Schema(name = "Operational State", description = "New operational state of the VNF package. Only present when changeType is OP_STATE_CHANGE.")
    private PackageOperationalStateType operationalState;
    @Schema(name = "Deletion Pending", description = "The value \"true\" indicates that the deletion instance of the VNF package has been requested but the VNF package is still being used by instantiated VNFs. Only present when changeType is DELETE_PEND_CHANGE.")
    private boolean deletionPending;
    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private PkgmLinks links;
}
