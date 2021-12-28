package org.etsi.sol005.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents provides input parameters to configure the forwarding behaviour.  It shall comply with the provisions defined in Table 6.5.3.73-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ForwardingBehaviourInputParameters   {
  /**
   * May be included if forwarding behaviour is equal to LB. Shall not be included otherwise. Permitted values: * ROUND_ROBIN * LEAST_CONNECTION * LEAST_TRAFFIC * LEAST_RESPONSE_TIME * CHAINED_FAILOVER * SOURCE_IP_HASH * SOURCE_MAC_HASH 
   */
  public enum AlgortihmNameEnum {
    ROUND_ROBIN("ROUND_ROBIN"),
    
    LEAST_CONNECTION("LEAST_CONNECTION"),
    
    LEAST_TRAFFIC("LEAST_TRAFFIC"),
    
    LEAST_RESPONSE_TIME("LEAST_RESPONSE_TIME"),
    
    CHAINED_FAILOVER("CHAINED_FAILOVER"),
    
    SOURCE_IP_HASH("SOURCE_IP_HASH"),
    
    SOURCE_MAC_HASH("SOURCE_MAC_HASH");

    private String value;

    AlgortihmNameEnum(String value) {
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
    public static AlgortihmNameEnum fromValue(String value) {
      for (AlgortihmNameEnum b : AlgortihmNameEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private AlgortihmNameEnum algortihmName;

  private List<Integer> algorithmWeights = null;
}

