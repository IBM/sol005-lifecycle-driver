package org.etsi.sol005.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type provides configuration data for the creation of an MSCS. It shall comply with the provisions defined in Table 6.5.3.87-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class MscsConfigData   {
  /**
   * The type of connectivity that is requested to be provided to the virtualized networks in the NFVI-PoP and characterizes the connectivity service across the WAN. Permitted values: - L2 - L3 
   */
  public enum MscsTypeEnum {
    L2("L2"),
    
    L3("L3");

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

  private List<MscsConfigDataSiteAccessProtectionSchemes> siteAccessProtectionSchemes = null;

  private BigDecimal mtuMscs;

  private WanLayer2ProtocolData wanLayer2ProtocolData;

  private WanLayer3ProtocolData wanLayer3ProtocolData;
}

