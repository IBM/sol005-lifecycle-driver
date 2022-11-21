package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



import lombok.Data;

/**
 * Represents information about an externally-managed virtual link.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents information about an externally-managed virtual link.")
public class ExtManagedVirtualLinkInfo {

    @Schema(name = "Id", required = true, description = "Identifier of the externally-managed internal VL and the related externally-managed VL information instance.")
    private String id;
    @Schema(name = "VNF VLD Id", required = true, description = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD.")
    private String vnfVirtualLinkDescId;
    @Schema(name = "Network Resource", required = true, description = "Reference to the VirtualNetwork resource.")
    private ResourceHandle networkResource;
    @Schema(name = "Link Ports", description = "Link ports of this VL.")
    private List<ExtLinkPortInfo> vnfLinkPorts;

}
