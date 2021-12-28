package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information that is requested to be modified for a VNF instance. The information to be modified shall comply with the associated NSD. EXAMPLE. The vnfPkgId attribute value for a particular VNF instance can only be updated with a value that matches the identifier value of a VNF package whose vnfdId is present in the associated profile of the NSD. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ModifyVnfInfoData   {
  private String vnfInstanceId;

  private String vnfInstanceName;

  private String vnfInstanceDescription;

  private String vnfdId;

  private Object vnfConfigurableProperties;

  private Object metadata;

  private Object extensions;
}

