package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents the information used to heal a NS.  It shall comply with the provisions defined in Table 6.5.3.43-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class HealNsData   {
  /**
   * Indicates the degree of healing. Possible values include: - HEAL_RESTORE: Complete the healing of the NS restoring the state of the NS before the failure occurred - HEAL_QOS: Complete the healing of the NS based on the newest QoS values - HEAL_RESET: Complete the healing of the NS resetting to the original instantiation state of the NS - PARTIAL_HEALING 
   */
  public enum DegreeHealingEnum {
    HEAL_RESTORE("HEAL_RESTORE"),
    
    HEAL_QOS("HEAL_QOS"),
    
    HEAL_RESET("HEAL_RESET"),
    
    PARTIAL_HEALING("PARTIAL_HEALING");

    private String value;

    DegreeHealingEnum(String value) {
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
    public static DegreeHealingEnum fromValue(String value) {
      for (DegreeHealingEnum b : DegreeHealingEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private DegreeHealingEnum degreeHealing;

  private List<String> actionsHealing = null;

  private String healScript;

  private Object additionalParamsforNs;
}

