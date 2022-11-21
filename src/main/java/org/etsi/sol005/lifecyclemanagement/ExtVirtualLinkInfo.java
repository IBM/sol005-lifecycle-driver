package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



import lombok.Data;

/**
 * Represents information about an external VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents information about an external VL.")
public class ExtVirtualLinkInfo {

    @Schema(name = "Id", required = true, description = "Identifier of the external VL and the related external VL information instance.")
    private String id;
    @Schema(name = "Resource Handle", required = true, description = "Reference to the resource realizing this VL.")
    private ResourceHandle resourceHandle;
    @Schema(name = "Link Ports", description = "Link ports of this VL.")
    private List<ExtLinkPortInfo> extLinkPorts;

}
