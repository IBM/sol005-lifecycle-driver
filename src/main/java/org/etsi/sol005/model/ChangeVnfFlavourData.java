package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * The type represents the information that is requested to be changed deployment flavor for an existing VNF instance. It shall comply with the provisions defined in Table 6.5.3.25-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ChangeVnfFlavourData   {

  private String vnfInstanceId;

  private String newFlavourId;

  private String instantiationLevelId;

  private List<ExtVirtualLinkData> extVirtualLinks = null;

  private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

  private Object additionalParams;

  private Object extensions;

  private Object vnfConfigurableProperties;
}

