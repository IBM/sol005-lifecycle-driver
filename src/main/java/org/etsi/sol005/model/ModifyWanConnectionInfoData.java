package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents attribute modifications for WAN connection information. The attributes of the \&quot;WanConnectionInfo\&quot; that can be modified according to the provisions of the \&quot;UpdateNsRequest\&quot; in clause 6.5.2.12 related to WAN connection information are included in the \&quot;ModifyWanConnectionInfoData\&quot; data type. It shall comply with the provisions defined in table 6.5.3.92-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ModifyWanConnectionInfoData   {
  private String wanConnectionInfoId;

  private String mscsName;

  private String mscsDescription;

  private List<MscsEndpointInfo> mscsEndpoints = null;

  private List<String> removeMscsEndpointIds = null;

  private List<ConnectivityServiceEndpointInfo> connectivityServiceEndpoints = null;

  private List<String> removeConnectivityServiceEndpoints = null;
}

