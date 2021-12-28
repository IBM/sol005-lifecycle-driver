package org.etsi.sol005.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents a VNF Snapshot. It shall comply with the provisions defined in table 6.5.2.18-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfSnapshot   {

  private String id;

  private String vnfInstanceId;

  private OffsetDateTime creationStartedAt = null;

  private OffsetDateTime creationFinishedAt = null;

  private String vnfdId;

  private VnfInstance vnfInstance;

  private List<VnfcSnapshotInfo> vnfcSnapshots = new ArrayList<>();

  private Object userDefinedData;
}

