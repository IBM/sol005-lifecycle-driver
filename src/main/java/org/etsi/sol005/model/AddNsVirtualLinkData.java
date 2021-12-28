package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This type specifies the parameters used for the creation of a new NsVirtualLink instance. It shall comply with the provisions defined in table 6.5.3.95-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddNsVirtualLinkData   {

  private String nsVirtualLinkProfileId;

}

