package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * The routing information that is activated on the connectivity service endpoint. More than one \&quot;routingInfo\&quot; is allowed to enable stacking different routing protocols (e.g., one routing protocol for IPv4 and another one for IPv6). 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer3ProtocolDataRoutingInfo   {
  /**
   * The routing protocol that is activated on the connectivity service endpoint. Permitted values: - BGP: used for dynamic routing BGPv4. - RIP: used for dynamic routing RIPv2. - OSPF: used for dynamic routing (OSPF version 2 for IPv4; and OSPF version 3 for IPv6). - STATIC: used for static routing. - DIRECT: used when the NFVI-PoP network is directly connected to the WAN provider network. - VRRP: used when the NFVI-PoP network is directly connected to the WAN provider network with virtual   router redundancy protocol support (VRRP). 
   */
  public enum RoutingProtocolEnum {
    BGP("BGP"),
    
    RIP("RIP"),
    
    OSPF("OSPF"),
    
    STATIC("STATIC"),
    
    DIRECT("DIRECT"),
    
    VRRP("VRRP");

    private String value;

    RoutingProtocolEnum(String value) {
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
    public static RoutingProtocolEnum fromValue(String value) {
      for (RoutingProtocolEnum b : RoutingProtocolEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RoutingProtocolEnum routingProtocol;

  private SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting staticRouting;

  /**
   * The IP version applicable to the dynamic routing protocol. Shall be present for dynamic routing protocols. Permitted values: - IPV4 - IPV6 
   */
  public enum RoutingAddressFamilyEnum {
    IPV4("IPV4"),
    
    IPV6("IPv6");

    private String value;

    RoutingAddressFamilyEnum(String value) {
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
    public static RoutingAddressFamilyEnum fromValue(String value) {
      for (RoutingAddressFamilyEnum b : RoutingAddressFamilyEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RoutingAddressFamilyEnum routingAddressFamily;

  private SiteToWanLayer3ProtocolDataRoutingInfoOspfRouting ospfRouting;

  private SiteToWanLayer3ProtocolDataRoutingInfoBgpRouting bgpRouting;

  private SiteToWanLayer3ProtocolDataRoutingInfoRouteMapsDistribution routeMapsDistribution;
}

