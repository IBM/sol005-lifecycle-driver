package org.etsi.sol005.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Information for setting up a LAG interface aggregating multiple connectivity service endpoints. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData   {

  private List<String> aggregatedEndpoints = new ArrayList<>();

  private Boolean lacpActivation;

  private Object lacpConfig;
}

