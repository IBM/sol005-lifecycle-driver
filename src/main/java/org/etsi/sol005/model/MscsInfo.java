package org.etsi.sol005.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type provides information about an already provisioned multi-site connectivity service (MSCS) deployed across a WAN. It shall comply with the provisions defined in Table 6.5.3.82-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class MscsInfo   {
  private String mscsId;

  private String mscsName;

  private String mscsDescription;

  /**
   * The type of connectivity that is provided to the virtualized networks in the NFVI-PoP and characterizes the connectivity service across the WAN. Permitted values: - L2 - L3 
   */
  public enum MscsTypeEnum {
    L2VPN("L2VPN"),
    
    L3VPN("L3VPN");

    private String value;

    MscsTypeEnum(String value) {
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
    public static MscsTypeEnum fromValue(String value) {
      for (MscsTypeEnum b : MscsTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private MscsTypeEnum mscsType;

  /**
   * Type of underlying connectivity service and protocol associated to the MSCS. Permitted values are as listed below and restricted by the type of MSCS: - EVPN_BGP_MPLS: as specified in IETF RFC 7432. Only applicable for mscsType=\"L2\". - EVPN_VPWS: as specified in IETF RFC 8214. Only applicable for mscsType=\"L2\". - VPLS_BGP: as specified in IETF RFC 4761 and IETF RFC 6624. Only applicable for mscsType=\"L2\". - VPLS_LDP_L2TP: as specified in IETF RFC 4762. Only applicable for mscsType=\"L2\". - VPWS_LDP_L2TP: as specified in IETF RFC 6074. Only applicable for mscsType=\"L2\". - BGP_IP_VPN: BGP/MPLS based IP VPN as specified in IETF RFC 4364. Only applicable for mscsType=\"L3\". 
   */
  public enum MscsLayerProtocolEnum {
    EVPN_BGP_MPLS("EVPN_BGP_MPLS"),
    
    EVPN_VPWS("EVPN_VPWS"),
    
    VPLS_BGP("VPLS_BGP"),
    
    VPLS_LDP("VPLS_LDP"),
    
    VPWS("VPWS"),
    
    BGP_IP_VPN("BGP_IP_VPN");

    private String value;

    MscsLayerProtocolEnum(String value) {
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
    public static MscsLayerProtocolEnum fromValue(String value) {
      for (MscsLayerProtocolEnum b : MscsLayerProtocolEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private MscsLayerProtocolEnum mscsLayerProtocol;

  private List<MscsInfoSiteAccessProtectionSchemes> siteAccessProtectionSchemes = null;

  private BigDecimal mtuMscs;

  private List<MscsEndpointInfo> mscsEndpoints = null;
}

