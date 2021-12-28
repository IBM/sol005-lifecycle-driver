package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * VnfLinkPortInfo
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfLinkPortInfo   {

  private String id;

  private ResourceHandle resourceHandle;

  private String cpInstanceId;

  /**
   * Type of the CP instance that is identified by cpInstanceId. Shall be present if \"cpInstanceId\" is present, and shall be absent otherwise. Permitted values: * VNFC_CP: The link port is connected to a VNFC CP * EXT_CP: The link port is associated to an external CP. 
   */
  public enum CpInstanceTypeEnum {
    VNFC_CP("VNFC_CP"),
    
    EXT_CP("EXT_CP");

    private String value;

    CpInstanceTypeEnum(String value) {
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
    public static CpInstanceTypeEnum fromValue(String value) {
      for (CpInstanceTypeEnum b : CpInstanceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private CpInstanceTypeEnum cpInstanceType;
}

