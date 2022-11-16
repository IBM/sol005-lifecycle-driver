package org.etsi.sol005.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents an externally-managed internal VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents an externally-managed internal VL.")
public class ExtManagedVirtualLinkData {

    @Schema(name = "Virtual Link Id", required = true, description = "The identifier of the externally-managed internal VL instance.")
    private String id;
    @Schema(name = "VLD Id", required = true, description = "The identifier of the VLD in the VNFD for this VL.")
    private String virtualLinkDescId;
    @Schema(name = "VIM Connection Id", description = "Identifier of the VIM connection to manage this resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.")
    private String vimConnectionId;
    @Schema(name = "Resource Provider Id", description = "Identifies the entity responsible for the management of this resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable.")
    private String resourceProviderId;
    @Schema(name = "Resource Id", required = true, description = "The identifier of the resource in the scope of the VIM or the resource provider.")
    private String resourceId;

}
