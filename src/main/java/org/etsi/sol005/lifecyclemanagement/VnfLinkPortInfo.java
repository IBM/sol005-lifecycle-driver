package org.etsi.sol005.lifecyclemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents a link port of an internal VL of a VNF.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a link port of an internal VL of a VNF.")
public class VnfLinkPortInfo {

    @Schema(name = "Id", required = true, description = "Identifier of this link port as provided by the entity that has created the link port.")
    private String id;
    @Schema(name = "Resource Handle", required = true, description = "Reference to the virtualised network resource realizing this link port.")
    private ResourceHandle resourceHandle;
    @Schema(name = "Connection Point Instance Id", description = "Identifier of the external CP of the VNF to be connected to this link port. Shall be present when the link port is used for external connectivity by the VNF. May be present if used to reference a VNFC CP instance. There shall be at most one link port associated with any external connection point instance or internal connection point (i.e. VNFC CP) instance. The value refers to an \"extCpInfo\" item in the VnfInstance or a \"vnfcCpInfo\" item of a \"vnfcResouceInfo\" item in the VnfInstance.")
    private String cpInstanceId;

}
