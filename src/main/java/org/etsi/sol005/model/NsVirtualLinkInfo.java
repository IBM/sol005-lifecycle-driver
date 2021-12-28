package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type specifies the information about an NS VL instance.  It shall comply with the provisions defined in Table 6.5.3.53-1 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsVirtualLinkInfo   {

  private String id;

  private String nsVirtualLinkDescId;

  private String nsVirtualLinkProfileId;

  private List<ResourceHandle> resourceHandle = null;

  private List<NsLinkPortInfo> linkPort = null;
}

