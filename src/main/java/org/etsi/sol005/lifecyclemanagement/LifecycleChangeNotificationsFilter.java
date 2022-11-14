package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.VnfInstanceSubscriptionFilter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents a subscription filter related to notifications about NS lifecycle changes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a subscription filter related to notifications about VNF lifecycle changes.")
public class LifecycleChangeNotificationsFilter {

    @Schema(name = "self", description = "Filter criteria to select VNF instances about which to notify.")
    private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter;
    @Schema(name = "self", description = "Match particular notification types.")
    private List<NotificationType> notificationTypes;
    @Schema(name = "self", description = "Match particular VNF lifecycle operation types for the notification of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise.")
    private List<LcmOperationType> operationTypes;
    @Schema(name = "self", description = "Match particular LCM operation state values as reported in notifications of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise.")
    private List<LcmOperationStateType> operationStates;

    public enum NotificationType {

        NsLcmOperationOccurrenceNotification,
        NsIdentifierCreationNotification,
        NsIdentifierDeletionNotification

    }
}
