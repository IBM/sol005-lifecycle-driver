package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents subscription filter criteria to match VNF software versions.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents subscription filter criteria to match VNF software versions.")
public class VnfSoftwareVersionFilter {

    @Schema(name = "Software Version", required = true, description = "Software version to match.")
    private String vnfSoftwareVersion;
    @Schema(name = "VNFD Version Filters", description = "If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider.")
    private List<String> vnfdVersions;

}
