package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * The NfpRule data type is an expression of the conditions that shall be met in order for the NFP to be applicable to the packet. The condition acts as a flow classifier and it is met only if all the values expressed in the condition are matched by those in the packet. It shall comply with the provisions defined in Table 6.5.3.40-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NfpRule   {
  private String etherDestinationAddress;

  private String etherSourceAddress;

  /**
   * Human readable description for the VNFFG. 
   */
  public enum EtherTypeEnum {
    IPV4("IPV4"),
    
    IPV6("IPV6");

    private String value;

    EtherTypeEnum(String value) {
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
    public static EtherTypeEnum fromValue(String value) {
      for (EtherTypeEnum b : EtherTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private EtherTypeEnum etherType;

  private List<String> vlanTag = null;

  /**
   * Indicates the L4 protocol, For IPv4 [7] this corresponds to the field called \"Protocol\" to identify the next level protocol. For IPv6 [28] this corresponds to the field is called the \"Next Header\" field. Permitted values: Any keyword defined in the IANA protocol registry [1], e.g.: TCP UDP ICMP 
   */
  public enum ProtocolEnum {
    TCP("TCP"),
    
    UDP("UDP"),
    
    ICMP("ICMP");

    private String value;

    ProtocolEnum(String value) {
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
    public static ProtocolEnum fromValue(String value) {
      for (ProtocolEnum b : ProtocolEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ProtocolEnum protocol;

  private String dscp;

  private PortRange sourcePortRange;

  private PortRange destinationPortRange;

  private String sourceIpAddressPrefix;

  private String destinationIpAddressPrefix;

  private List<Mask> extendedCriteria = null;
}

