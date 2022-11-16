package org.etsi.sol005.lifecyclemanagement;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents a monitoring parameter that is tracked by the VNFM, e.g. for auto-scaling purposes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a monitoring parameter that is tracked by the VNFM, e.g. for auto-scaling purposes.")
public class MonitoringParameter {

    @Schema(name = "Id", required = true, description = "Identifier of the monitoring parameter defined in the VNFD.")
    private String id;
    @Schema(name = "Name", description = "Human readable name of the monitoring parameter, as defined in the VNFD.")
    private String name;
    @Schema(name = "Value", required = true, description = "Value of the monitoring parameter known to the VNFM (e.g. obtained for autoscaling purposes).")
    private String value;
    @Schema(name = "Timestamp", required = true, description = "Represents the point in time when the measurement has been performed, as known to the VNFM.")
    private OffsetDateTime timeStamp;

}
