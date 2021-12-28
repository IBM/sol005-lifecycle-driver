package org.etsi.sol005.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Information specific to an instantiated VNF instance. This attribute shall be present if the instantiateState attribute value is INSTANTIATED. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfInstanceInstantiatedVnfInfo   {

  private String flavourId;

  private VnfOperationalStateType vnfState;

  private List<VnfScaleInfo> scaleStatus = null;

  private List<VnfScaleInfo> maxScaleLevels = null;

  private List<VnfExtCpInfo> extCpInfo = new ArrayList<>();

  private List<ExtVirtualLinkInfo> extVirtualLinkInfo = null;

  private List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo = null;

  private List<VnfMonitoringParameter> monitoringParameters = null;

  private String localizationLanguage;

  private List<VnfcResourceInfo> vnfcResourceInfo = null;

  private List<VnfVirtualLinkResourceInfo> virtualLinkResourceInfo = null;

  private List<VirtualStorageResourceInfo> virtualStorageResourceInfo = null;
}

