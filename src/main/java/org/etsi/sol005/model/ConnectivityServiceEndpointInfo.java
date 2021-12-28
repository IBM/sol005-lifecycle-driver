package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type provides configuration data for the NFVI-PoP network gateway providing connectivity service endpoints. The connectivity service endpoints are used as endpoints by an MSCS. It shall comply with the provisions defined in Table 6.5.3.84-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ConnectivityServiceEndpointInfo   {

  private String connectivityServiceEndpointId;

  private String vimId;

  private SiteToWanLayer2ProtocolData siteToWanLayer2ProtocolData;

  private SiteToWanLayer3ProtocolData siteToWanLayer3ProtocolData;
}

