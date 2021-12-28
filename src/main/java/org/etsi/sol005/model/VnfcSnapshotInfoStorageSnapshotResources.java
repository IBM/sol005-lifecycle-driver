package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * VnfcSnapshotInfoStorageSnapshotResources
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfcSnapshotInfoStorageSnapshotResources   {

  private String storageResourceId;

  private ResourceHandle storageSnapshotResources;
}

