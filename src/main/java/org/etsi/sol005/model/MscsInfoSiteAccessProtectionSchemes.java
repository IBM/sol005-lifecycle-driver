package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * MscsInfoSiteAccessProtectionSchemes
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class MscsInfoSiteAccessProtectionSchemes   {
  private LocationConstraints locationConstraints;

  /**
   * Defines the protection scheme. Permitted values: - UNPROTECTED: to indicate no protection. - ONE_TO_ONE: to indicate an active-passive access protection. - ONE_PLUS_ONE: to indicate an active-active access protection. - ONE_TO_N: to indicate an N active to 1 passive access protection. 
   */
  public enum ProtectionSchemeEnum {
    UNPROTECTED("UNPROTECTED"),
    
    ONE_TO_ONE("ONE_TO_ONE"),
    
    ONE_PLUS_ONE("ONE_PLUS_ONE"),
    
    ONE_TO_N("ONE_TO_N");

    private String value;

    ProtectionSchemeEnum(String value) {
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
    public static ProtectionSchemeEnum fromValue(String value) {
      for (ProtectionSchemeEnum b : ProtectionSchemeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ProtectionSchemeEnum protectionScheme;
}

