package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration PnfdUsageStateType shall comply with the provisions defined in Table 5.5.4.7-1 of GS NFV-SOL005. It indicates the usage state of the resource.IN-USE = The resource is in use.NOT_IN_USE = The resource is not-in-use. 
 */
public enum PnfdUsageStateType {
  
  IN_USE("IN_USE"),
  
  NOT_IN_USE("NOT_IN_USE");

  private String value;

  PnfdUsageStateType(String value) {
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
  public static PnfdUsageStateType fromValue(String value) {
    for (PnfdUsageStateType b : PnfdUsageStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

