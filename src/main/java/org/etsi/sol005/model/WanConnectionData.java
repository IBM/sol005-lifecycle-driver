package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type provides information used to connect the comprising network resources realizing a VL, e.g., when the VL is deployed on several sites and across a WAN. It shall comply with the provisions defined in table 6.5.3.80-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class WanConnectionData   {

  private Object nsVirtualLink;

  private Object vnfVirtualLink;

  private WanConnectionProtocolData protocolData;
}

