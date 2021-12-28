package org.etsi.sol005.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type represents an SAP instance. It shall comply with the provisions defined in Table 6.5.3.67-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SapInfo   {

  private String id;

  private String sapdId;

  private String sapName;

  private String description;

  private List<CpProtocolInfo> sapProtocolInfo = new ArrayList<>();
}

