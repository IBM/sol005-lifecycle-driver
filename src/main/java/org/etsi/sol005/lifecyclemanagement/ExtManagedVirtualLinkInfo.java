package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents information about an externally-managed virtual link.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents information about an externally-managed virtual link.")
public class ExtManagedVirtualLinkInfo {

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of the externally-managed internal VL and the related externally-managed VL information instance.")
    private String id;
    @ApiModelProperty(name = "VNF VLD Id", required = true, notes = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD.")
    private String vnfVirtualLinkDescId;
    @ApiModelProperty(name = "Network Resource", required = true, notes = "Reference to the VirtualNetwork resource.")
    private ResourceHandle networkResource;
    @ApiModelProperty(name = "Link Ports", notes = "Link ports of this VL.")
    private List<ExtLinkPortInfo> vnfLinkPorts;

}
