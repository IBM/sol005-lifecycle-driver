package org.etsi.sol005.lifecyclemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Represents the links to resources that a notification can contain.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents the links to resources that a notification can contain.")
public class LccnLinks {

    @Schema(name = "vnfInstance", required = true, description = "Link to the resource representing the VNF instance to which the notified change applies.")
    private Link vnfInstance;
    @Schema(name = "subscription", required = true, description = "Link to the related subscription.")
    private Link subscription;
    @Schema(name = "vnfLcmOpOcc", description = "Link to the VNF lifecycle management operation occurrence that this notification is related to. Shall be present if there is a related lifecycle operation occurrence.")
    private Link vnfLcmOpOcc;

}
