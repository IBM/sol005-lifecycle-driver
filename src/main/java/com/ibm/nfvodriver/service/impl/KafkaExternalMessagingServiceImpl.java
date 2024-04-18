package com.ibm.nfvodriver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.nfvodriver.config.NFVODriverProperties;
import com.ibm.nfvodriver.model.LcmOpOccPollingRequest;
import com.ibm.nfvodriver.model.alm.ExecutionAsyncResponse;
import com.ibm.nfvodriver.service.ExternalMessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
// import org.springframework.util.concurrent.ListenableFuture;
import java.util.concurrent.CompletableFuture;

import java.time.Duration;


public class KafkaExternalMessagingServiceImpl implements ExternalMessagingService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaExternalMessagingServiceImpl.class);

    private final NFVODriverProperties properties;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaExternalMessagingServiceImpl(NFVODriverProperties properties, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.properties = properties;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override public void sendExecutionAsyncResponse(ExecutionAsyncResponse request) {
        try {
            final String message = objectMapper.writeValueAsString(request);
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(properties.getTopics().getLifecycleResponsesTopic(), message);

            // future.addCallback(sendResult -> logger.debug("ExecutionAsyncResponse successfully sent"),
            //                    exception -> logger.warn("Exception sending ExecutionAsyncResponse", exception));

            future.thenAccept(sendResult -> logger.debug("ExecutionAsyncResponse successfully sent"))
                  .exceptionally(exception -> { logger.warn("Exception sending ExecutionAsyncResponse", exception);return null; });


        } catch (JsonProcessingException e) {
            logger.warn("Exception generating message text from ExecutionAsyncResponse", e);
        }
    }

    @Override
    @Async
    public void sendDelayedExecutionAsyncResponse(ExecutionAsyncResponse request, Duration delay) {
        if (delay != null) {
            try {
                Thread.sleep(delay.toMillis());
            } catch (InterruptedException e) {
                logger.error("Thread interrupted during sleep", e);
            }
        }
        sendExecutionAsyncResponse(request);
    }

    @Override public void sendLcmOpOccPollingRequest(LcmOpOccPollingRequest request) {
        try {
            try {
                Thread.sleep(properties.getLcmOpOccPollingDelay().toMillis());
            } catch (InterruptedException e) {
                logger.error("Thread interrupted during sleep", e);
            }
            final String message = objectMapper.writeValueAsString(request);
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(properties.getTopics().getLcmOpOccPollingTopic(), message);

            // future.addCallback(sendResult -> logger.debug("Submitted request to poll for LcmOpOcc [{}]", request.getNsLcmOpOccId()),
            //                    exception -> logger.warn("Exception sending LcmOpOccPollingRequest", exception));

            future.thenAccept(sendResult -> logger.debug("Submitted request to poll for LcmOpOcc [{}]", request.getNsLcmOpOccId()))
                  .exceptionally(exception -> { logger.warn("Exception sending LcmOpOccPollingRequest", exception);return null; });
             
        } catch (JsonProcessingException e) {
            logger.warn("Exception generating message text from LcmOpOccPollingRequest", e);
        }
    }

}
