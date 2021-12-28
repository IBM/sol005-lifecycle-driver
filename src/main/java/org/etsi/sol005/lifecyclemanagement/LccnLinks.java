package org.etsi.sol005.lifecyclemanagement;

import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the links to resources that a notification can contain.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the links to resources that a notification can contain.")
public class LccnLinks {

    @ApiModelProperty(name = "vnfInstance", required = true, notes = "Link to the resource representing the VNF instance to which the notified change applies.")
    private Link vnfInstance;
    @ApiModelProperty(name = "subscription", required = true, notes = "Link to the related subscription.")
    private Link subscription;
    @ApiModelProperty(name = "vnfLcmOpOcc", notes = "Link to the VNF lifecycle management operation occurrence that this notification is related to. Shall be present if there is a related lifecycle operation occurrence.")
    private Link vnfLcmOpOcc;

}
