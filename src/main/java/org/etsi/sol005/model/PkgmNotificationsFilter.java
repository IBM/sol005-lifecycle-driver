package org.etsi.sol005.model;

import org.etsi.sol005.model.NsProductsFromProviders;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import org.etsi.sol005.packagemanagement.PackageOperationalStateType;
import org.etsi.sol005.packagemanagement.PackageUsageStateType;
import lombok.Data;

import java.util.List;

/**
 * This type represents a subscription filter related to notifications related to VNF package management. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class PkgmNotificationsFilter   {
  /**
   * Match particular notification types. Permitted values: - VnfPackageOnboardingNotification - VnfPackageChangeNotification 
   */
  public enum NotificationTypesEnum {
    VNFPACKAGEONBOARDINGNOTIFICATION("VnfPackageOnboardingNotification"),
    
    VNFPACKAGECHANGENOTIFICATION("VnfPackageChangeNotification");

    private String value;

    NotificationTypesEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static NotificationTypesEnum fromValue(String value) {
      for (NotificationTypesEnum b : NotificationTypesEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private String vnfdId;

  private String vnfPkgId;

  private PackageOperationalStateType operationalState;

  private PackageUsageStateType usageState;

  String vnfmInfo;

  private List<NotificationTypesEnum> notificationTypes = null;

  private List<NsProductsFromProviders> nsProductsFromProviders = null;
}

