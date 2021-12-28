package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type provides information about Layer 2 protocol specific information for the configuration of the MSCS over the WAN. It shall comply with the provisions defined in Table 6.5.3.88-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class WanLayer2ProtocolData   {
  /**
   * Type of underlying connectivity service and protocol associated to the type of MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. - EVPN_VPWS: as specified in IETF RFC 8214. - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. - VPLS_LDP_L2TP: as specified in IETF RFC 4762 and IETF RFC 6074. - VPWS_LDP_L2TP: as specified in IETF RFC 6074. 
   */
  public enum MscsLayer2ProtocolEnum {
    EVPN_BGP_MPLS("EVPN_BGP_MPLS"),
    
    EVPN_VPWS("EVPN_VPWS"),
    
    VPLS_BGP("VPLS_BGP"),
    
    VPLS_LDP_L2TP("VPLS_LDP_L2TP"),
    
    VPWS_LDP_L2TP("VPWS_LDP_L2TP");

    private String value;

    MscsLayer2ProtocolEnum(String value) {
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
    public static MscsLayer2ProtocolEnum fromValue(String value) {
      for (MscsLayer2ProtocolEnum b : MscsLayer2ProtocolEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private MscsLayer2ProtocolEnum mscsLayer2Protocol;

  private Boolean isSegmentPreservation;

  private Boolean isSegmentCosPreservation;
}

