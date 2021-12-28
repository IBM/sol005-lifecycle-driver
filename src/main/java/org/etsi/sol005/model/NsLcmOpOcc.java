package org.etsi.sol005.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents a request a NS lifecycle operation occurrence. It shall comply with the provisions defined in Table 6.5.2.3-1. 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsLcmOpOcc   {

  private String id;

  private NsLcmOperationStateType operationState;

  private OffsetDateTime statusEnteredTime = null;

  private String nsInstanceId;

  private NsLcmOpType lcmOperationType;

  private OffsetDateTime startTime = null;

  private Boolean isAutomaticInvocation;

  /**
   * Input parameters of the LCM operation. This attribute shall be formatted according to the request data type of the related LCM operation. The following mapping between lcmOperationType and the data type of this attribute shall apply: - INSTANTIATE: InstantiateNsRequest - SCALE: ScaleNsRequest - UPDATE: UpdateNsRequest - HEAL: HealNsRequest - TERMINATE: TerminateNsRequest This attribute shall be present if this data type is returned in a response to reading an individual resource, and may be present according to the chosen attribute selector parameter if this data type is returned in a response to a query of a container resource. 
   */
  public enum OperationParamsEnum {
    INSTANTIATE("INSTANTIATE"),
    
    SCALE("SCALE"),
    
    UPDATE("UPDATE"),
    
    HEAL("HEAL"),
    
    TERMINATE("TERMINATE");

    private String value;

    OperationParamsEnum(String value) {
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
    public static OperationParamsEnum fromValue(String value) {
      for (OperationParamsEnum b : OperationParamsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private Object operationParams;

  private Boolean isCancelPending;

  private CancelModeType cancelMode;

  private ProblemDetails error;

  private NsLcmOpOccResourceChanges resourceChanges;

  private NsLcmOpOccLinks links;
}

