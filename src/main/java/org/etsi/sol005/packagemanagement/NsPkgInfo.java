package org.etsi.sol005.packagemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.etsi.sol005.common.Link;

import java.util.List;
import java.util.Map;

/**
 * Represents the information of a NS package.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents the information of a NS package.")
public class NsPkgInfo {

    @Schema(name = "Identifier", required = true, description = "Identifier of the NS package. This identifier is allocated by the NFVO. ")
    private String id;
    @Schema(name = "NS Identifier", description = "This identifier, which is managed by the NS provider, identifies the NS package and the NSD in a globally unique way. It is copied from the NSD of the on-boarded NS package. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsdId;
    @Schema(name = "NS Provider", description = "Provider of the NS package and the NSD. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsProvider;
    @Schema(name = "NS Product Name", description = "Name to identify the NS product. Invariant for the NS product lifetime. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsProductName;
    @Schema(name = "NS Software Version", description = "Software version of the NS. This is changed when there is any change to the software included in the NS package. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsSoftwareVersion;
    @Schema(name = "NSD Version", description = "The version of the NSD. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsdVersion;
    @Schema(name = "Checksum", description = "Checksum of the on-boarded NS package. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private Checksum checksum;
    @Schema(name = "Software Images", description = "Information about NS package artifacts that are software images. This attribute shall not be present before the NS package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector.")
    private List<NsPackageSoftwareImageInfo> softwareImages;
    @Schema(name = "Additional Artifacts", description = "Information about NS package artifacts contained in the NS package that are not software images. This attribute shall not be present before the NS package content is on-boarded. Otherwise, this attribute shall be present if the NS package contains additional artifacts.")
    private List<NsPackageArtifactInfo> additionalArtifacts;
    @Schema(name = "Onboarding State", required = true, description = "On-boarding state of the NS package.")
    private PackageOnboardingStateType onboardingState;
    @Schema(name = "Operational State", required = true, description = "Operational state of the NS package.")
    private PackageOperationalStateType operationalState;
    @Schema(name = "Usage State", required = true, description = "Usage state of the NS package.")
    private PackageUsageStateType usageState;
    @Schema(name = "User Defined Data", description = "User defined data for the NS package.")
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
        @Schema(name = "NSD", description = "Link to the NSD resource. This link shall be present after the NS package content is on-boarded.")
        private Link nsd;
        @Schema(name = "NS Instance", required = true, description = "Link to the \" NS package content\" resource.")
        private Link packageContent;

    }

}
