package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type specifies the identifier of an existing VNF instance of the NS instance to be reverted and the identifier of an existing VNF Snapshot to be reverted to. It shall comply with the provisions defined in Table 6.5.3.75-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class RevertVnfToSnapshotData   {

  private String vnfInstanceId;

  private String vnfSnapshotInfoId;

  private Object additionalParams;
}

