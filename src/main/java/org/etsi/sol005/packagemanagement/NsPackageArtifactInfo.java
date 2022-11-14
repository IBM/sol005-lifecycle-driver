package org.etsi.sol005.packagemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * Represents an artifact other than a software image which is contained in a VNF package.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents an artifact other than a software image which is contained in a VNF package.")
public class NsPackageArtifactInfo {

    @Schema(name = "Artifact Path", required = true, description = "Path in the VNF package, which identifies the artifact and also allows to access a copy of the artifact.")
    private String artifactPath;
    @Schema(name = "Checksum", required = true, description = "Checksum of the artifact file.")
    private Checksum checksum;
    @Schema(name = "Metadata", description = "The metadata of the artifact that are available in the VNF package, such as Content type, size, creation date, etc.")
    private Map<String, String> metadata;

}
