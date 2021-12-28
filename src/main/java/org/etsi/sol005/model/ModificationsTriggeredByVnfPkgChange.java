package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents attribute modifications that were performed on an \&quot;Individual VNF instance\&quot; resource when changing the current VNF package. The attributes that can be included consist of those requested to be modified explicitly in the \&quot;ChangeCurrentVnfPkgRequest\&quot; data structure, and additional attributes of the \&quot;VnfInstance\&quot; data structure that were modified implicitly during the operation. The \&quot;ModificationsTriggeredByVnfPkgChange\&quot; data type shall comply with the provisions defined in table 6.5.3.79-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ModificationsTriggeredByVnfPkgChange   {
  private Object vnfConfigurableProperties;

  private Object metadata;

  private Object extensions;

  private String vnfdId;

  private String vnfProvider;

  private String vnfProductName;

  private String vnfSoftwareVersion;

  private String vnfdVersion;
}

