package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VirtualStorageResourceInfo   {

  private String id;

  private String virtualStorageDescId;

  private String vnfdId;

  private ResourceHandle storageResource;

  private String reservationId;

  private Object metadata;
}

