package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents subscription filter criteria to match VNF providers.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents subscription filter criteria to match VNF providers.")
public class VnfProductsFromProviderFilter {

    @ApiModelProperty(name = "VNF Provider", required = true, notes = "Name of the VNF provider to match.")
    private String vnfProvider;
    @ApiModelProperty(name = "VNF Products Filter", notes = "If present, match VNF instances that belong to VNF products with certain product names, from one particular provider.")
    private List<VnfProductFilter> vnfProducts;

}
