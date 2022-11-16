package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Represents a NS identifier creation notification, which informs the receiver of the creation of a new NS
 * instance resource and the associated NS instance identifier.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a NS identifier creation notification, which informs the receiver of the creation of a new NS instance resource and the associated NS instance identifier.")
public class NsIdentifierCreationNotification implements LifecycleManagementNotification {

    public static final String TYPE = "NsIdentifierCreationNotification";

    @Schema(name = "Id", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"NsIdentifierCreationNotification\" for this notification type.")
    private final String notificationType = TYPE;
    @Schema(name = "Subscription Id", description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "NS Instance Id", required = true, description = "The created NS instance identifier.")
    private String nsInstanceId;
    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private LccnLinks links;

}
