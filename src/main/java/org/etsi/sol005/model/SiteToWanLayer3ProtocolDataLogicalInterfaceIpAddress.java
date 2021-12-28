package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * IP addressing information associated to a logical interface. Shall be present if the \&quot;interfaceType\&quot; of the SiteToWanLayer2ProtocolData is equal to \&quot;LOGICAL\&quot;. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer3ProtocolDataLogicalInterfaceIpAddress   {

  private String ipAddress;

  private String associatedSegmentId;
}

