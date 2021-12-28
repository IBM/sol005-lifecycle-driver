package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Segment identifiers to pass on the tagged interface. Shall be present if encapsulationType&#x3D;\&quot;VLAN\&quot; or “VXLAN\&quot;. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer2ProtocolDataLayer2ConnectionInfoWanSegmentIds   {

  private String wanSegmentIdValue;

  private String wanSegmentIdUpperRange;
}

