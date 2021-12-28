package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents subscription filter criteria to match VNF products.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents subscription filter criteria to match VNF products.")
public class VnfProductFilter {

    @ApiModelProperty(name = "VNF Product Name", required = true, notes = "Name of the VNF product to match.")
    private String vnfProductName;
    @ApiModelProperty(name = "Version Filters", notes = "If present, match VNF instances that belong to VNF products with certain versions and a certain product name, from one particular provider.")
    private List<VnfSoftwareVersionFilter> versions;

}
