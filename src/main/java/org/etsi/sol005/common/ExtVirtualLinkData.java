package org.etsi.sol005.common;

import java.util.List;

import org.etsi.sol005.lifecyclemanagement.ExtLinkPortData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents an external VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents an external VL.")
public class ExtVirtualLinkData {

    @ApiModelProperty(name = "Virtual Link Id", required = true, notes = "The identifier of the external VL instance.")
    private String id;
    @ApiModelProperty(name = "VIM Connection Id", notes = "Identifier of the VIM connection to manage this resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.")
    private String vimConnectionId;
    @ApiModelProperty(name = "Resource Provider Id", notes = "Identifies the entity responsible for the management of this resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable.")
    private String resourceProviderId;
    @ApiModelProperty(name = "Resource Id", required = true, notes = "The identifier of the resource in the scope of the VIM or the resource provider.")
    private String resourceId;
    @ApiModelProperty(name = "External Connection Points", required = true, notes = "External CPs of the VNF to be connected to this external VL.")
    private List<VnfExtCpData> extCps;
    @ApiModelProperty(name = "External Link Ports", notes = "Externally provided link ports to be used to connect external connection points to this external VL. If this attribute is not present, the VNFM shall create the link ports on the external VL.")
    private List<ExtLinkPortData> extLinkPorts;

}
