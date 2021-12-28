package org.etsi.sol005.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the information that allows addressing a virtualised resource that is used by a VNF instance.
 * Information about the resource is available from the VIM.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the information that allows addressing a virtualised resource that is used by a VNF instance.")
public class ResourceHandle {

    @ApiModelProperty(name = "VIM Connection Id", notes = "Identifier of the VIM connection to manage the resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.")
    private String vimConnectionId;
    @ApiModelProperty(name = "Resource Provider Id", notes = "Identifier of the entity responsible for the management of the resource. This attribute shall only be supported and present when VNF-related resource management in indirect mode is applicable.")
    private String resourceProviderId;
    @ApiModelProperty(name = "Resource Id", required = true, notes = "Identifier of the resource in the scope of the VIM or the resource provider.")
    private String resourceId;
    @ApiModelProperty(name = "VIM Level Resource Type", notes = "Type of the resource in the scope of the VIM or the resource provider.")
    private String vimLevelResourceType;

}
