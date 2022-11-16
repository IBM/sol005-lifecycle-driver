package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents request parameters for the "Operate VNF" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents request parameters for the \"Operate VNF\" operation.")
public class OperateVnfRequest {

    @Schema(name = "Flavour Id", required = true, description = "The desired operational state (i.e. started or stopped) to change the VNF to.")
    private VnfOperationalStateType changeStateTo;
    @Schema(name = "Stop Type", description = "It signals whether forceful or graceful stop is requested.")
    private StopType stopType;
    @Schema(name = "Graceful Stop Timeout", description = "The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF.")
    private Integer gracefulStopTimeout;
    @Schema(name = "Additional Parameters", description = "Additional parameters passed by the NFVO as input to the process, specific to the VNF of which the operation status is changed, as declared in the VNFD as part of \"OperateVnfOpConfig\".")
    private Map<String, String> additionalParams;

}
