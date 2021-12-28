package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the association of location constraints to a nested NS instance to be created according to a specific NS profile. It shall comply with the provisions defined in Table 6.5.3.96-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NestedNsLocationConstraint   {
  private String nsProfileId;

  private LocationConstraints locationConstraints;
}