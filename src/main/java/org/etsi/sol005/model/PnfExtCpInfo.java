package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information about the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.17-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class PnfExtCpInfo   {

  private String cpInstanceId;

  private String cpdId;

  private List<CpProtocolData> cpProtocolData = null;
}

