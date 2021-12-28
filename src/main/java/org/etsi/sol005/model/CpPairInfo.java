package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents describes a pair of ingress and egress CPs or SAPs which  the NFP passes by. It shall comply with the provisions defined in Table 6.5.3.72-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CpPairInfo   {

  private List<String> vnfExtCpIds = null;

  private List<String> pnfExtCpIds = null;

  private List<String> sapIds = null;
}

