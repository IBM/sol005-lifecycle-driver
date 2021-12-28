package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.etsi.sol005.model.*;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a NS lifecycle management operation occurrence notification, which informs the receiver of
 * changes in the NS lifecycle caused by a NS LCM operation occurrence.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a NS lifecycle management operation occurrence notification, which informs the receiver of changes in the VNF lifecycle caused by a VNF LCM operation occurrence.")
public class NsLcmOperationOccurrenceNotification implements LifecycleManagementNotification {

    public static final String TYPE = "NsLcmOperationOccurrenceNotification";

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @ApiModelProperty(name = "NS Instance Id", required = true, notes = "The identifier of the NS instance affected.")
    private String nsInstanceId;
    @ApiModelProperty(name = "Operation Type", required = true, notes = "The lifecycle operation.")
    private LcmOperationType operation;
    @ApiModelProperty(name = "NS Lifecycle Management Operation Occurrence Id", required = true, notes = "The identifier of the NS lifecycle operation occurrence associated to the notification.")
    private String nsLcmOpOccId;
    @ApiModelProperty(name = "Notification Type", required = true, notes = "Discriminator for the different notification types. Shall\n" + "be set to \"NsLcmOperationOccurrenceNotification\" for\n" + "this notification type. ")
    private final String notificationType = TYPE;
    @ApiModelProperty(name = "Subscription Id", required = true, notes = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @ApiModelProperty(name = "Notification Time", required = true, notes = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @ApiModelProperty(name = "Notification Status", required = true, notes = "Indicates whether this notification reports about the start of a lifecycle operation or the result of a lifecycle operation.")
    private NotificationStatus notificationStatus;
    @ApiModelProperty(name = "Operation State", required = true, notes = "The state of the NS LCM operation occurrence.")
    private LcmOperationStateType operationState;
    @ApiModelProperty(name = "Automatic Invocation", required = true, notes = "Set to true if this NS LCM operation occurrence has been triggered by an automated procedure inside the NFVO (i.e. ScaleNs / ScaleNsToLevel triggered by auto-scale, or HealNs triggered by auto-heal). Set to false otherwise.")
    @JsonProperty("isAutomaticInvocation")
    private boolean automaticInvocation;
    @ApiModelProperty(name = "Affected VNFs", notes = "Information about the VNF instances that were affected during the lifecycle operation. See note.")
    private List<AffectedVnf> affectedVnf;
    @ApiModelProperty(name = "Affected PNFs", notes = "Information about the PNF instances that were affected during the lifecycle operation. See note.")
    private List<AffectedPnf> affectedPnf;
    @ApiModelProperty(name = "Affected Virtual Links", notes = "Information about VL instances that were affected during the lifecycle operation.")
    private List<AffectedVirtualLink> affectedVirtualLinks;
    @ApiModelProperty(name = "Affected Virtual Storage", notes = "Information about the VNFFG instances that were affected during the lifecycle operation. See note.")
    private List<AffectedVnffg> affectedVnffg;
    @ApiModelProperty(name = "Affected Ns", notes = "Information about the NS instances that were affected during the lifecycle operation. See note.")
    private List<AffectedNs> affectedNs;
    @ApiModelProperty(name = "Affected Sap", notes = "Information about the SAP instances that affected during the lifecycle operation. See note.")
    private List<AffectedSap> affectedSap;
    @ApiModelProperty(name = "Error", notes = "Details of the latest error, if one has occurred during\n" +"executing the LCM operation (see clause 6.3 of ETSI\n" + "GS NFV-SOL 013 [16]). Shall be present if\n" + "operationState is \"FAILED_TEMP\" or \"FAILED\", and\n" + "shall be absent otherwise.")
    private ProblemDetails error;
    @ApiModelProperty(name = "Links", required = true, notes = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private LccnLinks links;

    public enum NotificationStatus {
        /**
         * Informs about the start of the NS LCM operation occurrence.
         */
        START,
        /**
         * Informs about the final or intermediate result of the NS LCM operation occurrence
         */
        RESULT
    }
}

