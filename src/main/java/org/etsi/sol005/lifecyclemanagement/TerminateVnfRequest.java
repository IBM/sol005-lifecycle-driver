package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents request parameters for the "Terminate VNF" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents request parameters for the \"Terminate VNF\" operation.")
public class TerminateVnfRequest {

    @ApiModelProperty(name = "Termination Type", required = true, notes = "Indicates whether forceful or graceful termination is requested.")
    private TerminationType terminationType;
    @ApiModelProperty(name = "Graceful Termination Timeout", notes = "This attribute is only applicable in case of graceful termination. It defines the time to wait for the VNF to be taken out of service before shutting down the VNF and releasing the resources. The unit is seconds.")
    private Integer gracefulTerminationTimeout;
    @ApiModelProperty(name = "Additional Parameters", notes = "Additional parameters passed by the NFVO as input to the termination process, specific to the VNF being terminated, as declared in the VNFD as part of \"TerminateVnfOpConfig\".")
    private Map<String, String> additionalParams;

}
