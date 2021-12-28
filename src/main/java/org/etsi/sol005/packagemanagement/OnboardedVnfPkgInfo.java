package org.etsi.sol005.packagemanagement;

import java.util.List;
import java.util.Map;

import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the information of an on-boarded VNF package.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the information of an on-boarded VNF package.")
public class OnboardedVnfPkgInfo {

    @ApiModelProperty(name = "Identifier", required = true, notes = "Identifier of the on-boarded VNF package. This identifier is allocated by the NFVO.")
    private String id;
    @ApiModelProperty(name = "VNF Identifier", required = true, notes = "This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It's copied from the VNFD of the onboarded VNF package.")
    private String vnfdId;
    @ApiModelProperty(name = "VNF Provider", required = true, notes = "Provider of the VNF package and the VNFD. This information is copied from the VNFD.")
    private String vnfProvider;
    @ApiModelProperty(name = "VNF Product Name", required = true, notes = "Name to identify the VNF product. Invariant for the VNF product lifetime. This information is copied from the VNFD.")
    private String vnfProductName;
    @ApiModelProperty(name = "VNF Software Version", required = true, notes = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD.")
    private String vnfSoftwareVersion;
    @ApiModelProperty(name = "VNFD Version", required = true, notes = "The version of the VNFD. This information is copied from the VNFD.")
    private String vnfdVersion;
    @ApiModelProperty(name = "Checksum", required = true, notes = "Checksum of the on-boarded VNF package.")
    private Checksum checksum;
    @ApiModelProperty(name = "Software Images", notes = "Information about VNF package artifacts that are software images. This attribute shall be present unless it has been requested to be excluded per attribute selector.")
    private List<VnfPackageSoftwareImageInfo> softwareImages;
    @ApiModelProperty(name = "Additional Artifacts", notes = "Information about VNF package artifacts contained in the VNF package that are not software images.")
    private List<VnfPackageArtifactInfo> additionalArtifacts;
    @ApiModelProperty(name = "Operational State", required = true, notes = "Operational state of the on-boarded instance of the VNF package.")
    private PackageOperationalStateType operationalState;
    @ApiModelProperty(name = "Usage State", required = true, notes = "Usage state of the on-boarded instance of the VNF package.")
    private PackageUsageStateType usageState;
    @ApiModelProperty(name = "Deletion Pending", required = true, notes = "The value \"true\" indicates that deletion of this instance of the VNF package has been requested but the VNF package is still being used by created VNFs. This instance of the VNF package will be deleted once all VNFs instantiated from this package are deleted.")
    private boolean deletionPending;
    @ApiModelProperty(name = "User Defined Data", notes = "User defined data for the VNF package.")
    private Map<String, String> userDefinedData;
    @ApiModelProperty(name = "Links", required = true, notes = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Links to resources related to this resource.")
    public static class Links {

        @ApiModelProperty(name = "Self", required = true, notes = "URI of this resource.")
        private Link self;
        @ApiModelProperty(name = "VNF Instance", required = true, notes = "Link to the VNFD resource.")
        private Link vnfd;
        @ApiModelProperty(name = "VNF Instance", required = true, notes = "Link to the \"On-boarded VNF package content\" resource.")
        private Link packageContent;
    }
}
