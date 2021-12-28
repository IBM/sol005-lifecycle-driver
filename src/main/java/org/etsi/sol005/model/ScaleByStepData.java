package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type describes the information to scale a VNF instance by steps.  The NFVO shall then invoke the Scale VNF operation towards the appropriate VNFM. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ScaleByStepData   {

  private String aspectId;

  private Integer numberOfSteps = 1;

  private Object additionalParams;
}

