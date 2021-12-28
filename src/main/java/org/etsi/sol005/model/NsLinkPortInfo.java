package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents information about a link port of a VL instance. It shall comply with the provisions defined in Table 6.5.3.55-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsLinkPortInfo   {

  private String id;

  private ResourceHandle resourceHandle;

  private List<NsCpHandle> nsCpHandle = null;
}

