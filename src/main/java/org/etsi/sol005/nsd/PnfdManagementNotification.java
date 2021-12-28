package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "notificationType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PnfdOnboardingNotification.class, name = PnfdOnboardingNotification.TYPE),
        @JsonSubTypes.Type(value = PnfdOnboardingFailureNotification.class, name = PnfdOnboardingFailureNotification.TYPE),
        @JsonSubTypes.Type(value = PnfdDeletionNotification.class, name = PnfdDeletionNotification.TYPE)
})
public interface PnfdManagementNotification extends NsdNotification {
    String getPnfdInfoId();
    String getPnfdId();
}
