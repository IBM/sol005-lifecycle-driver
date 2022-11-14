package org.etsi.sol005.lifecyclemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.IpOverEthernetAddressData;
import org.etsi.sol005.common.LayerProtocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



import lombok.Data;

/**
 * Describes the protocol layer(s) that a CP uses together with protocol-related information, like addresses.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Describes the protocol layer(s) that a CP uses together with protocol-related information, like addresses.")
public class CpProtocolInfo {

    @Schema(name = "Layer Protocol", required = true, description = "The identifier of layer(s) and protocol(s) associated to the network address information.")
    private LayerProtocol layerProtocol;
    @Schema(name = "Network Address Data", description = "IP addresses over Ethernet to assign to the extCP instance. Shall be present if layerProtocol is equal to \" IP_OVER_ETHERNET\", and shall be absent otherwise.")
    private IpOverEthernetAddressData ipOverEthernet;

}
