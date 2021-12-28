package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.5-1 of GS NFV-SOL 005. It indicates the on-boarding state of the NSD. CREATED = The NSD information object has been created. UPLOADING = The associated NSD content is being uploaded. PROCESSING = The associated NSD content is being processed, e.g. validation. ONBOARDED = The associated NSD content has been on-boarded. ERROR = There was an error during upload or processing of the NSD content. 
 */
public enum NsdOnboardingStateType {
  
  CREATED("CREATED"),
  
  UPLOADING("UPLOADING"),
  
  PROCESSING("PROCESSING"),
  
  ONBOARDED("ONBOARDED"),
  
  ERROR("ERROR");

  private String value;

  NsdOnboardingStateType(String value) {
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
  public static NsdOnboardingStateType fromValue(String value) {
    for (NsdOnboardingStateType b : NsdOnboardingStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

