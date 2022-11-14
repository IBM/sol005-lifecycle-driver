package org.etsi.sol005.lifecyclemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents an externally provided link port to be used to connect an external connection point to an external VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents an externally provided link port to be used to connect an external connection point to an external VL.")
public class ExtLinkPortData {

    @Schema(name = "External Link Port Id", required = true, description = "Identifier of this link port as provided by the entity that has created the link port.")
    private String id;
    @Schema(name = "Resource Handle", required = true, description = "Reference to the virtualised resource realizing this link port.")
    private ResourceHandle resourceHandle;

}
