package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.OffsetDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "notificationType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NsIdentifierCreationNotification.class, name = NsIdentifierCreationNotification.TYPE),
        @JsonSubTypes.Type(value = NsIdentifierDeletionNotification.class, name = NsIdentifierDeletionNotification.TYPE),
        @JsonSubTypes.Type(value = NsLcmOperationOccurrenceNotification.class, name = NsLcmOperationOccurrenceNotification.TYPE)
})
public interface LifecycleManagementNotification {

    String getId();
    String getNotificationType();
    String getSubscriptionId();
    OffsetDateTime getTimeStamp();
    String getNsInstanceId();
    LccnLinks getLinks();

}
