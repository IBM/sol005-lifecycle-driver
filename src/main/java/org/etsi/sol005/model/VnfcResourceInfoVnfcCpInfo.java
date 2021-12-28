package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * VnfcResourceInfoVnfcCpInfo
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class VnfcResourceInfoVnfcCpInfo   {

  private String id;

  private String cpdId;

  private String vnfExtCpId;

  private List<CpProtocolInfo> cpProtocolInfo = null;

  private String vnfLinkPortId;

  private Object metadata;
}

