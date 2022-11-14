package org.etsi.sol005.packagemanagement;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents a subscription filter related to notifications related to VNF package management.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a subscription filter related to notifications related to VNF package management.")
public class PkgmNotificationsFilter {

    @Schema(name = "Notification Types", description = "Match particular notification types.")
    private List<NotificationType> notificationTypes;
    @Schema(name = "VNF Providers", description = "Match VNF packages from a provider that is listed as part of this attribute.")
    @JsonProperty("vnfProvider")
    private List<String> vnfProviders;
    @Schema(name = "VnfProductInfo", description = "Match particular VNF products.")
    private List<VnfProductInfo> vnfProductInfo;
    @Schema(name = "VNFD Identifiers", description = "Match VNF packages with a VNFD identifier listed in the attribute.")
    @JsonProperty("vnfdId")
    private List<String> vnfdIds;
    @Schema(name = "Onboarded VNF Package Identifiers", description = "Match VNF packages with a package identifier listed in the attribute. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise.")
    @JsonProperty("onboardedVnfPkgId")
    private List<String> onboardedVnfPkgIds;
    @Schema(name = "Operational States", description = "Match particular operational state of the onboarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise.")
    @JsonProperty("operationalState")
    private List<PackageOperationalStateType> operationalStates;
    @Schema(name = "Operational States", description = "Match particular usage state of the onboarded VNF package. May be present if the \"notificationTypes\" attribute contains the value \"VnfPackageChangeNotification\", and shall be absent otherwise.")
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
    @Schema(description = "Match particular VNF products.")
    public static class VnfProductInfo {

        @Schema(name = "VNF Product Name", required = true, description = "Match VNF products with a particular name.")
        private String vnfProductName;
        @Schema(name = "VNF Software Version", description = "Match VNF products with one of the software versions listed as part of this attribute.")
        @JsonProperty("vnfSoftwareVersion")
        private List<String> vnfSoftwareVersions;
        @Schema(name = "VNFD Version", description = "Match VNF products with one of the VNFD versions listed as part of this attribute.")
        @JsonProperty("vnfdVersion")
        private List<String> vnfdVersions;
    }
}
