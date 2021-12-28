package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents the information related to a SAP of a NS. The InstantiateVnfData data type specifies the parameters that are needed for VNF instantiation. This information element is used for the bottom-up NS creation when the OSS/BSS explicitly requests VNF instantiation for a given NS. When the NFVO invokes the Instantiate VNF update operation, a set of these parameters are then passed by the NFVO to the VNFM. It shall comply with the provisions defined in Table 6.5.3.24-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class InstantiateVnfData   {
  private String vnfdId;

  private String vnfFlavourId;

  private String vnfInstantiationLevelId;

  private String vnfInstanceName;

  private String vnfInstanceDescription;

  private List<ExtVirtualLinkData> extVirtualLinks = null;

  private List<ExtManagedVirtualLinkData> extManagedVirtualLinks = null;

  private String localizationLanguage;

  private Object vnfConfigurableProperties;

  private Object additionalParams;

  private Object metadata;

  private Object extensions;

  private VnfLocationConstraint locationConstraints;
}

