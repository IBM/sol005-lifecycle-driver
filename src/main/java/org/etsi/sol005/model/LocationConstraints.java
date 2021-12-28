package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents location constraints for a VNF to be instantiated. The location constraints can be represented as follows: • as a country code • as a civic address combined with a country code • as an area, conditionally combined with a country code The LocationConstraints data type shall comply with the provisions defined in Table 6.5.3.21-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class LocationConstraints   {
  private String countryCode;

  private List<LocationConstraintsCivicAddressElement> civicAddressElement = null;

  private Object area;
}

