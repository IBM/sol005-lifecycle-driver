package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents a monitoring parameter that is tracked by the VNFM, for example,  for auto-scaling purposes. It shall comply with the provisions defined in Table 6.5.3.69-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfMonitoringParameter   {

  private String id;

  private String vnfdId;

  private String name;

  private String performanceMetric;
}

