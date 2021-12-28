package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Defines parameters for BGP routing. It shall only be present if the routingProtocol&#x3D;\&quot;BGP\&quot;. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting   {

  private Object bgpAs;

  private String bgpNeighbour;

  private Object bgpAdditionalParam;
}

