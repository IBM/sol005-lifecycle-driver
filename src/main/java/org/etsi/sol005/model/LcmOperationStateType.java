package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Value | Description ------|------------ PROCESSING | The LCM operation is currently in execution. COMPLETED | The LCM operation has been completed successfully. PARTIALLY_COMPLETED | The LCM operation has been partially completed with accepTable errors. FAILED_TEMP | The LCM operation has failed and execution has stopped, but the execution of the operation is not considered to be closed. FAILED | The LCM operation has failed and it cannot be retried or rolled back, as it is determined that such action will not succeed. OLLING_BACK | The LCM operation is currently being rolled back. ROLLED_BACK | The LCM operation has been successfully rolled back, i.e. The state of the NS prior to the original operation invocation has been restored as closely as possible. 
 */
public enum LcmOperationStateType {
  
  PROCESSING("PROCESSING"),
  
  COMPLETED("COMPLETED"),
  
  PARTIALLY_COMPLETED("PARTIALLY_COMPLETED"),
  
  FAILED_TEMP("FAILED_TEMP"),
  
  FAILED("FAILED"),
  
  ROLLING_BACK("ROLLING_BACK"),
  
  ROLLED_BACK("ROLLED_BACK");

  private String value;

  LcmOperationStateType(String value) {
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
  public static LcmOperationStateType fromValue(String value) {
    for (LcmOperationStateType b : LcmOperationStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

