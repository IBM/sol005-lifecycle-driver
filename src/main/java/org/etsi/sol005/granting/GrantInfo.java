package org.etsi.sol005.granting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents information about a Compute, storage or network resource whose addition/update/deletion was granted.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents information about a Compute, storage or network resource whose addition/update/deletion was granted.")
public class GrantInfo {

    @ApiModelProperty(name = "Resource Definition Identifier", required = true, notes = "Identifier of the related \"ResourceDefinition\" structure from the related \"GrantRequest\" structure.")
    private String resourceDefinitionId;
    @ApiModelProperty(name = "Reservation Identifier", notes ="The reservation identifier applicable to the VNFC/VirtualLink/VirtualStorage. It shall be present for new resources when policy is GRANT_RESERVE_MULTI and anapplicable reservation exists; shall not be present otherwise.")
    private String reservationId;
    @ApiModelProperty(name = "VIM Connection Identifier", notes ="Identifier of the VIM connection to be used to managethis resource. Shall be present for new resources, andshall be absent for resources that have already beenallocated.The applicable \"VimConnectionInfo\" structure, whichis referenced by vimConnectionId, can be obtainedfrom the \"vimConnectionInfo\" attribute of the\"VnfInstance\" structure.This attribute shall only be supported when VNFrelatedResource Management in direct mode isapplicable.")
    private String vimConnectionId;
    @ApiModelProperty(name = "Resource Provider Identifier", notes ="Identifies the entity responsible for the management ofthe virtualised resource.Shall be present for new resources, and shall beabsent for resources that have already been allocated.This attribute shall only be supported when VNFrelatedResource Management in indirect mode isapplicable. The identification scheme is outside thescope of the present document.")
    private String resourceProviderId;
    @ApiModelProperty(name = "Zone Identifier",  notes ="Reference to the identifier of the \"ZoneInfo\" structurein the \"Grant\" structure defining the resource zone intowhich this resource is to be placed. Shall be presentfor new resources if the zones concept is applicable tothem (typically, Compute resources), and shall beabsent for resources that have already been allocated.")
    private String zoneId;
    @ApiModelProperty(name = "Resource Group Identifier", notes ="Identifier of the \"infrastructure resource group\", logicalgrouping of virtual resources assigned to a tenantwithin an Infrastructure Domain, to be provided whenallocating the resource.If the VIM connection referenced by\"vimConnectionId\" applies to multiple infrastructureresource groups, this attribute shall be present for newresources.If the VIM connection referenced by\"vimConnectionId\" applies to a single infrastructureresource group, this attribute may be present for newresources.This attribute shall be absent for resources that havealready been allocated.")
    private String resourceGroupId;

}