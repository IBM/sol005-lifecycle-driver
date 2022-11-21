package org.etsi.sol005.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents configuration information for external CPs created from a CPD.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents configuration information for external CPs created from a CPD.")
public class VnfExtCpData {

    @Schema(name = "CPD Id", required = true, description = "The identifier of the CPD in the VNFD.")
    private String cpdId;
    @Schema(name = "CP Configuration", required = true, description = "List of instance data that need to be configured on the CP instances created from the respective CPD.")
    private List<VnfExtCpConfig> cpConfig;

}
