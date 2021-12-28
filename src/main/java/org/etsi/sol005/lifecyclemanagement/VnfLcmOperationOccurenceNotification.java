package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Represents a VNF lifecycle management operation occurrence notification, which informs the receiver of changes in the VNF lifecycle caused by a VNF LCM operation occurrence.")
public class VnfLcmOperationOccurenceNotification implements LifecycleManagementNotification {

    public static final String TYPE = "VnfLcmOperationOccurrenceNotification";

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @ApiModelProperty(name = "Notification Type", required = true, notes = "Discriminator for the different notification types. Shall be set to \"VnfLcmOperationOccurrenceNotification\" for this notification type.")
    private final String notificationType = TYPE;
    @ApiModelProperty(name = "Subscription Id", required = true, notes = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @ApiModelProperty(name = "Notification Time", required = true, notes = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @ApiModelProperty(name = "Notification Status", required = true, notes = "Indicates whether this notification reports about the start of a lifecycle operation or the result of a lifecycle operation.")
    private NotificationStatus notificationStatus;
    @ApiModelProperty(name = "Operation State", required = true, notes = "The state of the VNF LCM operation occurrence.")
    private LcmOperationStateType operationState;
    @ApiModelProperty(name = "VNF Instance Id", required = true, notes = "The identifier of the VNF instance affected.")
    private String nsInstanceId;
    @ApiModelProperty(name = "Operation Type", required = true, notes = "The lifecycle management operation.")
    private LcmOperationType operation;
    @ApiModelProperty(name = "Automatic Invocation", required = true, notes = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise.")
    @JsonProperty("isAutomaticInvocation")
    private boolean automaticInvocation;
    @ApiModelProperty(name = "VNF Lifecycle Management Operation Occurrence Id", required = true, notes = "The identifier of the VNF lifecycle management operation occurrence associated to the notification.")
    private String vnfLcmOpOccId;
    @ApiModelProperty(name = "Affected VNFCs", notes = "Information about VNFC instances that were affected during the lifecycle operation.")
    private List<AffectedVnfc> affectedVnfcs;
    @ApiModelProperty(name = "Affected Virtual Links", notes = "Information about VL instances that were affected during the lifecycle operation.")
    private List<AffectedVirtualLink> affectedVirtualLinks;
    @ApiModelProperty(name = "Affected Virtual Storage", notes = "Information about virtualised storage instances that were affected during the lifecycle operation.")
    private List<AffectedVirtualStorage> affectedVirtualStorages;
    @ApiModelProperty(name = "Changed Information", notes = "Information about the changed VNF instance information, including changed VNF configurable properties. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the operation has performed any changes to VNF instance information, including VNF configurable properties. Shall be absent otherwise.")
    private VnfInfoModificationRequest changedInfo;
    @ApiModelProperty(name = "Changed External Connectivity", notes = "Information about changed external connectivity, if this notification represents the result of a lifecycle operation occurrence. Shall be present if the \"notificationStatus\" is set to \"RESULT\" and the \"operation\" is set to \"CHANGE_EXT_CONN\". Shall be absent otherwise.")
    private List<ExtVirtualLinkInfo> changedExtConnectivity;
    @ApiModelProperty(name = "Error", notes = "Details of the latest error, if one has occurred during executing the LCM operation. Shall be present if the \"operationState\" attribute is \"FAILED_TEMP\" or \"FAILED\", and shall be absent otherwise.")
    private ProblemDetails error;
    @ApiModelProperty(name = "Links", required = true, notes = "Links to resources related to this notification.")
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
