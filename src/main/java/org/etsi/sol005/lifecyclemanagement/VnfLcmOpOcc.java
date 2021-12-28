package org.etsi.sol005.lifecyclemanagement;

import java.time.OffsetDateTime;
import java.util.List;

import org.etsi.sol005.common.Link;
import org.etsi.sol005.common.ProblemDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a VNF lifecycle management operation occurrence.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a VNF lifecycle management operation occurrence.")
public class VnfLcmOpOcc {

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of this VNF lifecycle management operation occurrence.")
    private String id;
    @ApiModelProperty(name = "Operation State", required = true, notes = "The state of the LCM operation.")
    private LcmOperationStateType operationState;
    @ApiModelProperty(name = "State Entered Time", required = true, notes = "Date-time when the current state was entered.")
    private OffsetDateTime stateEnteredTime;
    @ApiModelProperty(name = "Start Time", required = true, notes = "Date-time of the start of the operation.")
    private OffsetDateTime startTime;
    @ApiModelProperty(name = "VNF Instance Id", required = true, notes = "Identifier of the VNF instance to which the operation applies.")
    private String vnfInstanceId;
    @ApiModelProperty(name = "Grant Id", notes = "Identifier of the grant related to this VNF LCM operation occurrence, if such grant exists.")
    private String grantId;
    @ApiModelProperty(name = "Operation Type", required = true, notes = "Type of the actual LCM operation represented by this VNF LCM operation occurrence.")
    private LcmOperationType operation;
    @ApiModelProperty(name = "Automatic Invocation", required = true, notes = "Set to true if this VNF LCM operation occurrence has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf / ScaleVnfToLevel triggered by auto-scale, or HealVnf triggered by auto-heal). Set to false otherwise.")
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
    @ApiModelProperty(name = "Operation Parameters", required = true, notes = "Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation.")
    private Object operationParams;
    @ApiModelProperty(name = "Cancel Pending", required = true, notes = "If the VNF LCM operation occurrence is in \"STARTING\", \"PROCESSING\" or \"ROLLING_BACK\" state and the operation is being cancelled, this attribute shall be set to true. Otherwise, it shall be set to false.")
    @JsonProperty("isCancelPending")
    private boolean cancelPending;
    @ApiModelProperty(name = "Cancel Mode", notes = "The mode of an ongoing cancellation. Shall be present when isCancelPending=true, and shall be absent otherwise.")
    private CancelModeType cancelMode;
    @ApiModelProperty(name = "Error", notes = "If \"operationState\" is \"FAILED_TEMP\" or \"FAILED\" or \"operationState\" is \"PROCESSING\" or \"ROLLING_BACK\" and previous value of \"operationState\" was \"FAILED_TEMP\", this attribute shall be present and contain error information, unless it has been requested to be excluded via an attribute selector.")
    private ProblemDetails error;
    @ApiModelProperty(name = "Resource Changes", notes = "This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable.")
    private ResourceChanges resourceChanges;
    @ApiModelProperty(name = "Changed Information", notes = "Information about the changed VNF instance information, including VNF configurable properties, if applicable.")
    private VnfInfoModificationRequest changedInfo;
    @ApiModelProperty(name = "Changed External Connectivity", notes = "Information about changed external connectivity, if applicable.")
    private List<ExtVirtualLinkInfo> changedExtConnectivity;
    @ApiModelProperty(name = "Links", required = true, notes = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Links to resources related to this resource.")
    public static class Links {

        @ApiModelProperty(name = "self", required = true, notes = "URI of this resource.")
        private Link self;
        @ApiModelProperty(name = "vnfInstance", required = true, notes = "Link to the VNF instance that the operation applies to.")
        private Link vnfInstance;
        @ApiModelProperty(name = "grant", notes = "Link to the grant for this operation, if one exists.")
        private Link grant;
        @ApiModelProperty(name = "cancel", notes = "Link to the task resource that represents the \"cancel\" operation for this VNF LCM operation occurrence, if cancelling is currently allowed.")
        private Link cancel;
        @ApiModelProperty(name = "retry", notes = "Link to the task resource that represents the \"retry\" operation for this VNF LCM operation occurrence, if retrying is currently allowed.")
        private Link retry;
        @ApiModelProperty(name = "rollback", notes = "Link to the task resource that represents the \"rollback\" operation for this VNF LCM operation occurrence, if rolling back is currently allowed.")
        private Link rollback;
        @ApiModelProperty(name = "fail", notes = "Link to the task resource that represents the \"fail\" operation for this VNF LCM operation occurrence, if declaring as failed is currently allowed.")
        private Link fail;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start.")
    public static class ResourceChanges {

        @ApiModelProperty(name = "Affected VNFCs", notes = "Information about VNFC instances that were affected during the lifecycle operation.")
        private List<AffectedVnfc> affectedVnfcs;
        @ApiModelProperty(name = "Affected Virtual Links", notes = "Information about VL instances that were affected during the lifecycle operation.")
        private List<AffectedVirtualLink> affectedVirtualLinks;
        @ApiModelProperty(name = "Affected Virtual Storage", notes = "Information about virtualised storage instances that were affected during the lifecycle operation.")
        private List<AffectedVirtualStorage> affectedVirtualStorages;

    }

}
