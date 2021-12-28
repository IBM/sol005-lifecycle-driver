package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents information about an external CP of a VNF. It shall comply  with the provisions defined in Table 6.5.3.70-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfExtCpInfo   {

  private String id;

  private String cpdId;

  private String cpConfigId;

  private String vnfdId;

  private List<CpProtocolInfo> cpProtocolInfo = null;

  private String extLinkPortId;

  private Object metadata;

  private String associatedVnfcCpId;

  private String associatedVnfVirtualLinkId;
}

