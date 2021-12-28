package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type provides protocol specific information used to connect the comprising network resources realizing a VL, e.g., when the VL is deployed on several sites and across a WAN. This type supports signalling input information about both pre-provisioned WAN connectivity realized by external entities to NFV-MANO, as well as for the creation of MSCS under NFV-MANO responsibility (i.e., when connectivity is realized when NFVO communicates with the WIM). It shall comply with the provisions defined in table 6.5.3.81-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class WanConnectionProtocolData   {
  private MscsInfo mscsInfo;

  private List<ConnectivityServiceEndpointInfo> connectivityServiceEndpointConfigDatas = null;

  private MscsConfigData mscsConfigData;
}

