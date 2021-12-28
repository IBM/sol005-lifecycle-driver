package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsLcmOpOccResourceChanges   {

  private List<AffectedVnf> affectedVnfs = null;

  private List<AffectedPnf> affectedPnfs = null;

  private List<AffectedVirtualLink> affectedVls = null;

  private List<AffectedVnffg> affectedVnffgs = null;

  private List<AffectedNs> affectedNss = null;

  private List<AffectedSap> affectedSaps = null;
}

