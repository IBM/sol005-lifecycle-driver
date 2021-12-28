package org.etsi.sol005.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type provides information about Layer 3 protocol specific information for the stitching of the intra-site VN to the multi-site connectivity service over the WAN. It shall comply with the provisions defined in Table 6.5.3.86-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer3ProtocolData   {

  private SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress logicalInterfaceIpAddress;

  private SiteToWanLayer3ProtocolDataRoutingInfo routingInfo;

  private BigDecimal mtuL3;

  private SiteToWanLayer3ProtocolDataVirtualRoutingAndForwarding virtualRoutingAndForwarding;

  private Object bfdConfig;
}

