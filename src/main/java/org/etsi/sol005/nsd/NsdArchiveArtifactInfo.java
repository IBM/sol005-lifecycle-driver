package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

/**
 * This type represents an artifact contained in an NSD archive. It shall comply with provisions defined in Table 5.5.3.5-1. 
 */
@Schema(description = "This type represents an artifact contained in an NSD archive. It shall comply with provisions defined in Table 5.5.3.5-1. ")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-03-26T11:52:43.966Z[Europe/Dublin]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NsdArchiveArtifactInfo {
  @JsonProperty("artifactPath")
  private String artifactPath;

  @JsonProperty("checksum")
  private Checksum checksum;

  @JsonProperty("metadata")
  private Object metadata;

  public NsdArchiveArtifactInfo artifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
    return this;
  }

  /**
   * A string as defined in IETF RFC 8259. 
   * @return artifactPath
  */
  @Schema(required = true, description = "A string as defined in IETF RFC 8259. ")
  @NotNull


  public String getArtifactPath() {
    return artifactPath;
  }

  public void setArtifactPath(String artifactPath) {
    this.artifactPath = artifactPath;
  }

  public NsdArchiveArtifactInfo checksum(Checksum checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Get checksum
   * @return checksum
  */
  @Schema(required = true, description = "")
  @NotNull

  @Valid

  public Checksum getChecksum() {
    return checksum;
  }

  public void setChecksum(Checksum checksum) {
    this.checksum = checksum;
  }

  public NsdArchiveArtifactInfo metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions defined in clause 4 of IETF RFC 7159. 
   * @return metadata
  */
  @Schema(description = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions defined in clause 4 of IETF RFC 7159. ")


  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsdArchiveArtifactInfo nsdArchiveArtifactInfo = (NsdArchiveArtifactInfo) o;
    return Objects.equals(this.artifactPath, nsdArchiveArtifactInfo.artifactPath) &&
        Objects.equals(this.checksum, nsdArchiveArtifactInfo.checksum) &&
        Objects.equals(this.metadata, nsdArchiveArtifactInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(artifactPath, checksum, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdArchiveArtifactInfo {\n");
    
    sb.append("    artifactPath: ").append(toIndentedString(artifactPath)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

