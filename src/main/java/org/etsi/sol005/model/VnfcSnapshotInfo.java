package org.etsi.sol005.model;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents a VNFC Snapshot. It shall comply with the provisions defined in table 6.5.3.77-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfcSnapshotInfo   {

  private String id;

  private String vnfcInstanceId;

  private OffsetDateTime creationStartedAt = null;

  private OffsetDateTime creationFinishedAt = null;

  private String vnfcResourceInfoId;

  private ResourceHandle computeSnapshotResource;

  private List<VnfcSnapshotInfoStorageSnapshotResources> storageSnapshotResources = null;

  private Object userDefinedData;
}

