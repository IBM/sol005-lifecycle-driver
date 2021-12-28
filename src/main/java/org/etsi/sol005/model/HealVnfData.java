package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information to heal a VNF that is part of an NS.  The NFVO shall then invoke the Heal VNF operation towards the appropriate VNFM. It shall comply with the provisions defined in Table 6.5.3.44-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealVnfData   {
  private String vnfInstanceId;

  private String cause;

  private Object additionalParams;
}

