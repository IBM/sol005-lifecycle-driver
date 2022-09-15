package com.ibm.nfvodriver.web.etsi;


import com.ibm.nfvodriver.model.alm.ExecutionAsyncResponse;
import com.ibm.nfvodriver.model.alm.ExecutionStatus;
import com.ibm.nfvodriver.model.alm.FailureDetails;
import com.ibm.nfvodriver.service.ExternalMessagingService;
import io.swagger.annotations.ApiOperation;
import org.etsi.sol005.lifecyclemanagement.LcmOperationStateType;
import org.etsi.sol005.lifecyclemanagement.LifecycleManagementNotification;
import org.etsi.sol005.lifecyclemanagement.NsLcmOperationOccurrenceNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController("LifecycleNotificationController")
@RequestMapping("/nslcm/v2/notifications")
public class LifecycleNotificationController {

    private final static Logger logger = LoggerFactory.getLogger(LifecycleNotificationController.class);

    private final ExternalMessagingService externalMessagingService;

    @Autowired
    public LifecycleNotificationController(ExternalMessagingService externalMessagingService) {
        this.externalMessagingService = externalMessagingService;
    }

    @PostMapping
    @ApiOperation(value = "Receives a lifecycle operation occurrence notification from a VNFM", code = 204)
    public ResponseEntity<Void> receiveNotification(@RequestBody LifecycleManagementNotification notification) {
        // TODO This should be reduced to DEBUG level, but it assists in development testing to see all notification messages being received
        logger.info("Received notification:\n{}", notification);

        if (notification instanceof NsLcmOperationOccurrenceNotification) {
            final NsLcmOperationOccurrenceNotification nsLcmOpOccNotification = (NsLcmOperationOccurrenceNotification) notification;
            // Send an update if this is completed
            if (nsLcmOpOccNotification.getNotificationStatus() == NsLcmOperationOccurrenceNotification.NotificationStatus.RESULT){
                ExecutionAsyncResponse asyncResponse = new ExecutionAsyncResponse(nsLcmOpOccNotification.getNsLcmOpOccId(), ExecutionStatus.COMPLETE, null, Collections.emptyMap(), Collections.emptyMap());
                // If the operation state is anything other than COMPLETED, than assume we've failed (could be FAILED, FAILED_TEMP or ROLLED_BACK)
                if (nsLcmOpOccNotification.getOperationState() != LcmOperationStateType.COMPLETED) {
                    asyncResponse.setStatus(ExecutionStatus.FAILED);
                    // Set the failure details if we have an error message
                    if (nsLcmOpOccNotification.getError() != null && !StringUtils.isEmpty(nsLcmOpOccNotification.getError().getDetail())) {
                        asyncResponse.setFailureDetails(new FailureDetails(FailureDetails.FailureCode.INTERNAL_ERROR, nsLcmOpOccNotification.getError().getDetail()));
                    }
                }
                externalMessagingService.sendExecutionAsyncResponse(asyncResponse);
            }
        }

        return ResponseEntity.noContent().build();
    }

}
