package org.etsi.sol005.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * IndividualVNF package resource creation parameters, as defined in clause 9.5.2.2. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateVnfPkgInfoRequest   {

  private Map<String, String> userDefinedData;
}

