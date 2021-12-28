package org.etsi.sol005.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents an external VL. It shall comply with the provisions defined in Table 6.5.3.26-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExtVirtualLinkData   {

  private String extVirtualLinkId;

  private String vimId;

  private String resourceProviderId;

  private String resourceId;

  private List<VnfExtCpData> extCps = new ArrayList<>();

  private List<ExtLinkPortData> extLinkPorts = null;
}

