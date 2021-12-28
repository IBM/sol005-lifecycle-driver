package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type provides protocol specific information about the connectivity to the WAN of network resources realizing a VL, e.g., when the VL is deployed on several sites and across a WAN, and the related multi-site connectivity service (MSCS) enabling the connectivity through the WAN. This type supports providing information about both pre-provisioned WAN connectivity realized by external entities to NFV-MANO but parts of such connectivity is known to the NFVO, as well as information about MSCS created under NFV-MANO responsibility (i.e., connectivity is realized when NFVO communicates with the WIM).  It shall comply with the provisions defined in table 6.5.3.91-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class WanConnectionProtocolInfo   {
  private MscsInfo mscsInfo;
  private List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints = null;
}

