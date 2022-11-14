package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.etsi.sol005.lifecyclemanagement.LccnLinks;

import java.time.OffsetDateTime;

/**
 * Represents an NSD management notification, which informs the receiver of a change of the "nsdOperationalState" attribute of an on-boarded NSD.
 * Changes in the value of the "nsdUsageState" and "nsdOnboardingState" attributes are not reported. The notification shall comply with the provisions
 * defined in table 5.5.2.11-1. The support of this notification is mandatory. The notification shall be triggered by the NFVO
 * when the value of the "nsdOperationalState" attribute has changed, and the "nsdOperationalState" attribute has the value "ONBOARDED".
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents an NSD management notification, which informs the receiver of a change of the \"nsdOperationalState\" attribute of an on-boarded NSD.")
public class NsdChangeNotification implements NsdManagementNotification {
    public static final String TYPE = "NsdChangeNotification";

    @Schema(name = "Id", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"NsdChangeNotification\" for this notification type.")
    private final String notificationType = TYPE;
    @Schema(name = "Subscription Id", description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "Nsd Info Id", required = true, description = "Identifier of the NSD information object. This identifier is allocated by the NFVO.")
    private String nsdInfoId;
    @Schema(name = "Nsd Id", required = true, description = "This identifier, which is managed by the service provider, identifies the NSD in a globally unique way. It is copied from the on-boarded NSD.")
    private String nsdId;
    @Schema(name = "Nsd Operational State Type", required = true, description = "New operational state of the on-boarded NSD.")
    private NsdOperationalStateType nsdOperationalStateType;
    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private LccnLinks links;
}
