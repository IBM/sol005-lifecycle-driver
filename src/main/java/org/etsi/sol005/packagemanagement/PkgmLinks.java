package org.etsi.sol005.packagemanagement;

import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the links to resources that a VNF package management notification can contain.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the links to resources that a VNF package management notification can contain.")

public class PkgmLinks {

    @ApiModelProperty(name = "VNF Package", required = true, notes = "Link to the resource representing the VNF package towhich the notified change applies, i.e. the individual onboardedVNF package resource that represents theVNF package.")
    private Link vnfPackage;
    @ApiModelProperty(name = "Subscription", required = true, notes = "Link to the related subscription.")
    private Link subscription;
}
