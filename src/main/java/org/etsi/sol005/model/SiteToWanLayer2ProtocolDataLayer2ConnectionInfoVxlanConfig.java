package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * Additional configuration needed when using VXLAN encapsulation. Shall be present if interfaceTagging&#x3D;\&quot;TAGGED\&quot; and encapsulationType&#x3D;\&quot;VXLAN\&quot;. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig   {
  /**
   * Type of VXLAN access mode. Default value is \"STATIC\". Permitted values: - STATIC - BGP_EVPN 
   */
  public enum PeerModeEnum {
    STATIC("STATIC"),
    
    BGP_EVPN("BGP_EVPN");

    private String value;

    PeerModeEnum(String value) {
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
    public static PeerModeEnum fromValue(String value) {
      for (PeerModeEnum b : PeerModeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private PeerModeEnum peerMode;

  private List<String> peers = null;
}

