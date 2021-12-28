package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * * FORCEFUL: The VNFM will stop the VNF immediately after accepting the   request. * GRACEFUL: The VNFM will first arrange to take the VNF out of service   after accepting the request. Once that operation is successful or once   the timer value specified in the \"gracefulStopTimeout\" attribute   expires, the VNFM will stop the VNF. 
 */
public enum StopType {
  
  FORCEFUL("FORCEFUL"),
  
  GRACEFUL("GRACEFUL");

  private String value;

  StopType(String value) {
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
  public static StopType fromValue(String value) {
    for (StopType b : StopType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

