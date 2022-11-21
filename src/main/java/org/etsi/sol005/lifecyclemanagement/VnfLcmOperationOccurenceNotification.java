package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.etsi.sol005.common.ProblemDetails;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a VNF lifecycle management operation occurrence notification, which informs the receiver of
 * changes in the VNF lifecycle caused by a VNF LCM operation occurrence.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a VNF lifecycle management operation occurrence notification, which informs the receiver of changes in the VNF lifecycle caused by a VNF LCM operation occurrence.")
public class VnfLcmOperationOccurenceNotification implements LifecycleManagementNotification {

    public static final String TYPE = "VnfLcmOperationOccurrenceNotification";

    @Schema(name = "Id", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"VnfLcmOperationOccurrenceNotification\" for this notification type.")
    private final String notificationType = TYPE;
    @Schema(name = "Subscription Id", required = true, description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "Notification Status", required = true, description = "Indicates whether this notification reports about the start of a lifecycle operation or the result of a lifecycle operation.")
    private NotificationStatus notificationStatus;
    @Schema(name = "Operation State", required = true, description = "The state of the VNF LCM operation occurrence.")
    private LcmOperationStateType operationState;
    @Schema(name = "VNF Instance Id", required = true, description = "The identifier of the VNF instance affected.")
    private String nsInstanceId;
    @Schema(name = "Operation Type", required = true, description = "The lifecycle management operation.")
    private LcmOperationType operation;
    @Schema(name = "Automatic Invocation", required = true, description = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise.")
    @JsonProperty("isAutomaticInvocation")
    private boolean automaticInvocation;
    @Schema(name = "VNF Lifecycle Management Operation Occurrence Id", required = true, description = "The identifier of the VNF lifecycle management operation occurrence associated to the notification.")
    private String vnfLcmOpOccId;
    @Schema(name = "Affected VNFCs", description = "Information about VNFC instances that were affected during the lifecycle operation.")
    private List<AffectedVnfc> affectedVnfcs;
    @Schema(name = "Affected Virtual Links", description = "Information about VL instances that were affected during the lifecycle operation.")
    private List<AffectedVirtualLink> affectedVirtualLinks;
    @Schema(name = "Affected Virtual Storage", description = "Information about virtualised storage instances that were affected during the lifecycle operation.")
    private List<AffectedVirtualStorage> affectedVirtualStorages;
    @Schema(name = "Changed Information", description = "Information about the changed VNF instance information, including changed VNF configurable properties. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the operation has performed any changes to VNF instance information, including VNF configurable properties. Shall be absent otherwise.")
    private VnfInfoModificationRequest changedInfo;
    @Schema(name = "Changed External Connectivity", description = "Information about changed external connectivity, if this notification represents the result of a lifecycle operation occurrence. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the \"operation\" is set to \"CHANGE_EXT_CONN\". Shall be absent otherwise.")
    private List<ExtVirtualLinkInfo> changedExtConnectivity;
    @Schema(name = "Error", description = "Details of the latest error, if one has occurred during executing the LCM operation. Shall be present if the \"operationState\" attribute is \"FAILED_TEMP\" or \"FAILED\", and shall be absent otherwise.")
    private ProblemDetails error;
    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private LccnLinks links;

    public enum NotificationStatus {
        /**
         * Informs about the start of the VNF LCM operation occurrence.
         */
        START,
        /**
         * Informs about the final or intermediate result of the VNF LCM operation occurrence
         */
        RESULT
    }
}
