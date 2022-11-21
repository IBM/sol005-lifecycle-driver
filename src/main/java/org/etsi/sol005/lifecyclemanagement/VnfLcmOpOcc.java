package org.etsi.sol005.lifecyclemanagement;

import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.Link;
import org.etsi.sol005.common.ProblemDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a VNF lifecycle management operation occurrence.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a VNF lifecycle management operation occurrence.")
public class VnfLcmOpOcc {

    @Schema(name = "Id", required = true, description = "Identifier of this VNF lifecycle management operation occurrence.")
    private String id;
    @Schema(name = "Operation State", required = true, description = "The state of the LCM operation.")
    private LcmOperationStateType operationState;
    @Schema(name = "State Entered Time", required = true, description = "Date-time when the current state was entered.")
    private OffsetDateTime stateEnteredTime;
    @Schema(name = "Start Time", required = true, description = "Date-time of the start of the operation.")
    private OffsetDateTime startTime;
    @Schema(name = "VNF Instance Id", required = true, description = "Identifier of the VNF instance to which the operation applies.")
    private String vnfInstanceId;
    @Schema(name = "Grant Id", description = "Identifier of the grant related to this VNF LCM operation occurrence, if such grant exists.")
    private String grantId;
    @Schema(name = "Operation Type", required = true, description = "Type of the actual LCM operation represented by this VNF LCM operation occurrence.")
    private LcmOperationType operation;
    @Schema(name = "Automatic Invocation", required = true, description = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise.")
    @JsonProperty("isAutomaticInvocation")
    private boolean automaticInvocation;
    /*
     * The following mapping between operationType and the data type of this attribute shall apply:
     *   • INSTANTIATE: InstantiateVnfRequest
     *   • SCALE: ScaleVnfRequest
     *   • SCALE_TO_LEVEL: ScaleVnfToLevelRequest
     *   • CHANGE_FLAVOUR: ChangeVnfFlavourRequest
     *   • OPERATE: OperateVnfRequest
     *   • HEAL: HealVnfRequest
     *   • CHANGE_EXT_CONN: ChangeExtVnfConnectivityRequest
     *   • TERMINATE: TerminateVnfRequest
     *   • MODIFY_INFO: VnfInfoModifications
     */
    @Schema(name = "Operation Parameters", required = true, description = "Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation.")
    private Object operationParams;
    @Schema(name = "Cancel Pending", required = true, description = "If the VNF LCM operation occurrence is in \"STARTING\", \"PROCESSING\" or \"ROLLING_BACK\" state and the operation is being cancelled, this attribute shall be set to true. Otherwise, it shall be set to false.")
    @JsonProperty("isCancelPending")
    private boolean cancelPending;
    @Schema(name = "Cancel Mode", description = "The mode of an ongoing cancellation. Shall be present when isCancelPending=true, and shall be absent otherwise.")
    private CancelModeType cancelMode;
    @Schema(name = "Error", description = "If \"operationState\" is \"FAILED_TEMP\" or \"FAILED\" or \"operationState\" is \"PROCESSING\" or \"ROLLING_BACK\" and previous value of \"operationState\" was \"FAILED_TEMP\", this attribute shall be present and contain error information, unless it has been requested to be excluded via an attribute selector.")
    private ProblemDetails error;
    @Schema(name = "Resource Changes", description = "This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable.")
    private ResourceChanges resourceChanges;
    @Schema(name = "Changed Information", description = "Information about the changed VNF instance information, including VNF configurable properties, if applicable.")
    private VnfInfoModificationRequest changedInfo;
    @Schema(name = "Changed External Connectivity", description = "Information about changed external connectivity, if applicable.")
    private List<ExtVirtualLinkInfo> changedExtConnectivity;
    @Schema(name = "Links", required = true, description = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Links to resources related to this resource.")
    public static class Links {

        @Schema(name = "self", required = true, description = "URI of this resource.")
        private Link self;
        @Schema(name = "vnfInstance", required = true, description = "Link to the VNF instance that the operation applies to.")
        private Link vnfInstance;
        @Schema(name = "grant", description = "Link to the grant for this operation, if one exists.")
        private Link grant;
        @Schema(name = "cancel", description = "Link to the task resource that represents the \"cancel\" operation for this VNF LCM operation occurrence, if cancelling is currently allowed.")
        private Link cancel;
        @Schema(name = "retry", description = "Link to the task resource that represents the \"retry\" operation for this VNF LCM operation occurrence, if retrying is currently allowed.")
        private Link retry;
        @Schema(name = "rollback", description = "Link to the task resource that represents the \"rollback\" operation for this VNF LCM operation occurrence, if rolling back is currently allowed.")
        private Link rollback;
        @Schema(name = "fail", description = "Link to the task resource that represents the \"fail\" operation for this VNF LCM operation occurrence, if declaring as failed is currently allowed.")
        private Link fail;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start.")
    public static class ResourceChanges {

        @Schema(name = "Affected VNFCs", description = "Information about VNFC instances that were affected during the lifecycle operation.")
        private List<AffectedVnfc> affectedVnfcs;
        @Schema(name = "Affected Virtual Links", description = "Information about VL instances that were affected during the lifecycle operation.")
        private List<AffectedVirtualLink> affectedVirtualLinks;
        @Schema(name = "Affected Virtual Storage", description = "Information about virtualised storage instances that were affected during the lifecycle operation.")
        private List<AffectedVirtualStorage> affectedVirtualStorages;

    }

}
