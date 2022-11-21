package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents request parameters for the "Scale VNF" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents request parameters for the \"Scale VNF\" operation.")
public class ScaleVnfRequest {

    @Schema(name = "Scale Type", required = true, description = "Indicates the type of the scale operation requested.")
    private ScaleType type;
    @Schema(name = "Scaling Aspect Id", description = "Identifier of the scaling aspect.")
    private String aspectId;
    @Schema(name = "Number of Scaling Steps", description = "Number of scaling steps to be executed as part of this Scale VNF operation. It shall be a positive number and the default value shall be 1.")
    private Integer numberOfSteps;
    @Schema(name = "Additional Parameters", description = "Additional parameters passed by the NFVO as input to the scaling process, specific to the VNF being scaled, as declared in the VNFD as part of \"ScaleVnfOpConfig\".")
    private Map<String, String> additionalParams;

}
