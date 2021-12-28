package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents an identifier of the CP or SAP instance.  It shall comply with the provisions defined in Table 6.5.3.56-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsCpHandle   {
  private String vnfInstanceId;

  private String vnfExtCpInstanceId;

  private String pnfInfoId;

  private String pnfExtCpInstanceId;

  private String nsInstanceId;

  private String nsSapInstanceId;
}

