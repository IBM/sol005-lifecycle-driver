package org.etsi.sol005.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents configuration information for external CPs created from a CPD.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents configuration information for external CPs created from a CPD.")
public class VnfExtCpData {

    @ApiModelProperty(name = "CPD Id", required = true, notes = "The identifier of the CPD in the VNFD.")
    private String cpdId;
    @ApiModelProperty(name = "CP Configuration", required = true, notes = "List of instance data that need to be configured on the CP instances created from the respective CPD.")
    private List<VnfExtCpConfig> cpConfig;

}
