package org.etsi.sol005.packagemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Represents the information of a NS package.")
public class NsPkgInfo {

    @ApiModelProperty(name = "Identifier", required = true, notes = "Identifier of the NS package. This identifier is allocated by the NFVO. ")
    private String id;
    @ApiModelProperty(name = "NS Identifier", notes = "This identifier, which is managed by the NS provider, identifies the NS package and the NSD in a globally unique way. It is copied from the NSD of the on-boarded NS package. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsdId;
    @ApiModelProperty(name = "NS Provider", notes = "Provider of the NS package and the NSD. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsProvider;
    @ApiModelProperty(name = "NS Product Name", notes = "Name to identify the NS product. Invariant for the NS product lifetime. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsProductName;
    @ApiModelProperty(name = "NS Software Version", notes = "Software version of the NS. This is changed when there is any change to the software included in the NS package. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsSoftwareVersion;
    @ApiModelProperty(name = "NSD Version", notes = "The version of the NSD. This information is copied from the NSD. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private String nsdVersion;
    @ApiModelProperty(name = "Checksum", notes = "Checksum of the on-boarded NS package. It shall be present after the NS package content has been on-boarded and absent otherwise.")
    private Checksum checksum;
    @ApiModelProperty(name = "Software Images", notes = "Information about NS package artifacts that are software images. This attribute shall not be present before the NS package content is on-boarded. Otherwise, this attribute shall be present unless it has been requested to be excluded per attribute selector.")
    private List<NsPackageSoftwareImageInfo> softwareImages;
    @ApiModelProperty(name = "Additional Artifacts", notes = "Information about NS package artifacts contained in the NS package that are not software images. This attribute shall not be present before the NS package content is on-boarded. Otherwise, this attribute shall be present if the NS package contains additional artifacts.")
    private List<NsPackageArtifactInfo> additionalArtifacts;
    @ApiModelProperty(name = "Onboarding State", required = true, notes = "On-boarding state of the NS package.")
    private PackageOnboardingStateType onboardingState;
    @ApiModelProperty(name = "Operational State", required = true, notes = "Operational state of the NS package.")
    private PackageOperationalStateType operationalState;
    @ApiModelProperty(name = "Usage State", required = true, notes = "Usage state of the NS package.")
    private PackageUsageStateType usageState;
    @ApiModelProperty(name = "User Defined Data", notes = "User defined data for the NS package.")
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
        @ApiModelProperty(name = "NSD", notes = "Link to the NSD resource. This link shall be present after the NS package content is on-boarded.")
        private Link nsd;
        @ApiModelProperty(name = "NS Instance", required = true, notes = "Link to the \" NS package content\" resource.")
        private Link packageContent;

    }

}
