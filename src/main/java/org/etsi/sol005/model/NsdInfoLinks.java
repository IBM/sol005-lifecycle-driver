package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Links to resources related to this resource. 
 */
@Schema(description = "Links to resources related to this resource. ")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-03-30T10:40:53.292+01:00[Europe/Dublin]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NsdInfoLinks {
  @JsonProperty("self")
  private Link self;

  @JsonProperty("nsd_content")
  private Link nsdContent;

  public NsdInfoLinks self(Link self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
  */
  @Schema(required = true, description = "")
  @NotNull

  @Valid

  public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public NsdInfoLinks nsdContent(Link nsdContent) {
    this.nsdContent = nsdContent;
    return this;
  }

  /**
   * Get nsdContent
   * @return nsdContent
  */
  @Schema(required = true, description = "")
  @NotNull

  @Valid

  public Link getNsdContent() {
    return nsdContent;
  }

  public void setNsdContent(Link nsdContent) {
    this.nsdContent = nsdContent;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsdInfoLinks nsdInfoLinks = (NsdInfoLinks) o;
    return Objects.equals(this.self, nsdInfoLinks.self) &&
        Objects.equals(this.nsdContent, nsdInfoLinks.nsdContent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, nsdContent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdInfoLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    nsdContent: ").append(toIndentedString(nsdContent)).append("\n");
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

