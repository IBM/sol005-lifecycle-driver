package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents defines the information to scale a VNF instance  to a given level, or to scale a VNF instance by steps. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ScaleVnfData   {

  private String vnfInstanceId;

  /**
   * Type of the scale VNF operation requested. Allowed values are: - SCALE_OUT - SCALE_IN - SCALE_TO_INSTANTIATION_LEVEL - SCALE_TO_SCALE_LEVEL(S) The set of types actually supported depends on the capabilities of the VNF being managed. 
   */
  public enum ScaleVnfTypeEnum {
    OUT("SCALE_OUT"),
    
    IN("SCALE_IN"),
    
    TO_INSTANTIATION_LEVEL("SCALE_TO_INSTANTIATION_LEVEL"),
    
    TO_SCALE_LEVEL_S_("SCALE_TO_SCALE_LEVEL(S)");

    private String value;

    ScaleVnfTypeEnum(String value) {
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
    public static ScaleVnfTypeEnum fromValue(String value) {
      for (ScaleVnfTypeEnum b : ScaleVnfTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ScaleVnfTypeEnum scaleVnfType;

  private ScaleToLevelData scaleToLevelData;

  private ScaleByStepData scaleByStepData;
}

