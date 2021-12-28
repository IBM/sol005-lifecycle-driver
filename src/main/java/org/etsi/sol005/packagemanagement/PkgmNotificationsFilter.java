package org.etsi.sol005.packagemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a subscription filter related to notifications related to VNF package management.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a subscription filter related to notifications related to VNF package management.")
public class PkgmNotificationsFilter {

    @ApiModelProperty(name = "Notification Types", notes = "Match particular notification types.")
    private List<NotificationType> notificationTypes;
    @ApiModelProperty(name = "VNF Providers", notes = "Match VNF packages from a provider that is listed as part of this attribute.")
    @JsonProperty("vnfProvider")
    private List<String> vnfProviders;
    @ApiModelProperty(name = "VnfProductInfo", notes = "Match particular VNF products.")
    private List<VnfProductInfo> vnfProductInfo;
    @ApiModelProperty(name = "VNFD Identifiers", notes = "Match VNF packages with a VNFD identifier listed in the attribute.")
    @JsonProperty("vnfdId")
    private List<String> vnfdIds;
    @ApiModelProperty(name = "Onboarded VNF Package Identifiers", notes = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise.")
    @JsonProperty("onboardedVnfPkgId")
    private List<String> onboardedVnfPkgIds;
    @ApiModelProperty(name = "Operational States", notes = "Match particular operational state of the onboarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise.")
    @JsonProperty("operationalState")
    private List<PackageOperationalStateType> operationalStates;
    @ApiModelProperty(name = "Operational States", notes = "Match particular usage state of the onboarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise.")
    @JsonProperty("usageState")
    private List<PackageUsageStateType> usageStates;

    public static enum NotificationType {
        VnfPackageOnboardingNotification,
        VnfPackageChangeNotification
    }

    /**
     * Match particular VNF products.
     */
    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Match particular VNF products.")
    public static class VnfProductInfo {

        @ApiModelProperty(name = "VNF Product Name", required = true, notes = "Match VNF products with a particular name.")
        private String vnfProductName;
        @ApiModelProperty(name = "VNF Software Version", notes = "Match VNF products with one of the software versions listed as part of this attribute.")
        @JsonProperty("vnfSoftwareVersion")
        private List<String> vnfSoftwareVersions;
        @ApiModelProperty(name = "VNFD Version", notes = "Match VNF products with one of the VNFD versions listed as part of this attribute.")
        @JsonProperty("vnfdVersion")
        private List<String> vnfdVersions;
    }
}
