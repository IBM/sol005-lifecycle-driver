package org.etsi.sol005.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents an externally provided link port or network address information
 * per instance of an external connection point. In case a link port is
 * provided, the VNFM shall use that link port when connecting the external CP
 * to the external VL. In a link port is not provided, the VNFM shall create a
 * link port on the external VL, and use that link port to connect the external
 * CP to the external VL.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents an externally provided link port or network address information per instance of an external connection point. In case a link port is provided, the VNFM shall use that link port when connecting the external CP to the external VL. In a link port is not provided, the VNFM shall create a link port on the external VL, and use that link port to connect the external CP to the external VL.")
public class VnfExtCpConfig {

    @Schema(name = "CP Instance Id", description = "Identifier of the external CP instance to which this set of configuration parameters is requested to be applied. Shall be present if this instance has already been created.")
    private String cpInstanceId;
    @Schema(name = "Link Port Id", description = "Identifier of a pre-configured link port to which the external CP will be associated.")
    private String linkPortId;
    @Schema(name = "CP Protocol Configuration", description = "Parameters for configuring the network protocols on the link port that connects the CP to a VL.")
    private List<CpProtocolData> cpProtocolData;

}
