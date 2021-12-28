package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type defines the additional parameters for the VNF instance to be created associated with an NS instance. It shall comply with the provisions defined in Table 6.5.3.22-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ParamsForVnf   {

  private String vnfProfileId;

  private String vnfInstanceName;

  private String vnfInstanceDescription;

  private Object vnfConfigurableProperties;

  private Object metadata;

  private Object extensions;

  private Object additionalParams;
}

