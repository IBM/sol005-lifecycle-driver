package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information about a PNF that is part of an NS instance.  It shall comply with the provisions defined in Table 6.5.3.13-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class PnfInfo   {

  private String pnfId;

  private String pnfName;

  private String pnfdId;

  private String pnfdInfoId;

  private String pnfProfileId;

  private PnfExtCpInfo cpInfo;
}

