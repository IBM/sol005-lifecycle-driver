package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents information about an external VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents information about an external VL.")
public class ExtVirtualLinkInfo {

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of the external VL and the related external VL information instance.")
    private String id;
    @ApiModelProperty(name = "Resource Handle", required = true, notes = "Reference to the resource realizing this VL.")
    private ResourceHandle resourceHandle;
    @ApiModelProperty(name = "Link Ports", notes = "Link ports of this VL.")
    private List<ExtLinkPortInfo> extLinkPorts;

}
