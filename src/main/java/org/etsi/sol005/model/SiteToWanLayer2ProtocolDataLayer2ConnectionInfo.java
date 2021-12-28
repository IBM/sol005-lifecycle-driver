package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * Layer 2 protocol parameters of the connectivity service endpoint (CSE). 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer2ProtocolDataLayer2ConnectionInfo   {
  /**
   * The type of connection to be established on the connectivity service point. Permitted values: - CSE: defined by the characteristics of the existing referred connectivity service point. - AGGREGATE_CSE: create an aggregation of the connectivity service endpoints. 
   */
  public enum ConnectionTypeEnum {
    CSE("CSE"),
    
    AGGREGATE_CSE("AGGREGATE_CSE");

    private String value;

    ConnectionTypeEnum(String value) {
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
    public static ConnectionTypeEnum fromValue(String value) {
      for (ConnectionTypeEnum b : ConnectionTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ConnectionTypeEnum connectionType;

  /**
   * To indicate whether to create logical interfaces on the referred connectivity service endpoint or new aggregated connectivity service endpoint. Permitted values: - PARENT: use the mapped interface to the connectivity service endpoint as is, i.e., do not create logical interfaces. - LOGICAL: create logical interfaces. 
   */
  public enum InterfaceTypeEnum {
    PARENT("PARENT"),
    
    LOGICAL("LOGICAL");

    private String value;

    InterfaceTypeEnum(String value) {
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
    public static InterfaceTypeEnum fromValue(String value) {
      for (InterfaceTypeEnum b : InterfaceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private InterfaceTypeEnum interfaceType;

  /**
   * The type of frames to forward on the connectivity service point. Permitted values: - UNTAGGED: an interface where frames are not tagged. - TAGGED: an interface configured to forward tagged frames (i.e., enabled for VLAN tagging). 
   */
  public enum InterfaceTaggingEnum {
    UNTAGGED("UNTAGGED"),
    
    TAGGED("TAGGED");

    private String value;

    InterfaceTaggingEnum(String value) {
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
    public static InterfaceTaggingEnum fromValue(String value) {
      for (InterfaceTaggingEnum b : InterfaceTaggingEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private InterfaceTaggingEnum interfaceTagging;

  /**
   * The type of encapsulation. If the interfaceTagging=\"TAGGED\", either \"VLAN\" or \"VXLAN\" shall be set. Permitted values: - ETH: generic Ethernet encapsulation. - VLAN: encapsulation based on VLAN. - VXLAN: encapsulation based on VXLAN. 
   */
  public enum EncapsulationTypeEnum {
    ETH("ETH"),
    
    VLAN("VLAN"),
    
    VXLAN("VXLAN");

    private String value;

    EncapsulationTypeEnum(String value) {
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
    public static EncapsulationTypeEnum fromValue(String value) {
      for (EncapsulationTypeEnum b : EncapsulationTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private EncapsulationTypeEnum encapsulationType;

  /**
   * Type of encapsulation method for VLAN tagging. Shall be present if interfaceTagging=\"TAGGED\" and encapsulationType=\"VLAN\". Permitted values: - DOT1Q: used when packets on the CSE are encapsulated with one or a set of customer VLAN identifiers. - QINQ: used when packets on the CSE are encapsulated with multiple customer VLAN identifiers and a single   service VLAN identifier. - QINANY: used when packets on the CSE have no specific customer VLAN and a service VLAN identifier is used. 
   */
  public enum VlanTaggingTypeEnum {
    DOT1Q("DOT1Q"),
    
    QINQ("QINQ"),
    
    QINANY("QINANY");

    private String value;

    VlanTaggingTypeEnum(String value) {
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
    public static VlanTaggingTypeEnum fromValue(String value) {
      for (VlanTaggingTypeEnum b : VlanTaggingTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private VlanTaggingTypeEnum vlanTaggingType;

  private SiteToWanLayer2ProtocolDataLayer2ConnectionInfoWanSegmentIds wanSegmentIds;

  private SiteToWanLayer2ProtocolDataLayer2ConnectionInfoVxlanConfig vxlanConfig;

  private SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData lagInterfaceData;

  private Object layer2ControlProtocol;
}

