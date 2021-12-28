package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type specifies the identifier of information of an available VNF Snapshot to be deleted and the identifier of the related VNF instance of the NS instance. It shall comply with the provisions defined in Table 6.5.3.74-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class DeleteVnfSnapshotData   {

  private String vnfInstanceId;

  private String vnfSnapshotInfoId;
}

