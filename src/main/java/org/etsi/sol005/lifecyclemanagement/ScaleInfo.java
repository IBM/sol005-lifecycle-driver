package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents the scale level of a VNF instance related to a scaling aspect.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents the scale level of a VNF instance related to a scaling aspect.")
public class ScaleInfo {

    @Schema(name = "Scaling Aspect Id", description = "Identifier of the scaling aspect.")
    private String aspectId;
    @Schema(name = "Scale Level", required = true, description = "Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD.")
    private Integer scaleLevel;

}
