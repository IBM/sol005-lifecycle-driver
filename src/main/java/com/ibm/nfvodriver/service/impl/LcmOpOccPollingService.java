package com.ibm.nfvodriver.service.impl;

import static com.ibm.nfvodriver.config.NFVODriverConstants.COMPLETED_OPERATIONAL_STATES;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import jakarta.annotation.PreDestroy;

import com.ibm.nfvodriver.service.ExternalMessagingService;
import org.etsi.sol005.lifecyclemanagement.LcmOperationStateType;
import org.etsi.sol005.lifecyclemanagement.VnfLcmOpOcc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.ibm.nfvodriver.driver.NSLifecycleManagementDriver;
import com.ibm.nfvodriver.model.LcmOpOccPollingRequest;
import com.ibm.nfvodriver.model.alm.ExecutionAsyncResponse;
import com.ibm.nfvodriver.model.alm.ExecutionStatus;
import com.ibm.nfvodriver.model.alm.FailureDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LcmOpOccPollingService {

    private static final Logger logger = LoggerFactory.getLogger(LcmOpOccPollingService.class);

    private final NSLifecycleManagementDriver driver;
    private final ExternalMessagingService externalMessagingService;
    private final ObjectMapper objectMapper;

    @Autowired
    public LcmOpOccPollingService(NSLifecycleManagementDriver driver, ExternalMessagingService externalMessagingService, ObjectMapper objectMapper) {
        logger.info("Creating Lifecycle Management Operation Occurrence Polling Service");
        this.driver = driver;
        this.externalMessagingService = externalMessagingService;
        this.objectMapper = objectMapper;
    }

    @PreDestroy
    public void close() {
        logger.info("Shutting down Lifecycle Management Operation Occurrence Polling Service...");
    }

    @KafkaListener(topics = "${nfvodriver.topics.lcmOpOccPollingTopic}")
    public void listenForLcmOpOccPollingRequestMessages(final String message) {
        try {
            // Deserialize message into LcmOpOccPollingRequest
            LcmOpOccPollingRequest lcmOpOccPollingRequest = objectMapper.readValue(message, LcmOpOccPollingRequest.class);
            UUID uuid = UUID.randomUUID();
            VnfLcmOpOcc vnfLcmOpOcc = driver.queryLifecycleOperationOccurrence(lcmOpOccPollingRequest.getDeploymentLocation(), lcmOpOccPollingRequest.getNsLcmOpOccId(), uuid.toString());
            if (COMPLETED_OPERATIONAL_STATES.contains(vnfLcmOpOcc.getOperationState())) {
                // Send back Async response to Brent
                final ExecutionAsyncResponse executionResponse;
                if (vnfLcmOpOcc.getOperationState() == LcmOperationStateType.COMPLETED) {
                    executionResponse = new ExecutionAsyncResponse(lcmOpOccPollingRequest.getNsLcmOpOccId(), ExecutionStatus.COMPLETE, null, Collections.emptyMap(), Collections.emptyMap());
                } else {
                    executionResponse = new ExecutionAsyncResponse(lcmOpOccPollingRequest.getNsLcmOpOccId(),
                                                                   ExecutionStatus.FAILED,
                                                                   new FailureDetails(FailureDetails.FailureCode.INFRASTRUCTURE_ERROR, vnfLcmOpOcc.getError().getDetail()),
                                                                   Collections.emptyMap(),Collections.emptyMap());
                }
                executionResponse.setTimestamp(System.currentTimeMillis());
                externalMessagingService.sendExecutionAsyncResponse(executionResponse);
            } else {
                // Keep waiting and re-enqueue polling request
                externalMessagingService.sendLcmOpOccPollingRequest(lcmOpOccPollingRequest);
            }
        } catch (Exception e) {
            logger.error("Exception caught processing LcmOpOccPollingRequest message", e);
        }
    }

}
