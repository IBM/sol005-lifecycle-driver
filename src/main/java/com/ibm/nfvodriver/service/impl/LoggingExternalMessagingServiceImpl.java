package com.ibm.nfvodriver.service.impl;

import com.ibm.nfvodriver.model.LcmOpOccPollingRequest;
import com.ibm.nfvodriver.model.alm.ExecutionAsyncResponse;
import com.ibm.nfvodriver.service.ExternalMessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LoggingExternalMessagingServiceImpl implements ExternalMessagingService {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExternalMessagingServiceImpl.class);

    @Override public void sendExecutionAsyncResponse(ExecutionAsyncResponse request) {
        logger.info("Would send: {}", request);
    }

    @Override public void sendDelayedExecutionAsyncResponse(ExecutionAsyncResponse request, Duration delay) {
        logger.info("Would send delayed message after {} seconds: {}", delay.getSeconds(), request);
    }

    @Override public void sendLcmOpOccPollingRequest(LcmOpOccPollingRequest request) {
        logger.info("Would submit request to poll for Lccn request [{}]", request.getNsLcmOpOccId());
    }

}
