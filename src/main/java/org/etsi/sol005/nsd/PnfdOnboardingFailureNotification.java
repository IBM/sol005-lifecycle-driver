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
 * Represents a PNFD management notification, which informs the receiver of the failure of on-boarding a PNFD.
 * It shall comply with the provisions defined in table 5.5.2.14-1. The support of this notification is mandatory.
 * The notification is triggered when the on-boarding of a PNFD fails.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a PNFD management notification, which informs the receiver of the failure of on-boarding a PNFD.")
public class PnfdOnboardingFailureNotification implements PnfdManagementNotification {
    public static final String TYPE = "PnfdOnboardingFailureNotification";

    @Schema(name = "Id", required = true, description = "Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the \"id\" attribute of all these notifications shall have the same value.")
    private String id;
    @Schema(name = "Notification Type", required = true, description = "Discriminator for the different notification types. Shall be set to \"PnfdOnboardingFailureNotification\" for this notification type.")
    private final String notificationType = TYPE;
    @Schema(name = "Subscription Id", description = "Identifier of the subscription that this notification relates to.")
    private String subscriptionId;
    @Schema(name = "Notification Time", required = true, description = "Date-time of the generation of the notification.")
    private OffsetDateTime timeStamp;
    @Schema(name = "Pnfd Info Id", required = true, description = "Identifier of the PNFD information object. This identifier is allocated by the NFVO.")
    private String pnfdInfoId;
    @Schema(name = "Pnfd Id", required = true, description = "This identifier, which is managed by the service provider, identifies the PNFD in a globally unique way. It is copied from the on-boarded PNFD.")
    private String pnfdId;
    @Schema(name = "Onboarding Failure Details", required = true, description = "Failure details of current onboarding procedure. See clause 6.3 of ETSI GS NFV-SOL 013 [16] for the details of ProblemDetails structure.")
    private ProblemDetails onboardingFailureDetails;
    @Schema(name = "Links", required = true, description = "Links to resources related to this notification.")
    @JsonProperty("_links")
    private LccnLinks links;
}
