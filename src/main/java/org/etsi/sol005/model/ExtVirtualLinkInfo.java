package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * ExtVirtualLinkInfo
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExtVirtualLinkInfo   {
  private String id;

  private ResourceHandle resourceHandle;

  private List<ExtLinkPortInfo> extLinkPorts = null;

  private VnfExtCpData currentVnfExtCpData;
}

