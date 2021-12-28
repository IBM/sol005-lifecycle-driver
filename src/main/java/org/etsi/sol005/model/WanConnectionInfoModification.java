package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents attribute modifications that were performed on WAN connection information. The attributes that can be included consist of those requested to be modified explicitly with the \&quot;UpdateNsRequest\&quot; data structure. It shall comply with the provisions defined in table 6.5.3.93-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class WanConnectionInfoModification   {

  private String wanConnectionInfoId;

  private String mscsName;

  private String mscsDescription;

  private List<MscsEndpointInfo> mscsEndpoints = null;

  private List<String> removeMscsEndpointIds = null;

  private List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints = null;

  private List<String> removeConnectivityServiceEndpoints = null;
}

