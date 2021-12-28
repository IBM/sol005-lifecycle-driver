package org.etsi.sol005.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type provides encapsulates information about an MSCS endpoint of the MSCS. It shall comply with the provisions defined in table 6.5.3.83-1. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class MscsEndpointInfo   {
  private String mscsEndpointId;

  /**
   * Directionality of the data traffic in the context of the terminating MSCS endpoint from WAN’s perspective. Permitted values: - INBOUND: to indicate into the WAN. - OUTBOUND: to indicate from the WAN. - BOTH: to indicate bidirectional data traffic to/from the WAN. 
   */
  public enum DirectionalityEnum {
    INBOUND("INBOUND"),
    
    OUTBOUND("OUTBOUND"),
    
    BOTH("BOTH");

    private String value;

    DirectionalityEnum(String value) {
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
    public static DirectionalityEnum fromValue(String value) {
      for (DirectionalityEnum b : DirectionalityEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private DirectionalityEnum directionality;

  private List<String> connectivityServiceEndpoinId = new ArrayList<>();
}

