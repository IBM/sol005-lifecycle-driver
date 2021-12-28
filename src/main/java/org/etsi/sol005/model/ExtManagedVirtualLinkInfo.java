package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * ExtManagedVirtualLinkInfo
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExtManagedVirtualLinkInfo   {

  private String id;

  private String vnfdId;

  private String vnfVirtualLinkDescId;

  private ResourceHandle networkResource;

  private List<VnfLinkPortInfo> vnfLinkPorts = null;

  private String extManagedMultisiteVirtualLinkId;
}

