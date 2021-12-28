package org.etsi.sol005.granting;

import java.util.List;
import java.util.Map;

import org.etsi.sol005.common.Link;
import org.etsi.sol005.lifecyclemanagement.LcmOperationType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a grant request.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a grant request.")
public class GrantRequest {

    @ApiModelProperty(name = "VNF Instance Id", required = true, notes = "Identifier of the VNF instance which this grant request is related to. Shall also be provided for VNFs that not yet exist but are planned to exist in the future, i.e. if the grant is requested for InstantiateVNF.")
    private String vnfInstanceId;
    @ApiModelProperty(name = "VNF Lifecycle Management Operation Occurrence Id", required = true, notes = "The identifier of the VNF lifecycle management operation occurrence associated to the GrantRequest.")
    private String vnfLcmOpOccId;
    @ApiModelProperty(name = "VNFD Id", required = true, notes = "Identifier of the VNFD that defines the VNF for which the LCM operation is to be granted.")
    private String vnfdId;
    @ApiModelProperty(name = "VNF Deployment Flavour Id", notes = "Identifier of the VNF deployment flavour of the VNFD that defines the VNF for which the LCM operation is to be granted. Shall be provided when instantiating the VNF or changing the deployment flavour of the  NF instance.")
    private String flavourId;
    @ApiModelProperty(name = "Operation Type", required = true, notes = "The lifecycle management operation for which granting is requested.")
    private LcmOperationType operation;
    @ApiModelProperty(name = "Automatic Invocation", required = true, notes = "Set to true if this VNF LCM operation occurrence  has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf ScaleVnfToLevel triggered by auto-scale, HealVnf triggered by auto-heal). Set to false otherwise.")
    @JsonProperty("isAutomaticInvocation")
    private boolean automaticInvocation;
    @ApiModelProperty(name = "Instantiation Level Id", notes = "If operation=INSTANTIATE, the identifier of the instantiation level may be provided as an alternative way to define the resources to be added. This attribute shall only be used if operation=INSTANTIATE.")
    private String instantiationLevelId;
    @ApiModelProperty(name = "List of Resources to Add", notes = "List of resource definitions in the VNFD for resources to be added by the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> addResources;
    @ApiModelProperty(name = "List of Resources to Temporarily Instantiate", notes = "List of resource definitions in the VNFD for resources to be temporarily instantiated during the runtime of the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> tempResources;
    @ApiModelProperty(name = "List of Resources to Remove", notes = "Provides the definitions of resources to be removed by the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> removeResources;
    @ApiModelProperty(name = "List of Resources to Update", notes = "Provides the definitions of resources to be modified by the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> updateResources;
    @ApiModelProperty(name = "List of Placement Constraints", notes = "Placement constraints that the VNFM may send to the NFVO in order to influence the resource placement decision. If sent, the NFVO shall take the constraints into consideration when making resource placement decisions, and shall reject the grant if they cannot be honoured.")
    private List<PlacementConstraint> placementConstraints;
    @ApiModelProperty(name = "List of VIM Constraints", notes = "Used by the VNFM to require that multiple resources are managed through the same VIM connection. If sent, the NFVO shall take the  constraints into consideration when making VIM selection decisions, and shall reject the grant if they cannot be honoured. This attribute shall be supported if VNF-related Resource Management in direct mode is  applicable. The applicability and further details of this attribute for indirect mode are left for future specification.")
    private List<VimConstraint> vimConstraints;
    @ApiModelProperty(name = "Additional Parameters", notes = "Additional parameters passed by the VNFM, specific to the VNF and the LCM operation.")
    private Map<String, String> additionalParams;
    @ApiModelProperty(name = "Links", required = true, notes = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Links to resources related to this resource.")
    public static class Links {

        @ApiModelProperty(name = "VNF Lifecycle Management Operation Occurrence", required = true, notes = "Related VNF lifecycle management operation occurrence.")
        private Link vnfLcmOpOcc;
        @ApiModelProperty(name = "VNF Instance", required = true, notes = "Related VNF instance.")
        private Link vnfInstance;

    }

}
