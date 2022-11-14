package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents a subnet defined as an IP address range.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a subnet defined as an IP address range.")
public class SubnetIpRange {

    @Schema(name = "Lowest IP Address", required = true, description = "Lowest IP address belonging to the range.")
    private String minAddress;
    @Schema(name = "Highest IP Address", required = true, description = "Highest IP address belonging to the range.")
    private String maxAddress;

}
