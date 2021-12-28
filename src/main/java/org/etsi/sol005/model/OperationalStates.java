package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * STARTED - The VNF instance is up and running. STOPPED - The VNF instance has been shut down. 
 */
public enum OperationalStates {
  
  STARTED("STARTED"),
  
  STOPPED("STOPPED");

  private String value;

  OperationalStates(String value) {
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
  public static OperationalStates fromValue(String value) {
    for (OperationalStates b : OperationalStates.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

