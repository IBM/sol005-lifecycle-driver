package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents information about a link port of an external VL, i.e. a port providing connectivity for the VNF to an NS VL.  
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ExtLinkPortInfo   {

  private String id;

  private ResourceHandle resourceHandle;

  private String cpInstanceId;
}

