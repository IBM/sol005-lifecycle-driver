package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents request parameters for the "Create VNF identifier" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents request parameters for the \"Create VNF identifier\" operation.")
public class CreateVnfRequest {

    @ApiModelProperty(name = "VNFD Id", required = true, notes = "Identifier that identifies the VNFD which defines the VNF instance to be created.")
    private String vnfdId;
    @ApiModelProperty(name = "VNF Instance Name", notes = "Human-readable name of the VNF instance to be created.")
    private String vnfInstanceName;
    @ApiModelProperty(name = "VNF Instance Description", notes = "Human-readable description of the VNF instance to be created.")
    private String vnfInstanceDescription;

}
