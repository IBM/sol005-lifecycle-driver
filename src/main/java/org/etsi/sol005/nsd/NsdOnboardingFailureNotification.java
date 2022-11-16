package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.etsi.sol005.common.ProblemDetails;
import org.etsi.sol005.lifecyclemanagement.LccnLinks;

import java.time.OffsetDateTime;

/**
 * Represents an NSD management notification, which informs the receiver of the failure of on-boarding an NSD.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents an NSD management notification, which informs the receiver of the failure of on-boarding an NSD.")
public class NsdOnboardingFailureNotification implements NsdManagementNotification {
    public static final String TYPE = "NsdOnboardingFailureNotification";

    @Schema(name = "Id", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"NsdOnboardingFailureNotification\" for this notification type.")
    private final String notificationType = TYPE;
    @Schema(name = "Subscription Id", description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "Nsd Info Id", required = true, description = "Identifier of the NSD information object. This identifier is allocated by the NFVO.")
    private String nsdInfoId;
    @Schema(name = "Nsd Id", required = true, description = "This identifier, which is managed by the service provider, identifies the NSD in a globally unique way. It is copied from the on-boarded NSD.")
    private String nsdId;
    @Schema(name = "Onboarding Failure Details", required = true, description = "Failure details of current onboarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 [16] for the details of ProblemDetails structure.")
    private ProblemDetails onboardingFailureDetails;
    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private LccnLinks links;
}
