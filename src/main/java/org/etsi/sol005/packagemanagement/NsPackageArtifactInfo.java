package org.etsi.sol005.packagemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * Represents an artifact other than a software image which is contained in a VNF package.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents an artifact other than a software image which is contained in a VNF package.")
public class NsPackageArtifactInfo {

    @ApiModelProperty(name = "Artifact Path", required = true, notes = "Path in the VNF package, which identifies the artifact and also allows to access a copy of the artifact.")
    private String artifactPath;
    @ApiModelProperty(name = "Checksum", required = true, notes = "Checksum of the artifact file.")
    private Checksum checksum;
    @ApiModelProperty(name = "Metadata", notes = "The metadata of the artifact that are available in the VNF package, such as Content type, size, creation date, etc.")
    private Map<String, String> metadata;

}
