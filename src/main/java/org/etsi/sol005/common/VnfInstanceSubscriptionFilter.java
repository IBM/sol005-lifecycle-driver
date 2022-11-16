package org.etsi.sol005.common;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.lifecyclemanagement.VnfProductsFromProviderFilter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents subscription filter criteria to match VNF instances.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents subscription filter criteria to match VNF instances.")
public class VnfInstanceSubscriptionFilter {

    @Schema(name = "VNFD Id Filters", description = "If present, match VNF instances that were created based on a VNFD identified by one of the vnfdId values listed in this attribute.")
    private List<String> vnfdIds;
    @Schema(name = "VNF Product Filters", description = "If present, match VNF instances that belong to VNF products from certain providers.")
    private List<VnfProductsFromProviderFilter> vnfProductsFromProviders;
    @Schema(name = "VNF Instance Id Filters", description = "If present, match VNF instances with an instance identifier listed in this attribute.")
    private List<String> vnfInstanceIds;
    @Schema(name = "VNF Instance Name Filters", description = "If present, match VNF instances with a VNF Instance Name listed in this attribute.")
    private List<String> vnfInstanceNames;

}
