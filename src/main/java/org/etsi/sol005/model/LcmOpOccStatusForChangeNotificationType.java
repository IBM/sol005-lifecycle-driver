package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration LcmOpOccStatusForChangeNotificationType represents the status of the lifecycle management operation occurrence that impacts the NS component and triggers an NS change notification. It shall comply with the provisions defined in Table 6.5.4.7-1. Value | Description ------|------------ START | The impact on the NS component is identified. COMPLETED | The impact on the NS component stops and related lifecycle operation completes successfully. PARTIALLY_COMPLETED | The impact on the NS component stops and related lifecycle operation partially completes. Inconsistency state may exist on the NS       component. FAILED | The impact on the NS component stops and related lifecycle operation fails. Inconsistency state may exist for the NS component. ROLLED_BACK | The impact on the NS component stops and related lifecycle operation is rolled back. 
 */
public enum LcmOpOccStatusForChangeNotificationType {
  
  START("START"),
  
  COMPLETED("COMPLETED"),
  
  PARTIALLY_COMPLETED("PARTIALLY_COMPLETED"),
  
  FAILED("FAILED"),
  
  ROLLED_BACK("ROLLED_BACK");

  private String value;

  LcmOpOccStatusForChangeNotificationType(String value) {
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
  public static LcmOpOccStatusForChangeNotificationType fromValue(String value) {
    for (LcmOpOccStatusForChangeNotificationType b : LcmOpOccStatusForChangeNotificationType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

