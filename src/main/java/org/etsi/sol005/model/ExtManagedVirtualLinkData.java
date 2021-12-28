package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents an externally-managed internal VL. It shall comply with the provisions defined in Table 6.5.3.27-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExtManagedVirtualLinkData   {

  private String extManagedVirtualLinkId;

  private String vnfVirtualLinkDescId;

  private String vimId;

  private String resourceProviderId;

  private String resourceId;

  private List<VnfLinkPortData> vnfLinkPort = null;

  private String extManagedMultisiteVirtualLinkId;
}

