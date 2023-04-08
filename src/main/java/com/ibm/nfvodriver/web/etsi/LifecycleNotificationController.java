package com.ibm.nfvodriver.web.etsi;


import com.ibm.common.utils.LoggingUtils;
import com.ibm.nfvodriver.model.MessageDirection;
import com.ibm.nfvodriver.model.MessageType;
import com.ibm.nfvodriver.model.alm.ExecutionAsyncResponse;
import com.ibm.nfvodriver.model.alm.ExecutionStatus;
import com.ibm.nfvodriver.model.alm.FailureDetails;
import com.ibm.nfvodriver.service.ExternalMessagingService;
import com.ibm.nfvodriver.utils.RequestResponseLogUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.etsi.sol005.lifecyclemanagement.LcmOperationStateType;
import org.etsi.sol005.lifecyclemanagement.LifecycleManagementNotification;
import org.etsi.sol005.lifecyclemanagement.NsLcmOperationOccurrenceNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController("LifecycleNotificationController")
@RequestMapping(LifecycleNotificationController.NOTIFICATIONS_URI)
public class LifecycleNotificationController {

    private final static Logger logger = LoggerFactory.getLogger(LifecycleNotificationController.class);
    public final static String NOTIFICATIONS_URI = "/nslcm/v2/notifications";
    public final static String LOG_URI_PREFIX = "...";
    private final ExternalMessagingService externalMessagingService;

    @Autowired
    public LifecycleNotificationController(ExternalMessagingService externalMessagingService) {
        this.externalMessagingService = externalMessagingService;
    }

    @PostMapping
    @Operation(summary = "Receives a lifecycle operation occurrence notification from a VNFM")
    public ResponseEntity<Void> receiveNotification(@RequestBody LifecycleManagementNotification notification, @RequestHeader HttpHeaders headers) {
        // TODO This should be reduced to DEBUG level, but it assists in development testing to see all notification messages being received
        logger.info("Received notification:\n{}", notification);
        UUID uuid = UUID.randomUUID();

        if (notification instanceof NsLcmOperationOccurrenceNotification) {
            final NsLcmOperationOccurrenceNotification nsLcmOpOccNotification = (NsLcmOperationOccurrenceNotification) notification;
            LoggingUtils.logEnabledMDC(RequestResponseLogUtils.convertToJson(nsLcmOpOccNotification), MessageType.REQUEST, MessageDirection.RECEIVED, uuid.toString(), MediaType.APPLICATION_JSON_VALUE, "http",
                    RequestResponseLogUtils.getRequestReceivedProtocolMetaData(LOG_URI_PREFIX+NOTIFICATIONS_URI, HttpMethod.POST.name(), headers), nsLcmOpOccNotification.getNsLcmOpOccId());
            // Send an update if this is completed
            if (nsLcmOpOccNotification.getNotificationStatus() == NsLcmOperationOccurrenceNotification.NotificationStatus.RESULT){
                ExecutionAsyncResponse asyncResponse = new ExecutionAsyncResponse(nsLcmOpOccNotification.getNsLcmOpOccId(), ExecutionStatus.COMPLETE, null, Collections.emptyMap(), Collections.emptyMap());
                // If the operation state is anything other than COMPLETED, than assume we've failed (could be FAILED, FAILED_TEMP or ROLLED_BACK)
                if (nsLcmOpOccNotification.getOperationState() != LcmOperationStateType.COMPLETED) {
                    asyncResponse.setStatus(ExecutionStatus.FAILED);
                    // Set the failure details if we have an error message
                    if (nsLcmOpOccNotification.getError() != null && StringUtils.hasLength(nsLcmOpOccNotification.getError().getDetail())) {
                        asyncResponse.setFailureDetails(new FailureDetails(FailureDetails.FailureCode.INTERNAL_ERROR, nsLcmOpOccNotification.getError().getDetail()));
                    }
                }
                externalMessagingService.sendExecutionAsyncResponse(asyncResponse);
            }
            LoggingUtils.logEnabledMDC("", MessageType.RESPONSE, MessageDirection.SENT, uuid.toString(), "", "http",
                    RequestResponseLogUtils.getResponseSentProtocolMetaData(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase(), null), nsLcmOpOccNotification.getNsLcmOpOccId());
        }

        return ResponseEntity.noContent().build();
    }

}
