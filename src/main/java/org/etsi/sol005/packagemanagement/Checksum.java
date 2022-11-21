package org.etsi.sol005.packagemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents the checksum of a VNF package or an artifact file.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents the checksum of a VNF package or an artifact file.")
public class Checksum {

    @Schema(name = "Algorithm", required = true, description = "Name of the algorithm used to generate the checksum,as defined in ETSI GS NFV-SOL 004 [2]. For example,SHA-256, SHA-512.")
    private String algorithm;
    @Schema(name = "Hash", required = true, description = "The hexadecimal value of the checksum.")
    private String hash;
}
