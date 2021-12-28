package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents request parameters for the "Scale VNF" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents request parameters for the \"Scale VNF\" operation.")
public class ScaleVnfRequest {

    @ApiModelProperty(name = "Scale Type", required = true, notes = "Indicates the type of the scale operation requested.")
    private ScaleType type;
    @ApiModelProperty(name = "Scaling Aspect Id", notes = "Identifier of the scaling aspect.")
    private String aspectId;
    @ApiModelProperty(name = "Number of Scaling Steps", notes = "Number of scaling steps to be executed as part of this Scale VNF operation. It shall be a positive number and the default value shall be 1.")
    private Integer numberOfSteps;
    @ApiModelProperty(name = "Additional Parameters", notes = "Additional parameters passed by the NFVO as input to the scaling process, specific to the VNF being scaled, as declared in the VNFD as part of \"ScaleVnfOpConfig\".")
    private Map<String, String> additionalParams;

}
