package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration LcmOpOccNotificationVerbosityType provides values to control the verbosity of LCM operation occurrence notifications. It shall comply with the provisions defined in table 6.5.4.11-1. * FULL: This signals a full notification which contains all change details. * SHORT: This signals a short notification which omits large-volume change details to reduce the size of data to          be sent via the notification mechanism. 
 */
public enum LcmOpOccNotificationVerbosityType {
  
  FULL("FULL"),
  
  SHORT("SHORT");

  private String value;

  LcmOpOccNotificationVerbosityType(String value) {
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
  public static LcmOpOccNotificationVerbosityType fromValue(String value) {
    for (LcmOpOccNotificationVerbosityType b : LcmOpOccNotificationVerbosityType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

