package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

//import org.etsi.sol005.model.OneOfAnyTypeAnyType;

/**
 * This type represents information about a network address that has been assigned. It shall comply with the provisions defined in Table 6.5.3.18-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class IpOverEthernetAddressInfo   {
  private String macAddress;

  private String segmentationId;

  private List<Object> ipAddresses = null;

  /**
   * The type of the IP addresses 
   */
  public enum TypeEnum {
    PV4("PV4"),
    
    PV6("PV6");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  private String addresses;

  private Boolean isDynamic;

  private Object addressRange;

  private String minAddress;

  private String maxAddress;

  private String subnetId;
}

