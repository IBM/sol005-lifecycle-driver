package com.ibm.nfvodriver.web.alm;

import com.ibm.nfvodriver.driver.SOL005ResponseException;
import com.ibm.nfvodriver.model.alm.ExecutionAcceptedResponse;
import com.ibm.nfvodriver.model.alm.ExecutionRequest;
import com.ibm.nfvodriver.model.alm.FindReferenceResponse;
import com.ibm.nfvodriver.model.alm.GenericExecutionRequestPropertyValue;
import com.ibm.nfvodriver.model.web.ErrorInfo;
import com.ibm.nfvodriver.service.LifecycleManagementService;
import org.etsi.sol005.common.ProblemDetails;
import org.etsi.sol005.model.FindReferenceRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static com.ibm.nfvodriver.test.TestConstants.TEST_DL_NO_AUTH;
import static com.ibm.nfvodriver.test.TestConstants.TEST_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LifecycleControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private LifecycleManagementService lifecycleManagementService;

    @Test
    public void testExecuteLifecycle() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Create");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);
        executionRequest.getResourceProperties().put("nsdId", new GenericExecutionRequestPropertyValue("fa2343af-2a81-4e84-a667-e40662e5ed93"));
        executionRequest.getResourceProperties().put("nsInstanceName", new GenericExecutionRequestPropertyValue("CSCF-1"));
        executionRequest.getResourceProperties().put("additionalParams.nsPkgId", new GenericExecutionRequestPropertyValue("316aa140-c99a-4a08-b8f5-8e2cb73c83e8"));
        executionRequest.getResourceProperties().put("additionalParams.testProperty", new GenericExecutionRequestPropertyValue("TestValue"));
        when(lifecycleManagementService.executeLifecycle(any())).thenReturn(new ExecutionAcceptedResponse(UUID.randomUUID().toString()));

        final ResponseEntity<ExecutionAcceptedResponse> responseEntity = testRestTemplate.postForEntity("/api/driver/lifecycle/execute", executionRequest, ExecutionAcceptedResponse.class);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(responseEntity.getHeaders().getContentType()).isNotNull();
        assertThat(responseEntity.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getRequestId()).isNotEmpty();
    }

    @Test
    public void testExecuteLifecycleReturnsErrorInfo() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Install");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);

        when(lifecycleManagementService.executeLifecycle(any()))
                .thenThrow(new SOL005ResponseException("Received SOL005-compliant error when communicating with NFVO: " + TEST_EXCEPTION_MESSAGE, new ProblemDetails(404, TEST_EXCEPTION_MESSAGE)));

        final ResponseEntity<ErrorInfo> responseEntity = testRestTemplate.postForEntity("/api/driver/lifecycle/execute", executionRequest, ErrorInfo.class);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getHeaders().getContentType()).isNotNull();
        assertThat(responseEntity.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)).isTrue();
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getLocalizedMessage()).isEqualTo("Received SOL005-compliant error when communicating with NFVO: TestExceptionMessage");
        assertThat(responseEntity.getBody().getDetails().get("nfvoStatus")).isEqualTo(404);
        assertThat(responseEntity.getBody().getDetails().get("nfvoDetail")).isEqualTo(TEST_EXCEPTION_MESSAGE);
    }

    @Test
    public void testFindReferences() throws Exception {
        final FindReferenceRequest findReferenceRequest = new FindReferenceRequest();
        findReferenceRequest.setInstanceName("Instance1");

        final ResponseEntity<FindReferenceResponse> responseEntity = testRestTemplate.postForEntity("/api/driver/references/find", findReferenceRequest, FindReferenceResponse.class);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_IMPLEMENTED);
    }

}