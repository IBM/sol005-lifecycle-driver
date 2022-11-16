package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.etsi.sol005.lifecyclemanagement.LccnLinks;

import java.time.OffsetDateTime;

/**
 * Represents a PNFD management notification, which informs the receiver of the deletion of an on-boarded PNFD.
 * The notification shall comply with the provisions defined in table 5.5.2.15-1. The support of this notification is mandatory.
 * The notification is triggered when an on-boarded PNFD is deleted.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a PNFD management notification, which informs the receiver of the deletion of an on-boarded PNFD.")
public class PnfdDeletionNotification implements PnfdManagementNotification {
    public static final String TYPE = "PnfdDeletionNotification";

    @Schema(name = "Id", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"PnfdDeletionNotification\" for this notification type.")
    private final String notificationType = TYPE;
    @Schema(name = "Subscription Id", description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "Pnfd Info Id", required = true, description = "Identifier of the PNFD information object. This identifier is allocated by the NFVO.")
    private String pnfdInfoId;
    @Schema(name = "Pnfd Id", required = true, description = "This identifier, which is managed by the service provider, identifies the PNFD in a globally unique way. It is copied from the on-boarded PNFD.")
    private String pnfdId;
    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private LccnLinks links;
}
