package org.etsi.sol005.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * Defines a static route. It shall only be present if the routingProtocol&#x3D;\&quot;STATIC\&quot;. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer3ProtocolDataRoutingInfoStaticRouting   {
  /**
   * The IP version applicable to the routing entry. Permitted values: - IPV4 - IPV6 
   */
  public enum IpVersionEnum {
    IPV4("IPV4"),
    
    IPV6("IPV6");

    private String value;

    IpVersionEnum(String value) {
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
    public static IpVersionEnum fromValue(String value) {
      for (IpVersionEnum b : IpVersionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private IpVersionEnum ipVersion;

  private String ipPrefix;

  private BigDecimal prefixSize;

  private String nextHop;
}

