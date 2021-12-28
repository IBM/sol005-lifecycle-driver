package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfcResourceInfo   {

  private String id;

  private String vnfdId;

  private String vduId;

  private ResourceHandle computeResource;

  private List<String> storageResourceIds = null;

  private String reservationId;

  private List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo = null;

  private Object metadata;
}

