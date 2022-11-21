package org.etsi.sol005.granting;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.Link;
import org.etsi.sol005.lifecyclemanagement.LcmOperationType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



import lombok.Data;

/**
 * Represents a grant request.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a grant request.")
public class GrantRequest {

    @Schema(name = "VNF Instance Id", required = true, description = "Identifier of the VNF instance which this grant request is related to. Shall also be provided for VNFs that not yet exist but are planned to exist in the future, i.e. if the grant is requested for InstantiateVNF.")
    private String vnfInstanceId;
    @Schema(name = "VNF Lifecycle Management Operation Occurrence Id", required = true, description = "The identifier of the VNF lifecycle management operation occurrence associated to the GrantRequest.")
    private String vnfLcmOpOccId;
    @Schema(name = "VNFD Id", required = true, description = "Identifier of the VNFD that defines the VNF for which the LCM operation is to be granted.")
    private String vnfdId;
    @Schema(name = "VNF Deployment Flavour Id", description = "Identifier of the VNF deployment flavour of the VNFD that defines the VNF for which the LCM operation is to be granted. Shall be provided when instantiating the VNF or changing the deployment flavour of the  NF instance.")
    private String flavourId;
    @Schema(name = "Operation Type", required = true, description = "The lifecycle management operation for which granting is requested.")
    private LcmOperationType operation;
    @Schema(name = "Automatic Invocation", required = true, description = "Set to true if this VNF LCM operation occurrence  has been triggered by an automated procedure inside the VNFM (i.e. ScaleVnf ScaleVnfToLevel triggered by auto-scale, HealVnf triggered by auto-heal). Set to false otherwise.")
    @JsonProperty("isAutomaticInvocation")
    private boolean automaticInvocation;
    @Schema(name = "Instantiation Level Id", description = "If operation=INSTANTIATE, the identifier of the instantiation level may be provided as an alternative way to define the resources to be added. This attribute shall only be used if operation=INSTANTIATE.")
    private String instantiationLevelId;
    @Schema(name = "List of Resources to Add", description = "List of resource definitions in the VNFD for resources to be added by the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> addResources;
    @Schema(name = "List of Resources to Temporarily Instantiate", description = "List of resource definitions in the VNFD for resources to be temporarily instantiated during the runtime of the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> tempResources;
    @Schema(name = "List of Resources to Remove", description = "Provides the definitions of resources to be removed by the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> removeResources;
    @Schema(name = "List of Resources to Update", description = "Provides the definitions of resources to be modified by the LCM operation which is related to this grant request, with one entry per resource.")
    private List<ResourceDefinition> updateResources;
    @Schema(name = "List of Placement Constraints", description = "Placement constraints that the VNFM may send to the NFVO in order to influence the resource placement decision. If sent, the NFVO shall take the constraints into consideration when making resource placement decisions, and shall reject the grant if they cannot be honoured.")
    private List<PlacementConstraint> placementConstraints;
    @Schema(name = "List of VIM Constraints", description = "Used by the VNFM to require that multiple resources are managed through the same VIM connection. If sent, the NFVO shall take the  constraints into consideration when making VIM selection decisions, and shall reject the grant if they cannot be honoured. This attribute shall be supported if VNF-related Resource Management in direct mode is  applicable. The applicability and further details of this attribute for indirect mode are left for future specification.")
    private List<VimConstraint> vimConstraints;
    @Schema(name = "Additional Parameters", description = "Additional parameters passed by the VNFM, specific to the VNF and the LCM operation.")
    private Map<String, String> additionalParams;
    @Schema(name = "Links", required = true, description = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Links to resources related to this resource.")
    public static class Links {

        @Schema(name = "VNF Lifecycle Management Operation Occurrence", required = true, description = "Related VNF lifecycle management operation occurrence.")
        private Link vnfLcmOpOcc;
        @Schema(name = "VNF Instance", required = true, description = "Related VNF instance.")
        private Link vnfInstance;

    }

}
