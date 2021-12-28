package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type provides information about the connectivity to the WAN of network resources realizing a VL, e.g., when the VL is deployed on several sites across a WAN. It shall comply with the provisions defined in table 6.5.3.90-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class WanConnectionInfo   {

  private String wanConnectionInfoId;

  private String nsVirtualLinkInfoId;

  private String vnfVirtualLinkResourceInfoId;

  private WanConnectionProtocolInfo protocolInfo;
}

