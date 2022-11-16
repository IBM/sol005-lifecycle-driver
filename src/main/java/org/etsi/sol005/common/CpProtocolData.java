package org.etsi.sol005.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents network protocol data.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents network protocol data.")
public class CpProtocolData {

    @Schema(name = "Layer Protocol", required = true, description = "Identifier of layer(s) and protocol(s).")
    private LayerProtocol layerProtocol;
    @Schema(name = "Network Address Data", description = "Network address data for IP over Ethernet to assign to the extCP instance. Shall be present if layerProtocol is equal to \"IP_OVER_ETHERNET\", and shall be absent otherwise.")
    private IpOverEthernetAddressData ipOverEthernet;

}
