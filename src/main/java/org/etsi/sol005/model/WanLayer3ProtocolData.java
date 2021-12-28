package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of underlying connectivity service and protocol associated to the type of MSCS. Permitted values are as listed below and restricted by the type of MSCS: - BGP_IP_VPN: BGP/MPLS based IP VPN as specified in IETF RFC 4364. 
 */
public enum WanLayer3ProtocolData {
  
  BGP_IP_VPN("BGP_IP_VPN");

  private String value;

  WanLayer3ProtocolData(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static WanLayer3ProtocolData fromValue(String value) {
    for (WanLayer3ProtocolData b : WanLayer3ProtocolData.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

