package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * This type represents an artifact contained in a PNFD archive. It shall comply with provisions defined in table 5.5.3.6-1.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PnfdArchiveArtifactInfo {
    @JsonProperty("artifactPath")
    private String artifactPath;
    @JsonProperty("checksum")
    private Checksum checksum;
    @JsonProperty("nonManoArtifactSetId")
    private String nonManoArtifactSetId;
    @JsonProperty("metadata")
    private Object metadata;
}
