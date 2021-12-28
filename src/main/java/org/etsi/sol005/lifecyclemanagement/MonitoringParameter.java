package org.etsi.sol005.lifecyclemanagement;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a monitoring parameter that is tracked by the VNFM, e.g. for auto-scaling purposes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a monitoring parameter that is tracked by the VNFM, e.g. for auto-scaling purposes.")
public class MonitoringParameter {

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of the monitoring parameter defined in the VNFD.")
    private String id;
    @ApiModelProperty(name = "Name", notes = "Human readable name of the monitoring parameter, as defined in the VNFD.")
    private String name;
    @ApiModelProperty(name = "Value", required = true, notes = "Value of the monitoring parameter known to the VNFM (e.g. obtained for autoscaling purposes).")
    private String value;
    @ApiModelProperty(name = "Timestamp", required = true, notes = "Represents the point in time when the measurement has been performed, as known to the VNFM.")
    private OffsetDateTime timeStamp;

}
