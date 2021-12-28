package org.etsi.sol005.common;

import java.util.List;

import org.etsi.sol005.lifecyclemanagement.VnfProductsFromProviderFilter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents subscription filter criteria to match VNF instances.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents subscription filter criteria to match VNF instances.")
public class VnfInstanceSubscriptionFilter {

    @ApiModelProperty(name = "VNFD Id Filters", notes = "If present, match VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute.")
    private List<String> vnfdIds;
    @ApiModelProperty(name = "VNF Product Filters", notes = "If present, match VNF instances that belong to VNF products from certain providers.")
    private List<VnfProductsFromProviderFilter> vnfProductsFromProviders;
    @ApiModelProperty(name = "VNF Instance Id Filters", notes = "If present, match VNF instances with an instance identifier listed in this attribute.")
    private List<String> vnfInstanceIds;
    @ApiModelProperty(name = "VNF Instance Name Filters", notes = "If present, match VNF instances with a VNF Instance Name listed in this attribute.")
    private List<String> vnfInstanceNames;

}
