package com.ibm.nfvodriver.service;

import com.ibm.nfvodriver.config.NFVODriverProperties;
import com.ibm.nfvodriver.driver.NSLifecycleManagementDriver;
import com.ibm.nfvodriver.model.alm.ExecutionAcceptedResponse;
import com.ibm.nfvodriver.model.alm.ExecutionAsyncResponse;
import com.ibm.nfvodriver.model.alm.ExecutionRequest;
import com.ibm.nfvodriver.model.alm.ExecutionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service("LifecycleManagementService")
public class LifecycleManagementService {

    private final static Logger logger = LoggerFactory.getLogger(LifecycleManagementService.class);

    private final NSLifecycleManagementDriver nsLifecycleManagementDriver;
    private final MessageConversionService messageConversionService;
    private final ExternalMessagingService externalMessagingService;
    private final NFVODriverProperties properties;

    @Autowired
    public LifecycleManagementService(NSLifecycleManagementDriver nsLifecycleManagementDriver, MessageConversionService messageConversionService, ExternalMessagingService externalMessagingService,
                                      NFVODriverProperties properties) {
        this.nsLifecycleManagementDriver = nsLifecycleManagementDriver;
        this.messageConversionService = messageConversionService;
        this.externalMessagingService = externalMessagingService;
        this.properties = properties;
    }

    public ExecutionAcceptedResponse executeLifecycle(ExecutionRequest executionRequest) throws MessageConversionException {
        logger.info("Processing execution request");

        try {
            if ("Create".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Generate CreateNSRequest message
                final String createNsRequest = messageConversionService.generateMessageFromRequest("CreateNsRequest", executionRequest);
                // Send message to NFVO
                final String nsInstanceResponse = nsLifecycleManagementDriver.createNsInstance(executionRequest.getDeploymentLocation(), createNsRequest);
                // Convert response into properties to be returned to ALM
                final Map<String, Object> outputs = messageConversionService.extractPropertiesFromMessage("NsInstance", executionRequest, nsInstanceResponse);
                final String requestId = UUID.randomUUID().toString();
                // Delay sending the asynchronous response (from a different thread) as this method needs to complete first (to send the response back to Brent)
                externalMessagingService.sendDelayedExecutionAsyncResponse(new ExecutionAsyncResponse(requestId, ExecutionStatus.COMPLETE, null, outputs, Collections.emptyMap()), properties.getExecutionResponseDelay());

                return new ExecutionAcceptedResponse(requestId);
            } else if ("Install".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Instantiate
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                final String instantiateNsRequest = messageConversionService.generateMessageFromRequest("InstantiateNsRequest", executionRequest);
                final String requestId = nsLifecycleManagementDriver.instantiateNs(executionRequest.getDeploymentLocation(), nsInstanceId, instantiateNsRequest);
                return new ExecutionAcceptedResponse(requestId);

            } else if ("Update".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Update
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                final String updateNsRequest = messageConversionService.generateMessageFromRequest("UpdateNsRequest", executionRequest);
                final String requestId = nsLifecycleManagementDriver.updateNs(executionRequest.getDeploymentLocation(), nsInstanceId, updateNsRequest);
                return new ExecutionAcceptedResponse(requestId);
            } else if ("Delete".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Delete
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                nsLifecycleManagementDriver.deleteNsInstance(executionRequest.getDeploymentLocation(), nsInstanceId);
                final String requestId = UUID.randomUUID().toString();
                externalMessagingService.sendDelayedExecutionAsyncResponse(new ExecutionAsyncResponse(requestId, ExecutionStatus.COMPLETE, null, Collections.emptyMap(), Collections.emptyMap()), properties.getExecutionResponseDelay());
                return new ExecutionAcceptedResponse(requestId);
            } else if ("Uninstall".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Terminate
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                final String terminateNsRequest = messageConversionService.generateMessageFromRequest("TerminateNsRequest", executionRequest);
                final String requestId = nsLifecycleManagementDriver.terminateNs(executionRequest.getDeploymentLocation(), nsInstanceId, terminateNsRequest);
                return new ExecutionAcceptedResponse(requestId);
            } else if ("Scale".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Scale
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                final String scaleNsRequest = messageConversionService.generateMessageFromRequest("ScaleNsRequest", executionRequest);
                final String requestId = nsLifecycleManagementDriver.scaleNs(executionRequest.getDeploymentLocation(), nsInstanceId, scaleNsRequest);
                return new ExecutionAcceptedResponse(requestId);
            }  else if ("ScaleOut".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Scale Out
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                final String scaleNsRequest = messageConversionService.generateMessageFromRequest("ScaleNsRequest", executionRequest);
                final String requestId = nsLifecycleManagementDriver.scaleNs(executionRequest.getDeploymentLocation(), nsInstanceId, scaleNsRequest);
                return new ExecutionAcceptedResponse(requestId);
            } else if ("ScaleIn".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Scale In
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                final String scaleNsRequest = messageConversionService.generateMessageFromRequest("ScaleNsRequest", executionRequest);
                final String requestId = nsLifecycleManagementDriver.scaleNs(executionRequest.getDeploymentLocation(), nsInstanceId, scaleNsRequest);
                return new ExecutionAcceptedResponse(requestId);
            } else if ("Heal".equalsIgnoreCase(executionRequest.getLifecycleName())) {
                // Heal
                final String nsInstanceId = executionRequest.getStringResourceProperty("nsInstanceId");
                final String healNsRequest = messageConversionService.generateMessageFromRequest("HealNsRequest", executionRequest);
                final String requestId = nsLifecycleManagementDriver.healNs(executionRequest.getDeploymentLocation(), nsInstanceId, healNsRequest);
                return new ExecutionAcceptedResponse(requestId);
            } else {
                throw new IllegalArgumentException(String.format("Requested transition [%s] is not supported by this lifecycle driver", executionRequest.getLifecycleName()));
            }
        } catch (MessageConversionException e) {
            logger.error("Error converting message", e);
            throw e;
        }
    }

}
