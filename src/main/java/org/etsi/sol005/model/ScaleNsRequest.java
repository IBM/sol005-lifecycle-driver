package org.etsi.sol005.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents a request for the scale NS operation. Either the parameter scaleNsData or the parameter scaleVnfData, but not both shall be provided 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ScaleNsRequest   {
  /**
   * Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF 
   */
  public enum ScaleTypeEnum {
    NS("SCALE_NS"),
    
    VNF("SCALE_VNF");

    private String value;

    ScaleTypeEnum(String value) {
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
    public static ScaleTypeEnum fromValue(String value) {
      for (ScaleTypeEnum b : ScaleTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ScaleTypeEnum scaleType;

  private ScaleNsData scaleNsData;

  private List<ScaleVnfData> scaleVnfData = null;

  private OffsetDateTime scaleTime = null;
}

