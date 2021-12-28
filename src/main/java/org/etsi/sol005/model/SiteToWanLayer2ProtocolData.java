package org.etsi.sol005.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type provides information about Layer 2 protocol specific information for the configuration of the NFVI-PoP network gateway to enable the stitching of the intra-site VN to the MSCS over the WAN. It shall comply with the provisions defined in Table 6.5.3.85-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer2ProtocolData   {

  private SiteToWanLayer2ProtocolDataLayer2ConnectionInfo layer2ConnectionInfo;

  private BigDecimal mtuL2;

  private SiteToWanLayer2ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding;

  private Object forwardingConfig;
}

