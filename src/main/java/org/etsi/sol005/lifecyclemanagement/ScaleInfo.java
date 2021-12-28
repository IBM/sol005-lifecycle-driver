package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the scale level of a VNF instance related to a scaling aspect.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the scale level of a VNF instance related to a scaling aspect.")
public class ScaleInfo {

    @ApiModelProperty(name = "Scaling Aspect Id", notes = "Identifier of the scaling aspect.")
    private String aspectId;
    @ApiModelProperty(name = "Scale Level", required = true, notes = "Indicates the scale level. The minimum value shall be 0 and the maximum value shall be <= maxScaleLevel as described in the VNFD.")
    private Integer scaleLevel;

}
