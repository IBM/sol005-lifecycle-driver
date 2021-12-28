package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsComponentType represents the NS component type. It shall comply with the provisions defined in Table 6.5.4.5-1. Value | Description ------|------------ VNF | Represents the impacted NS component is a VNF. PNF | Represents the impacted NS component is a PNF. NS | Represents the impacted NS component is a nested NS. 
 */
public enum NsComponentType {
  
  VNF("VNF"),
  
  PNF("PNF"),
  
  NS("NS");

  private String value;

  NsComponentType(String value) {
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
  public static NsComponentType fromValue(String value) {
    for (NsComponentType b : NsComponentType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

