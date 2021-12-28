package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents describes a group of CPs and/or SAPs pairs associated to  the same position in an NFP. It shall comply with the provisions defined in  Table 6.5.3.71-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CpGroupInfo   {

  private List<CpPairInfo> cpPairInfo = null;

  /**
   * Identifies a rule to apply to forward traffic to the ingress CPs or SAPs of  the group. Permitted values: * ALL = Traffic flows shall be forwarded simultaneously to all CPs or SAPs  of the group. * LB = Traffic flows shall be forwarded to one CP or SAP of the group selected  based on a loadbalancing algorithm. 
   */
  public enum ForwardingBehaviourEnum {
    ALL("ALL"),
    
    LB("LB");

    private String value;

    ForwardingBehaviourEnum(String value) {
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
    public static ForwardingBehaviourEnum fromValue(String value) {
      for (ForwardingBehaviourEnum b : ForwardingBehaviourEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ForwardingBehaviourEnum forwardingBehaviour;

  private ForwardingBehaviourInputParameters forwardingBehaviourInputParameters;
}

