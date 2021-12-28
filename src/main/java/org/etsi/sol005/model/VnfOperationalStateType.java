package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets VnfOperationalStateType
 */
public enum VnfOperationalStateType {
  
  STARTED("STARTED"),
  
  STOPPED("STOPPED");

  private String value;

  VnfOperationalStateType(String value) {
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
  public static VnfOperationalStateType fromValue(String value) {
    for (VnfOperationalStateType b : VnfOperationalStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

