package org.etsi.sol005.packagemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the checksum of a VNF package or an artifact file.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the checksum of a VNF package or an artifact file.")
public class Checksum {

    @ApiModelProperty(name = "Algorithm", required = true, notes = "Name of the algorithm used to generate the checksum,as defined in ETSI GS NFV-SOL 004 [2]. For example,SHA-256, SHA-512.")
    private String algorithm;
    @ApiModelProperty(name = "Hash", required = true, notes = "The hexadecimal value of the checksum.")
    private String hash;
}
