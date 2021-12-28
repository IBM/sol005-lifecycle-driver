package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents request parameters for the "Operate VNF" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents request parameters for the \"Operate VNF\" operation.")
public class OperateVnfRequest {

    @ApiModelProperty(name = "Flavour Id", required = true, notes = "The desired operational state (i.e. started or stopped) to change the VNF to.")
    private VnfOperationalStateType changeStateTo;
    @ApiModelProperty(name = "Stop Type", notes = "It signals whether forceful or graceful stop is requested.")
    private StopType stopType;
    @ApiModelProperty(name = "Graceful Stop Timeout", notes = "The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF.")
    private Integer gracefulStopTimeout;
    @ApiModelProperty(name = "Additional Parameters", notes = "Additional parameters passed by the NFVO as input to the process, specific to the VNF of which the operation status is changed, as declared in the VNFD as part of \"OperateVnfOpConfig\".")
    private Map<String, String> additionalParams;

}
