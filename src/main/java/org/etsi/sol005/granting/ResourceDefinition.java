package org.etsi.sol005.granting;

import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents information of an existing or proposed resource used by the VNF..
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a subscription request related to notifications about VNF lifecycle changes.")
public class ResourceDefinition {

    @ApiModelProperty(name = "Identifier", required = true, notes = "Identifier of this \"ResourceDefinition\" structure, unique at least within the scope of the \"GrantRequest\" structure.")
    private String id;
    @ApiModelProperty(name = "Type", required = true, notes = "Type of the resource definition referenced.")
    private ResourceDefinitionType type;
    @ApiModelProperty(name = "VDU Reference", notes = "Reference to the related VDU in the VNFD applicable to this resource. Shall only be present if a VDU is applicable to this resource.")
    private String vduId;
    @ApiModelProperty(name = "Resource Template Reference", notes = "Reference to a resource template (VnfVirtualLinkDesc, VirtualComputeDesc, VnfExtCpd, VirtualStorageDesc) in the VNFD. Shall be present for the planned creation of new resources, including temporary resources, and for the modification of existing resources. Shall be absent otherwise.")
    private String resourceTemplateId;
    @ApiModelProperty(name = "Resource Template Reference", notes = "Resource information for an existing resource. Shall be present for resources that are planned to be deleted or modified. Shall be absent otherwise.")
    private ResourceHandle resource;

}
