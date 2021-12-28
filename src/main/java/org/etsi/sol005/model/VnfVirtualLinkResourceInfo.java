package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfVirtualLinkResourceInfo   {

  private String id;

  private String vnfdId;

  private String vnfVirtualLinkDescId;

  private ResourceHandle networkResource;

  private String reservationId;

  private List<VnfLinkPortInfo> vnfLinkPorts = null;

  private Object metadata;
}

