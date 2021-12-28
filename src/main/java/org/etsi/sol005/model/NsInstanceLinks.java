package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Links to resources related to this resource.
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsInstanceLinks   {

  private Link self;

  private List<Link> nestedNsInstances = null;

  private List<Link> vnfSnapshotInfos = null;

  private Link instantiate;

  private Link terminate;

  private Link update;

  private Link scale;

  private Link heal;
}

