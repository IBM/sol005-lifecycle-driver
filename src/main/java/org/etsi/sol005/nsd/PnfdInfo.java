package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.etsi.sol005.model.ProblemDetails;
import org.etsi.sol005.nsd.NsdInfoLinks;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.etsi.sol005.model.PnfdOnboardingStateType;
import org.etsi.sol005.model.PnfdUsageStateType;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * This type represents a response for the query PNFD operation. It shall comply with the provisions defined in table 5.5.2.5-1.
 */
@Schema(description = "This type represents a response for the query NSD operation. ")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PnfdInfo {
  @JsonProperty("id")
  private String id;

  @JsonProperty("pnfdId")
  private String pnfdId;

  @JsonProperty("pnfdName")
  private String pnfdName;

  @JsonProperty("pnfdVersion")
  private String pnfdVersion;

  @JsonProperty("pnfdProvider")
  private String pnfdProvider;

  @JsonProperty("pnfdInvariantId")
  private String pnfdInvariantId;

  /**
   * Signals the security option used by the PNFD archive as defined in clause 5.1 of ETSI GS NFV-SOL 004 [5].  Valid values: OPTION_1, OPTION_2
   */
  public enum ArchiveSecurityOptionEnum {
    _1("OPTION_1"),
    
    _2("OPTION_2");

    private final String value;

    ArchiveSecurityOptionEnum(String value) {
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
    public static ArchiveSecurityOptionEnum fromValue(String value) {
      for (ArchiveSecurityOptionEnum b : ArchiveSecurityOptionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("archiveSecurityOption")
  private ArchiveSecurityOptionEnum archiveSecurityOption;

  @JsonProperty("signingCertificate")
  private String signingCertificate;

  @JsonProperty("artifacts")
  @Valid
  private List<PnfdArchiveArtifactInfo> artifacts = null;

  @JsonProperty("pnfdOnboardingState")
  private PnfdOnboardingStateType pnfdOnboardingState;

  @JsonProperty("onboardingFailureDetails")
  private ProblemDetails onboardingFailureDetails;

  @JsonProperty("pnfdUsageState")
  private PnfdUsageStateType pnfdUsageState;

  @JsonProperty("userDefinedData")
  private Map<String, String> userDefinedData;

  @JsonProperty("_links")
  private NsdInfoLinks links;

  public PnfdInfo id(String id) {
    this.id = id;
    return this;
  }
}

