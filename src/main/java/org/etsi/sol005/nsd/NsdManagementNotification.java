package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "notificationType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NsdOnboardingNotification.class, name = NsdOnboardingNotification.TYPE),
        @JsonSubTypes.Type(value = NsdOnboardingFailureNotification.class, name = NsdOnboardingFailureNotification.TYPE),
        @JsonSubTypes.Type(value = NsdChangeNotification.class, name = NsdChangeNotification.TYPE),
        @JsonSubTypes.Type(value = NsdDeletionNotification.class, name = NsdDeletionNotification.TYPE)
})
public interface NsdManagementNotification extends NsdNotification {
    String getNsdInfoId();
    String getNsdId();
}
