package org.etsi.sol005.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents network protocol data.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents network protocol data.")
public class CpProtocolData {

    @ApiModelProperty(name = "Layer Protocol", required = true, notes = "Identifier of layer(s) and protocol(s).")
    private LayerProtocol layerProtocol;
    @ApiModelProperty(name = "Network Address Data", notes = "Network address data for IP over Ethernet to assign to the extCP instance. Shall be present if layerProtocol is equal to \"IP_OVER_ETHERNET\", and shall be absent otherwise.")
    private IpOverEthernetAddressData ipOverEthernet;

}
