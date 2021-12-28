package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents subscription filter criteria to match VNF software versions.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents subscription filter criteria to match VNF software versions.")
public class VnfSoftwareVersionFilter {

    @ApiModelProperty(name = "Software Version", required = true, notes = "Software version to match.")
    private String vnfSoftwareVersion;
    @ApiModelProperty(name = "VNFD Version Filters", notes = "If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider.")
    private List<String> vnfdVersions;

}
