package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents a response for Query NS operation.  It shall comply with the provisions defined in Table 6.5.2.10-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NsInstance   {
  private boolean topLevel = true;

  private String id;

  private String nsInstanceName;

  private String nsInstanceDescription;

  private String nsdId;

  private String nsdInfoId;

  private String flavourId;

  private List<VnfInstance> vnfInstance = null;

  private List<PnfInfo> pnfInfo = null;

  private List<NsVirtualLinkInfo> virtualLinkInfo = null;

  private List<VnffgInfo> vnffgInfo = null;

  private List<SapInfo> sapInfo = null;

  private List<String> nestedNsInstanceId = null;

  private List<String> vnfSnapshotInfoIds = null;

  /**
   * The state of the NS instance. Permitted values: NOT_INSTANTIATED: The NS instance is terminated or not instantiated. INSTANTIATED: The NS instance is instantiated. 
   */
  public enum NsStateEnum {
    NOT_INSTANTIATED("NOT_INSTANTIATED"),
    
    INSTANTIATED("INSTANTIATED");

    private String value;

    NsStateEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NsStateEnum fromValue(String value) {
      for (NsStateEnum b : NsStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private NsStateEnum nsState;

  private List<NsMonitoringParameter> monitoringParameter = null;

  private List<NsScaleInfo> nsScaleStatus = null;

  private List<AffinityOrAntiAffinityRule> additionalAffinityOrAntiAffinityRule = null;

  private List<WanConnectionInfo> wanConnectionInfo = null;

  private NsInstanceLinks links;
}

