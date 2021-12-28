package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents an externally provided link port to be used to connect a VNFC connection point to an externally-managed VL. It shall comply with the provisions defined in table 6.5.3.78-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfLinkPortData   {

  private String vnfLinkPortId;

  private ResourceHandle resourceHandle;
}

