package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Links to resources related to this resource. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsLcmOpOccLinks   {

  private Link self;

  private Link nsInstance;

  private Link cancel;

  private Link retry;

  private Link rollback;

  private Link _continue;

  private Link fail;
}

