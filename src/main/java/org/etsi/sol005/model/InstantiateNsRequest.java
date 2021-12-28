package org.etsi.sol005.model;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * InstantiateNsRequest
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstantiateNsRequest   {
  private String nsFlavourId;

  private List<SapData> sapData = null;

  private List<AddPnfData> addpnfData = null;

  private List<VnfInstanceData> vnfInstanceData = null;

  private List<NestedNsInstanceData> nestedNsInstanceData = null;

  private List<VnfLocationConstraint> locationConstraints = null;

  private List<Object> nestedNslocationConstraints = null;

  private Map<String, Object> additionalParamsForNs;

  private List<ParamsForNestedNs> additionalParamForNestedNs = null;

  private List<ParamsForVnf> additionalParamsForVnf = null;

  private OffsetDateTime startTime = null;

  private String nsInstantiationLevelId;

  private List<WanConnectionData> wanConnectionData = null;

  private List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;
}

