package org.etsi.sol005.packagemanagement;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents the links to resources that a VNF package management notification can contain.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents the links to resources that a VNF package management notification can contain.")

public class PkgmLinks {

    @Schema(name = "VNF Package", required = true, description = "Link to the resource representing the VNF package towhich the notified change applies, i.e. the individual onboardedVNF package resource that represents theVNF package.")
    private Link vnfPackage;
    @Schema(name = "Subscription", required = true, description = "Link to the related subscription.")
    private Link subscription;
}
