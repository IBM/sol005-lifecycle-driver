package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a parameter to select the mode of cancelling an ongoing VNF LCM operation occurrence.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a parameter to select the mode of cancelling an ongoing VNF LCM operation occurrence.")
public class CancelMode {

    @ApiModelProperty(name = "Cancel Mode", required = true, notes = "Cancellation mode to apply.")
    private CancelModeType cancelMode;

}
