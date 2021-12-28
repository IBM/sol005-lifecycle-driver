package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information to scale a NS. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ScaleNsData   {

  private List<VnfInstanceData> vnfInstanceToBeAdded = null;

  private List<String> vnfInstanceToBeRemoved = null;

  private ScaleNsByStepsData scaleNsByStepsData;

  private ScaleNsToLevelData scaleNsToLevelData;

  private ParamsForVnf additionalParamsForNs;

  private List<ParamsForVnf> additionalParamsForVnf = null;

  private List<VnfLocationConstraint> locationConstraints = null;

  private List<NestedNsLocationConstraint> nestedNslocationConstraints = null;
}

