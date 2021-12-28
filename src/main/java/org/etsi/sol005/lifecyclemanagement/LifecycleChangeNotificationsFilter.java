package org.etsi.sol005.lifecyclemanagement;

import java.util.List;

import org.etsi.sol005.common.VnfInstanceSubscriptionFilter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a subscription filter related to notifications about NS lifecycle changes.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a subscription filter related to notifications about VNF lifecycle changes.")
public class LifecycleChangeNotificationsFilter {

    @ApiModelProperty(name = "self", notes = "Filter criteria to select VNF instances about which to notify.")
    private VnfInstanceSubscriptionFilter vnfInstanceSubscriptionFilter;
    @ApiModelProperty(name = "self", notes = "Match particular notification types.")
    private List<NotificationType> notificationTypes;
    @ApiModelProperty(name = "self", notes = "Match particular VNF lifecycle operation types for the notification of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise.")
    private List<LcmOperationType> operationTypes;
    @ApiModelProperty(name = "self", notes = "Match particular LCM operation state values as reported in notifications of type VnfLcmOperationOccurrenceNotification. May be present if the \"notificationTypes\" attribute contains the value \"VnfLcmOperationOccurrenceNotification\", and shall be absent otherwise.")
    private List<LcmOperationStateType> operationStates;

    public enum NotificationType {

        NsLcmOperationOccurrenceNotification,
        NsIdentifierCreationNotification,
        NsIdentifierDeletionNotification

    }
}
