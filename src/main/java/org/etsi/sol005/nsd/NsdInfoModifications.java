package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This type represents attribute modifications for an individual NS descriptor resource based on the NsdInfo data type. The attributes of NsdInfo that can be modified are included in the NsdInfoModifications data type.NOTE: At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. 
 */
@Schema(description = "This type represents attribute modifications for an individual NS descriptor resource based on the NsdInfo data type. The attributes of NsdInfo that can be modified are included in the NsdInfoModifications data type.NOTE: At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-03-26T11:52:43.966Z[Europe/Dublin]")
public class NsdInfoModifications {
  @JsonProperty("nsdOperationalState")
  private NsdOperationalStateType nsdOperationalState;

  @JsonProperty("userDefinedData")
  @Valid
  private Map<String, String> userDefinedData = null;

  public NsdInfoModifications nsdOperationalState(NsdOperationalStateType nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
    return this;
  }

  /**
   * Get nsdOperationalState
   * @return nsdOperationalState
  */
  @Schema(description = "")

  @Valid

  public NsdOperationalStateType getNsdOperationalState() {
    return nsdOperationalState;
  }

  public void setNsdOperationalState(NsdOperationalStateType nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
  }

  public NsdInfoModifications userDefinedData(Map<String, String> userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  public NsdInfoModifications addUserDefinedDataItem(String userDefinedDataKey, String userDefinedDataItem) {
    if (this.userDefinedData == null) {
      this.userDefinedData = new HashMap<>();
    }
    this.userDefinedData.put(userDefinedDataKey, userDefinedDataItem);
    return this;
  }

  /**
   * Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge Patch (see IETF RFC 7396). NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. 
   * @return userDefinedData
  */
  @Schema(description = "Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge Patch (see IETF RFC 7396). NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")


  public Map<String, String> getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(Map<String, String> userDefinedData) {
    this.userDefinedData = userDefinedData;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NsdInfoModifications nsdInfoModifications = (NsdInfoModifications) o;
    return Objects.equals(this.nsdOperationalState, nsdInfoModifications.nsdOperationalState) &&
        Objects.equals(this.userDefinedData, nsdInfoModifications.userDefinedData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsdOperationalState, userDefinedData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsdInfoModifications {\n");
    
    sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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

