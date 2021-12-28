package org.etsi.sol005.model;

import java.util.List;

import org.etsi.sol005.model.NsInstanceSubscriptionFilter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

/**
 * This type represents a subscription filter related to notifications about  NS lifecycle changes. It shall comply with the provisions defined in Table 6.5.3.8-1. At a particular nesting level in the filter structure, the following applies:  All attributes shall match in order for the filter to match (logical \&quot;and\&quot; between different filter attributes).  If an attribute is an array, the attribute shall match if at least one of the values in the array matches (logical \&quot;or\&quot; between the values of one filter attribute). 
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class LifecycleChangeNotificationsFilter   {
  private NsInstanceSubscriptionFilter nsInstanceSubscriptionFilter;

  /**
   * Gets or Sets notificationTypes
   */
  public enum NotificationTypesEnum {
    NSLCMOPERATIONOCCURENCENOTIFICATION("NsLcmOperationOccurenceNotification"),
    
    NSIDENTIFIERCREATIONNOTIFICATION("NsIdentifierCreationNotification"),
    
    NSIDENTIFIERDELETIONNOTIFICATION("NsIdentifierDeletionNotification"),
    
    NSCHANGENOTIFICATION("NsChangeNotification");

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

  private List<NsLcmOpType> operationTypes = null;

  private List<LcmOperationStateType> operationStates = null;

  private List<NsComponentType> nsComponentTypes = null;

  private List<LcmOpNameForChangeNotificationType> lcmOpNameImpactingNsComponent = null;

  private List<LcmOpOccStatusForChangeNotificationType> lcmOpOccStatusImpactingNsComponent = null;
}

