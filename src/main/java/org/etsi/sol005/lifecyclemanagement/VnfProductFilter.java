package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents subscription filter criteria to match VNF products.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents subscription filter criteria to match VNF products.")
public class VnfProductFilter {

    @Schema(name = "VNF Product Name", required = true, description = "Name of the VNF product to match.")
    private String vnfProductName;
    @Schema(name = "Version Filters", description = "If present, match VNF instances that belong to VNF products with certain versions and a certain product name, from one particular provider.")
    private List<VnfSoftwareVersionFilter> versions;

}
