package org.etsi.sol005.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents an externally-managed internal VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents an externally-managed internal VL.")
public class ExtManagedVirtualLinkData {

    @ApiModelProperty(name = "Virtual Link Id", required = true, notes = "The identifier of the externally-managed internal VL instance.")
    private String id;
    @ApiModelProperty(name = "VLD Id", required = true, notes = "The identifier of the VLD in the VNFD for this VL.")
    private String virtualLinkDescId;
    @ApiModelProperty(name = "VIM Connection Id", notes = "Identifier of the VIM connection to manage this resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.")
    private String vimConnectionId;
    @ApiModelProperty(name = "Resource Provider Id", notes = "Identifies the entity responsible for the management of this resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable.")
    private String resourceProviderId;
    @ApiModelProperty(name = "Resource Id", required = true, notes = "The identifier of the resource in the scope of the VIM or the resource provider.")
    private String resourceId;

}
