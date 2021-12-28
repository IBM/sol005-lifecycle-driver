package org.etsi.sol005.model;

import java.util.List;

import org.etsi.sol005.nsd.NsdOnboardingStateType;
import org.etsi.sol005.nsd.NsdOperationalStateType;
import org.etsi.sol005.nsd.NsdUsageStateType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents a subscription filter related to notifications about NSD management. It shall comply with the provisions defined in Table 5.5.3.2-1 of GS NFV-SOL 005. At a particular nesting level in the filter structure, the following applies: All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes). If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). NOTE 1: The attributes \&quot;nsdId\&quot; and \&quot;nsdInfoId\&quot; are alternatives to reference to a particular NSD in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. NOTE 2: The attributes \&quot;pnfdId\&quot; and \&quot;pnfdInfoId\&quot; are alternatives to reference to a particular PNFD in a filter. They should not be used both in the same filter instance, but one alternative should be chosen. 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class NsdmNotificationsFilter {
  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    NSDONBOARDINGNOTIFICATION("NsdOnBoardingNotification"),

    NSDONBOARDINGFAILURENOTIFICATION("NsdOnboardingFailureNotification"),

    NSDCHANGENOTIFICATION("NsdChangeNotification"),

    NSDDELETIONNOTIFICATION("NsdDeletionNotification"),

    PNFDONBOARDINGNOTIFICATION("PnfdOnBoardingNotification"),

    PNFDONBOARDINGFAILURENOTIFICATION("PnfdOnBoardingFailureNotification"),

    PNFDDELETIONNOTIFICATION("PnfdDeletionNotification");

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

  private List<NotificationTypesEnum> notificationTypes = null;

  private List<String> nsdInfoId = null;

  private List<String> nsdId = null;

  private List<String> nsdName = null;

  private List<String> nsdVersion = null;

  private List<String> nsdDesigner = null;

  private List<String> nsdInvariantId = null;

  private List<String> vnfPkgIds = null;

  private List<String> pnfdInfoIds = null;

  private List<String> nestedNsdInfoIds = null;

  private List<NsdOnboardingStateType> nsdOnboardingState = null;

  private List<NsdOperationalStateType> nsdOperationalState = null;

  private List<NsdUsageStateType> nsdUsageState = null;

  private List<String> pnfdId = null;

  private List<String> pnfdName = null;

  private List<String> pnfdVersion = null;

  private List<String> pnfdProvider = null;

  private List<String> pnfdInvariantId = null;

  private List<PnfdOnboardingStateType> pnfdOnboardingState = null;

  private List<PnfdUsageStateType> pnfdUsageState = null;

}

