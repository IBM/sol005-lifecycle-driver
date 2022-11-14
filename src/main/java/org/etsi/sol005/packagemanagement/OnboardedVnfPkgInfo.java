package org.etsi.sol005.packagemanagement;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.Link;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Represents the information of an on-boarded VNF package.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents the information of an on-boarded VNF package.")
public class OnboardedVnfPkgInfo {

    @Schema(name = "Identifier", required = true, description = "Identifier of the on-boarded VNF package. This identifier is allocated by the NFVO.")
    private String id;
    @Schema(name = "VNF Identifier", required = true, description = "This identifier, which is managed by the VNF provider, identifies the VNF package and the VNFD in a globally unique way. It's copied from the VNFD of the onboarded VNF package.")
    private String vnfdId;
    @Schema(name = "VNF Provider", required = true, description = "Provider of the VNF package and the VNFD. This information is copied from the VNFD.")
    private String vnfProvider;
    @Schema(name = "VNF Product Name", required = true, description = "Name to identify the VNF product. Invariant for the VNF product lifetime. This information is copied from the VNFD.")
    private String vnfProductName;
    @Schema(name = "VNF Software Version", required = true, description = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD.")
    private String vnfSoftwareVersion;
    @Schema(name = "VNFD Version", required = true, description = "The version of the VNFD. This information is copied from the VNFD.")
    private String vnfdVersion;
    @Schema(name = "Checksum", required = true, description = "Checksum of the on-boarded VNF package.")
    private Checksum checksum;
    @Schema(name = "Software Images", description = "Information about VNF package artifacts that are software images. This attribute shall be present unless it has been requested to be excluded per attribute selector.")
    private List<VnfPackageSoftwareImageInfo> softwareImages;
    @Schema(name = "Additional Artifacts", description = "Information about VNF package artifacts contained in the VNF package that are not software images.")
    private List<VnfPackageArtifactInfo> additionalArtifacts;
    @Schema(name = "Operational State", required = true, description = "Operational state of the on-boarded instance of the VNF package.")
    private PackageOperationalStateType operationalState;
    @Schema(name = "Usage State", required = true, description = "Usage state of the on-boarded instance of the VNF package.")
    private PackageUsageStateType usageState;
    @Schema(name = "Deletion Pending", required = true, description = "The value \"true\" indicates that deletion of this instance of the VNF package has been requested but the VNF package is still being used by created VNFs. This instance of the VNF package will be deleted once all VNFs instantiated from this package are deleted.")
    private boolean deletionPending;
    @Schema(name = "User Defined Data", description = "User defined data for the VNF package.")
    private Map<String, String> userDefinedData;
    @Schema(name = "Links", required = true, description = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Links to resources related to this resource.")
    public static class Links {

        @Schema(name = "Self", required = true, description = "URI of this resource.")
        private Link self;
        @Schema(name = "VNF Instance", required = true, description = "Link to the VNFD resource.")
        private Link vnfd;
        @Schema(name = "VNF Instance", required = true, description = "Link to the \"On-boarded VNF package content\" resource.")
        private Link packageContent;
    }
}
