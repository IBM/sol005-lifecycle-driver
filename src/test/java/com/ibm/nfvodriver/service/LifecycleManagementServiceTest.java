package com.ibm.nfvodriver.service;

import com.ibm.nfvodriver.config.NFVODriverProperties;
import com.ibm.nfvodriver.driver.NSLifecycleManagementDriver;
import com.ibm.nfvodriver.model.alm.ExecutionAcceptedResponse;
import com.ibm.nfvodriver.model.alm.ExecutionRequest;
import com.ibm.nfvodriver.model.alm.GenericExecutionRequestPropertyValue;
import com.ibm.nfvodriver.service.impl.JavascriptMessageConversionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static com.ibm.nfvodriver.test.TestConstants.TEST_DL_NO_AUTH;
import static com.ibm.nfvodriver.test.TestConstants.loadFileIntoString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class LifecycleManagementServiceTest {

    @Test
    public void testExecuteLifecycle() throws Exception {
        final NSLifecycleManagementDriver mockDriver = mock(NSLifecycleManagementDriver.class);
        final ExternalMessagingService mockExternalMessagingService = mock(ExternalMessagingService.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);
        final LifecycleManagementService lifecycleManagementService = new LifecycleManagementService(mockDriver, messageConversionService, mockExternalMessagingService, new NFVODriverProperties());

        when(mockDriver.createNsInstance(any(),any(),any())).thenReturn(loadFileIntoString("examples/NsInstance.json"));

        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Create");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);
        executionRequest.getResourceProperties().put("nsdId", new GenericExecutionRequestPropertyValue("fa2343af-2a81-4e84-a667-e40662e5ed93"));
        executionRequest.getResourceProperties().put("nsInstanceName", new GenericExecutionRequestPropertyValue("CSC-1"));
        executionRequest.getResourceProperties().put("additionalParams.nsPkgId", new GenericExecutionRequestPropertyValue("316aa140-c99a-4a08-b8f5-8e2cb73c83e8"));
        executionRequest.getResourceProperties().put("additionalParams.testProperty", new GenericExecutionRequestPropertyValue("TestValue"));
        // These properties should be ignored
        executionRequest.getResourceProperties().put("property1", new GenericExecutionRequestPropertyValue("value1"));
        executionRequest.getResourceProperties().put("property2", new GenericExecutionRequestPropertyValue("value2"));
        executionRequest.getResourceProperties().put("property3", new GenericExecutionRequestPropertyValue("value3"));

        final ExecutionAcceptedResponse executionAcceptedResponse = lifecycleManagementService.executeLifecycle(executionRequest);

        assertThat(executionAcceptedResponse).isNotNull();

        verify(mockExternalMessagingService).sendDelayedExecutionAsyncResponse(any(),any());
    }

    @Test
    public void testExecuteLifecycleInvalidLifecycleName() {
        final LifecycleManagementService lifecycleManagementService = new LifecycleManagementService(null, null, null, null);

        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Integrity");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);

        assertThatThrownBy(() -> lifecycleManagementService.executeLifecycle(executionRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Requested transition [Integrity] is not supported by this lifecycle driver");
    }

}