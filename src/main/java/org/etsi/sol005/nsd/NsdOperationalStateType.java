package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED = The operational state of the resource is enabled. DISABLED = The operational state of the resource is disabled. 
 */
public enum NsdOperationalStateType {
  
  ENABLED("ENABLED"),
  
  DISABLED("DISABLED");

  private String value;


  NsdOperationalStateType(String value) {
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
  public static NsdOperationalStateType fromValue(String value) {
    for (NsdOperationalStateType b : NsdOperationalStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

