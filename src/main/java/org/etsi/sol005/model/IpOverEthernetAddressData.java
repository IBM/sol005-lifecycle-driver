package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

//import org.etsi.sol005.model.OneOfAnyTypeAnyTypeAnyType;

/**
 * This type represents network address data for IP over Ethernet. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class IpOverEthernetAddressData   {
  private String macAddress;

  private String segmentationId;

  private List<Object> ipAddresses = null;
}

