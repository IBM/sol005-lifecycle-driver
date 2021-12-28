package org.etsi.sol005.nsd;

import org.etsi.sol005.lifecyclemanagement.LccnLinks;

import java.time.OffsetDateTime;

public interface NsdNotification {

    String getId();
    String getNotificationType();
    String getSubscriptionId();
    OffsetDateTime getTimeStamp();
    LccnLinks getLinks();
}
