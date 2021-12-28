package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration PnfdOnboardingStateType shall comply with the provisions defined in Table 5.5.4.6-1 of GS-NFV SOL005. It indicates the on-boarding state of the individual PNF descriptor resource. CREATED = The PNF descriptor resource has been created. UPLOADING = The associated PNFD content is being uploaded. PROCESSING = The associated PNFD content is being processed, e.g. validation. ONBOARDED = The associated PNFD content has been on-boarded. ERROR = There was an error during upload or processing of the associated PNFD content. 
 */
public enum PnfdOnboardingStateType {
  
  CREATED("CREATED"),
  
  UPLOADING("UPLOADING"),
  
  PROCESSING("PROCESSING"),
  
  ONBOARDED("ONBOARDED"),
  
  ERROR("ERROR");

  private String value;

  PnfdOnboardingStateType(String value) {
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
  public static PnfdOnboardingStateType fromValue(String value) {
    for (PnfdOnboardingStateType b : PnfdOnboardingStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

