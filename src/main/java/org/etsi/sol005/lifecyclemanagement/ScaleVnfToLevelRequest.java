package org.etsi.sol005.lifecyclemanagement;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents request parameters for the "Scale VNF to Level" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents request parameters for the \"Scale VNF to Level\" operation.")
public class ScaleVnfToLevelRequest {

    @ApiModelProperty(name = "Scale Type", notes = "Identifier of the target instantiation level of the current deployment flavour to which the VNF is requested to be scaled. NOTE: Either the instantiationLevelId attribute or the scaleInfo attribute shall be included.")
    private String instantiationLevelId;
    @ApiModelProperty(name = "Scaling Aspect Id", notes = "For each scaling aspect of the current deployment flavour, indicates the target scale level to which the VNF is to be scaled. NOTE: Either the instantiationLevelId attribute or the scaleInfo attribute shall be included.")
    private List<ScaleInfo> scaleInfo;
    @ApiModelProperty(name = "Additional Parameters", notes = "Additional parameters passed by the NFVO as input to the scaling process, specific to the VNF being scaled, as declared in the VNFD as part of \"ScaleVnfToLevelOpConfig\".")
    private Map<String, String> additionalParams;

}
