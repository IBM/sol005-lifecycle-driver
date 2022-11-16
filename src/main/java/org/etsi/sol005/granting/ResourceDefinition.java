package org.etsi.sol005.granting;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



import lombok.Data;

/**
 * Represents information of an existing or proposed resource used by the VNF..
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a subscription request related to notifications about VNF lifecycle changes.")
public class ResourceDefinition {

    @Schema(name = "Identifier", required = true, description = "Identifier of this \"ResourceDefinition\" structure, unique at least within the scope of the \"GrantRequest\" structure.")
    private String id;
    @Schema(name = "Type", required = true, description = "Type of the resource definition referenced.")
    private ResourceDefinitionType type;
    @Schema(name = "VDU Reference", description = "Reference to the related VDU in the VNFD applicable to this resource. Shall only be present if a VDU is applicable to this resource.")
    private String vduId;
    @Schema(name = "Resource Template Reference", description = "Reference to a resource template (VnfVirtualLinkDesc, VirtualComputeDesc, VnfExtCpd, VirtualStorageDesc) in the VNFD. Shall be present for the planned creation of new resources, including temporary resources, and for the modification of existing resources. Shall be absent otherwise.")
    private String resourceTemplateId;
    @Schema(name = "Resource Template Reference", description = "Resource information for an existing resource. Shall be present for resources that are planned to be deleted or modified. Shall be absent otherwise.")
    private ResourceHandle resource;

}
