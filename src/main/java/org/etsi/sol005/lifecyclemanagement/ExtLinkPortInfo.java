package org.etsi.sol005.lifecyclemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents a link port of an external VL, i.e. a port providing connectivity for the VNF to an NS VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a link port of an external VL, i.e. a port providing connectivity for the VNF to an NS VL.")
public class ExtLinkPortInfo {

    @Schema(name = "Id", required = true, description = "Identifier of this link port as provided by the entity that has created the link port.")
    private String id;
    @Schema(name = "Resource Handle", required = true, description = "Reference to the virtualised network resource realizing this link port.")
    private ResourceHandle resourceHandle;
    @Schema(name = "Connection Point Instance Id", description = "Identifier of the external CP of the VNF to be connected to this link port. There shall be at most one link port associated with any external connection point instance. The value refers to an \"extCpInfo\" item in the VnfInstance.")
    private String cpInstanceId;

}
