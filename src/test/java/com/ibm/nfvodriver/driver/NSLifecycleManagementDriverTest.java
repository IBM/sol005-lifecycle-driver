package com.ibm.nfvodriver.driver;


import com.ibm.common.utils.LoggingUtils;
import com.ibm.nfvodriver.service.AuthenticatedRestTemplateService;
import com.ibm.nfvodriver.test.TestConstants;
import org.etsi.sol005.lifecyclemanagement.LccnSubscription;
import org.etsi.sol005.lifecyclemanagement.LccnSubscriptionRequest;
import org.etsi.sol005.lifecyclemanagement.VnfLcmOpOcc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_ACCESS_TOKEN_URI;
import static com.ibm.nfvodriver.config.NFVODriverConstants.AUTHENTICATION_URL;
import java.io.IOException;
import java.net.URI;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest({NSLifecycleManagementDriver.class, SOL005ResponseErrorHandler.class, AuthenticatedRestTemplateService.class})
@AutoConfigureWireMock(port = 0)
public class NSLifecycleManagementDriverTest {

    private static final String BASE_API_ROOT = "/nslcm/v2";
    private static final String NS_INSTANCE_ENDPOINT = BASE_API_ROOT + "/ns_instances";
    private static final String LCM_OP_OCC_ENDPOINT = BASE_API_ROOT + "/ns_lcm_op_occs";
    private static final String SUBSCRIPTIONS_ENDPOINT = BASE_API_ROOT + "/subscriptions";
    private final static String API_PREFIX_OP_OCCURRENCES = "ns_lcm_op_occs";

    @Autowired
    private NSLifecycleManagementDriver driver;
    @Autowired
    private AuthenticatedRestTemplateService authenticatedRestTemplateService;

    @Value("${wiremock.server.port}")
    private int wiremockServerPort;

    @Test
    public void testCreateNsInstance() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withCreatedEntity(URI.create(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" + TestConstants.TEST_NS_INSTANCE_ID))
                        .body(TestConstants.loadFileIntoString("examples/NsInstance.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String createNsRequest = TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        final String nsInstanceResponse = driver.createNsInstance(TestConstants.TEST_DL_NO_AUTH, createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        assertThat(nsInstanceResponse).isNotNull();
    }


    @Test
    public void testStartNS() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" + TestConstants.TEST_NS_INSTANCE_ID + "/update"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID)));

        final String updateNsRequest = TestConstants.loadFileIntoString("examples/UpdateNsRequest-START.json");

        final String nsLcmOpOccId = driver.updateNs(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_INSTANCE_ID, updateNsRequest);

        assertThat(nsLcmOpOccId).isEqualTo(TestConstants.TEST_NS_LCM_OP_OCC_ID);
    }

    @Test
    public void testInstantiateNs() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID + "/instantiate")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(
                        TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID)));

        final String instantiateNsRequest =
                TestConstants.loadFileIntoString("examples/InstantiateNsRequest.json");

        final String nsLcmOpOccId = driver.instantiateNs(TestConstants.TEST_DL_NO_AUTH,
                TestConstants.TEST_NS_INSTANCE_ID, instantiateNsRequest);

        assertThat(nsLcmOpOccId).isEqualTo(TestConstants.TEST_NS_LCM_OP_OCC_ID);
    }

    @Test
    public void testCreateNsInstanceWithBasicAuth() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_BASIC_AUTH)).build();

        server.expect(requestTo(TestConstants.SECURE_TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header(HttpHeaders.AUTHORIZATION, TestConstants.BASIC_AUTHORIZATION_HEADER))
                .andRespond(withCreatedEntity(URI.create(TestConstants.SECURE_TEST_SERVER_BASE_URL +
                        NS_INSTANCE_ENDPOINT + "/" + TestConstants.TEST_NS_INSTANCE_ID))
                        .body(TestConstants.loadFileIntoString("examples/NsInstance.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String createNsRequest =
                TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        final String nsInstanceResponse = driver.createNsInstance(TestConstants.TEST_DL_BASIC_AUTH,
                createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        assertThat(nsInstanceResponse).isNotNull();
    }

    @Test
    public void testCreateNsInstanceWithOAuth2() throws Exception {
        TestConstants.TEST_DL_OAUTH2_AUTH.getProperties().put(AUTHENTICATION_ACCESS_TOKEN_URI, String.format("http://localhost:%s/oauth/token", wiremockServerPort));

        stubFor(post(urlEqualTo("/oauth/token"))
                        .withBasicAuth("LmClient", "pass123")
                        .withRequestBody(equalTo("grant_type=client_credentials"))
                        .willReturn(aResponse().withBody(TestConstants.TEST_ACCESS_TOKEN_RESPONSE).withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_OAUTH2_AUTH)).build();

        server.expect(requestTo(TestConstants.SECURE_TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
              .andExpect(method(HttpMethod.POST))
              .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
              .andExpect(header(HttpHeaders.AUTHORIZATION, "Bearer " + TestConstants.TEST_ACCESS_TOKEN))
              .andRespond(withCreatedEntity(URI.create(TestConstants.SECURE_TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" + TestConstants.TEST_NS_INSTANCE_ID))
                                  .body(TestConstants.loadFileIntoString("examples/NsInstance.json"))
                                  .contentType(MediaType.APPLICATION_JSON));

        final String createNsRequest = TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        final String nsInstanceResponse = driver.createNsInstance(TestConstants.TEST_DL_OAUTH2_AUTH, createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);
        
        

        assertThat(nsInstanceResponse).isNotNull();
    }

    @Test
    public void testCreateNsInstanceWithCookieAuth() throws Exception {
        TestConstants.TEST_DL_SESSION_AUTH.getProperties().put(AUTHENTICATION_URL, String.format("http://localhost:%s/login", wiremockServerPort));

        stubFor(post(urlEqualTo("/login"))
                        .withRequestBody(equalTo("IDToken1=Administrator&IDToken2=TestPassw0rd"))
                        .willReturn(aResponse().withHeader("Set-Cookie", TestConstants.TEST_SESSION_COOKIE)));

        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_SESSION_AUTH)).build();

        server.expect(requestTo(TestConstants.SECURE_TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
              .andExpect(method(HttpMethod.POST))
              .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
              .andExpect(header(HttpHeaders.COOKIE, TestConstants.TEST_SESSION_TOKEN))
              .andRespond(withCreatedEntity(URI.create(TestConstants.SECURE_TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" + TestConstants.TEST_NS_INSTANCE_ID))
                                  .body(TestConstants.loadFileIntoString("examples/NsInstance.json"))
                                  .contentType(MediaType.APPLICATION_JSON));

        final String createNsRequest = TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        final String nsInstanceResponse = driver.createNsInstance(TestConstants.TEST_DL_SESSION_AUTH, createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        assertThat(nsInstanceResponse).isNotNull();
    }


    @Test
    public void testCreateNsInstanceWithProblemDetails() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withServerError().body(TestConstants.loadFileIntoString(
                        "examples/ProblemDetails.json")).contentType(MediaType.APPLICATION_JSON));

        final String createNsRequest =
                TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        SOL005ResponseException exception = catchThrowableOfType(() ->
                        driver.createNsInstance(TestConstants.TEST_DL_NO_AUTH, createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID),
                SOL005ResponseException.class);

        assertThat(exception.getProblemDetails()).isNotNull();
        assertThat(exception.getProblemDetails().getStatus()).isEqualTo(HttpStatus.
                INTERNAL_SERVER_ERROR.value());
        assertThat(exception.getProblemDetails().getDetail()).
                isEqualTo("An error has occurred");
    }

    @Test
    public void testCreateNsInstanceWithUnknownException() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)).andRespond(withServerError());

        final String createNsRequest =
                TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        SOL005ResponseException exception = catchThrowableOfType(() ->
                        driver.createNsInstance(TestConstants.TEST_DL_NO_AUTH, createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID),
                SOL005ResponseException.class);

        assertThat(exception.getProblemDetails()).isNotNull();
        assertThat(exception.getProblemDetails().getStatus()).isEqualTo(HttpStatus.
                INTERNAL_SERVER_ERROR.value());
        assertThat(exception.getProblemDetails().getDetail()).
                isEqualTo("Internal Server Error");
    }

    @Test
    public void testCreateNsInstanceWithUnknownExceptionAndBody() throws
            Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withServerError().body(TestConstants.TEST_EXCEPTION_MESSAGE));

        final String createNsRequest =
                TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        SOL005ResponseException exception = catchThrowableOfType(() ->
                        driver.createNsInstance(TestConstants.TEST_DL_NO_AUTH, createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID),
                SOL005ResponseException.class);

        assertThat(exception.getProblemDetails()).isNotNull();
        assertThat(exception.getProblemDetails().getStatus()).isEqualTo(HttpStatus.
                INTERNAL_SERVER_ERROR.value());
        assertThat(exception.getProblemDetails().getDetail()).isEqualTo(LoggingUtils.getReasonPhrase(HttpStatus.
                INTERNAL_SERVER_ERROR.value()) + ": " + TestConstants.TEST_EXCEPTION_MESSAGE);
    }


    @Test
    public void testCreateNsInstanceWithInvalidSuccessCode() throws
            Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().body(TestConstants.loadFileIntoString("examples/NsInstance.json")
                ).contentType(MediaType.APPLICATION_JSON));

        final String createNsRequest =
                TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        final String nsInstanceResponse = driver.createNsInstance(TestConstants.TEST_DL_NO_AUTH,
                createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        assertThat(nsInstanceResponse).isNotNull();
    }

    @Test
    public void testCreateNsInstanceWithInvalidResponseCode() throws
            Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.MOVED_PERMANENTLY));

        final String createNsRequest =
                TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        assertThatThrownBy(() -> driver.createNsInstance(TestConstants.TEST_DL_NO_AUTH,
                createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID)).isInstanceOf(SOL005ResponseException.class)
                .hasMessage("Invalid status code [301 MOVED_PERMANENTLY] received");
    }

    @Test
    public void testCreateNsInstanceWithEmptyResponseBody() throws
            Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)).andRespond(withCreatedEntity(null));

        final String createNsRequest =
                TestConstants.loadFileIntoString("examples/CreateNsRequest.json");

        assertThatThrownBy(() -> driver.createNsInstance(TestConstants.TEST_DL_NO_AUTH,
                createNsRequest, TestConstants.TEST_NS_DRIVER_INSTANCE_ID)).isInstanceOf(SOL005ResponseException.class)
                .hasMessage("No response body");
    }

    @Test
    public void testDeleteNsInstance() {
        final MockRestServiceServer server
                =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID)).andExpect(method(HttpMethod.DELETE))
                .andRespond(withNoContent());

        driver.deleteNsInstance(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_INSTANCE_ID, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);
    }

    @Test
    public void testDeleteNsInstanceNotFound() {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID)).andExpect(method(HttpMethod.DELETE))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        SOL005ResponseException exception = catchThrowableOfType(() ->
                        driver.deleteNsInstance(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_INSTANCE_ID, TestConstants.TEST_NS_DRIVER_INSTANCE_ID),
                SOL005ResponseException.class);

        assertThat(exception.getProblemDetails()).isNotNull();
        assertThat(exception.getProblemDetails().getStatus()).isEqualTo(HttpStatus.
                NOT_FOUND.value());
        assertThat(exception.getProblemDetails().getDetail()).isEqualTo(LoggingUtils.getReasonPhrase(HttpStatus.
                NOT_FOUND.value()));
    }

    @Test
    public void testDeleteNsInstanceFailed() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID)).andExpect(method(HttpMethod.DELETE))
                .andRespond(withServerError().body(TestConstants.loadFileIntoString(
                        "examples/ProblemDetails.json")).contentType(MediaType.APPLICATION_JSON));

        SOL005ResponseException exception = catchThrowableOfType(() ->
                        driver.deleteNsInstance(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_INSTANCE_ID, TestConstants.TEST_NS_DRIVER_INSTANCE_ID),
                SOL005ResponseException.class);

        assertThat(exception.getProblemDetails()).isNotNull();
        assertThat(exception.getProblemDetails().getStatus()).isEqualTo(HttpStatus.
                INTERNAL_SERVER_ERROR.value());
        assertThat(exception.getProblemDetails().getDetail()).
                isEqualTo("An error has occurred");
    }

    @Test
    public void testInstantiateNsNoLocationHeaderReturned() throws
            Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID + "/instantiate")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED));

        final String instantiateNsRequest =
                TestConstants.loadFileIntoString("examples/InstantiateNsRequest.json");

        SOL005ResponseException exception = catchThrowableOfType(() ->
                driver.instantiateNs(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_INSTANCE_ID,
                        instantiateNsRequest), SOL005ResponseException.class);

        assertThat(exception.getProblemDetails()).isNotNull();
        assertThat(exception.getProblemDetails().getStatus()).isEqualTo(
                SOL005ResponseException.DEFAULT_STATUS_VALUE);
        assertThat(exception.getProblemDetails().getDetail()).
                isEqualTo("No Location header found");
    }

    @Test
    public void testInstantiateNsWithProblemDetails() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID + "/instantiate")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withServerError().body(TestConstants.loadFileIntoString(
                        "examples/ProblemDetails.json")).contentType(MediaType.APPLICATION_JSON));

        final String instantiateNsRequest =
                TestConstants.loadFileIntoString("examples/InstantiateNsRequest.json");

        SOL005ResponseException exception = catchThrowableOfType(() ->
                driver.instantiateNs(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_INSTANCE_ID,
                        instantiateNsRequest), SOL005ResponseException.class);

        assertThat(exception.getProblemDetails()).isNotNull();
        assertThat(exception.getProblemDetails().getStatus()).isEqualTo(HttpStatus.
                INTERNAL_SERVER_ERROR.value());
        assertThat(exception.getProblemDetails().getDetail()).
                isEqualTo("An error has occurred");
    }

    @Test
    public void testScaleNs() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID + "/scale")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(
                        TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID)));

        final String scaleNsRequest =
                TestConstants.loadFileIntoString("examples/ScaleNsRequest.json");

        final String nsLcmOpOccId = driver.scaleNs(TestConstants.TEST_DL_NO_AUTH,
                TestConstants.TEST_NS_INSTANCE_ID, scaleNsRequest);

        assertThat(nsLcmOpOccId).isEqualTo(TestConstants.TEST_NS_LCM_OP_OCC_ID);
    }

    @Test
    public void testStartNs() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID + "/update")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(
                        TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID)));

        final String updateNsRequest =
                TestConstants.loadFileIntoString("examples/UpdateNsRequest-STOP.json");

        final String nsLcmOpOccId = driver.updateNs(TestConstants.TEST_DL_NO_AUTH,
                TestConstants.TEST_NS_INSTANCE_ID, updateNsRequest);

        assertThat(nsLcmOpOccId).isEqualTo(TestConstants.TEST_NS_LCM_OP_OCC_ID);
    }

    @Test
    public void testStopNs() throws Exception {
        final MockRestServiceServer
                server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID + "/update")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(
                        TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID)));

        final String updateNsRequest =
                TestConstants.loadFileIntoString("examples/UpdateNsRequest-STOP.json");

        final String nsLcmOpOccId = driver.updateNs(TestConstants.TEST_DL_NO_AUTH,
                TestConstants.TEST_NS_INSTANCE_ID, updateNsRequest);

        assertThat(nsLcmOpOccId).isEqualTo(TestConstants.TEST_NS_LCM_OP_OCC_ID);
    }

    @Test
    public void testCreateLifecycleSubscription() throws Exception {
        final MockRestServiceServer server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withCreatedEntity(URI.create(TestConstants.TEST_SERVER_BASE_URL +
                        SUBSCRIPTIONS_ENDPOINT + "/" + TestConstants.TEST_LCCN_SUBSCRIPTION_ID))
                        .body(TestConstants.loadFileIntoString("examples/LccnSubscription.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final LccnSubscriptionRequest lccnSubscriptionRequest = new
                LccnSubscriptionRequest();
        lccnSubscriptionRequest.setCallbackUri(TestConstants.NOTIFICATIONS_ENDPOINT);

        final LccnSubscription lccnSubscription =
                driver.createLifecycleSubscription(TestConstants.TEST_DL_NO_AUTH, lccnSubscriptionRequest);

        assertThat(lccnSubscription).isNotNull();
        assertThat(lccnSubscription.getId()).isEqualTo(TestConstants.TEST_LCCN_SUBSCRIPTION_ID);
        assertThat(lccnSubscription.getCallbackUri()).isEqualTo(
                TestConstants.NOTIFICATIONS_ENDPOINT);
    }

    @Test
    public void testHealNs() throws Exception {
        final MockRestServiceServer
                server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" +
                        TestConstants.TEST_NS_INSTANCE_ID + "/heal")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(
                        TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID)));

        final String healNsRequest =
                TestConstants.loadFileIntoString("examples/HealNsRequest.json");

        final String nsLcmOpOccId = driver.healNs(TestConstants.TEST_DL_NO_AUTH,
                TestConstants.TEST_NS_INSTANCE_ID, healNsRequest);

        assertThat(nsLcmOpOccId).isEqualTo(TestConstants.TEST_NS_LCM_OP_OCC_ID);
    }

    @Test
    public void testTerminateNs() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();
        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT + "/" + TestConstants.TEST_NS_INSTANCE_ID + "/terminate"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID)));

        final String terminateNsRequest = TestConstants.loadFileIntoString("examples/TerminateNsRequest.json");

        final String nsLcmOpOccId = driver.terminateNs(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_INSTANCE_ID, terminateNsRequest);

        assertThat(nsLcmOpOccId).isEqualTo(TestConstants.TEST_NS_LCM_OP_OCC_ID);
    }

    @Test
    public void testQueryAllLifecycleOperationOccurrences() throws IOException {

        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();
        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + BASE_API_ROOT + "/" + API_PREFIX_OP_OCCURRENCES))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().body(TestConstants.loadFileIntoString(
                        "examples/NsLcmOpOcc.json")).contentType(MediaType.APPLICATION_JSON));
        final String response = driver.queryAllLifecycleOperationOccurrences(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);
        assertThat(response);
    }

    @Test
    public void testQueryLifecycleOperationOccurrence() throws IOException {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + BASE_API_ROOT + "/" + API_PREFIX_OP_OCCURRENCES + "/nsLcmOpOccId"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().body(TestConstants.loadFileIntoString(
                        "examples/LccnSubscription.json")).contentType(MediaType.APPLICATION_JSON));
        final VnfLcmOpOcc response = driver.queryLifecycleOperationOccurrence(TestConstants.TEST_DL_NO_AUTH, "nsLcmOpOccId", TestConstants.TEST_NS_DRIVER_INSTANCE_ID);
        assertThat(response);
        server.verify();
    }

    @Test
    public void testNsLcmOperationsOccurrencesRetry() throws IOException {

        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + BASE_API_ROOT + "/" + API_PREFIX_OP_OCCURRENCES + "/nsLcmOpOccId/retry"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON));
        driver.nsLcmOperationsOccurrencesRetry(TestConstants.TEST_DL_NO_AUTH, "nsLcmOpOccId", TestConstants.TEST_NS_DRIVER_INSTANCE_ID);
        server.verify();
    }

    @Test
    public void testNsLcmOperationsOccurrencesRollback() throws Exception {

        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + BASE_API_ROOT + "/" + API_PREFIX_OP_OCCURRENCES + "/nsLcmOpOccId/rollback"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON));
        driver.nsLcmOperationsOccurrencesRollback(TestConstants.TEST_DL_NO_AUTH, "nsLcmOpOccId", TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        assertThat(HttpStatus.ACCEPTED);
        server.verify();
    }

    @Test
    public void testNsLcmOperationsOccurrencesContinue() throws SOL005ResponseException {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + BASE_API_ROOT + "/" + API_PREFIX_OP_OCCURRENCES + "/nsLcmOpOccId/continue"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON));
        driver.nsLcmOperationsOccurrencesContinue(TestConstants.TEST_DL_NO_AUTH, "nsLcmOpOccId", TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        assertThat(HttpStatus.ACCEPTED);
        server.verify();
    }

    @Test
    public void testNsLcmOperationsOccurrencesFail() throws SOL005ResponseException, IOException {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + BASE_API_ROOT + "/" + API_PREFIX_OP_OCCURRENCES + "/nsLcmOpOccId/fail"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().body(TestConstants.loadFileIntoString(
                        "examples/NsLcmOpOcc.json")).contentType(MediaType.APPLICATION_JSON));
        final String response = driver.nsLcmOperationsOccurrencesFail(TestConstants.TEST_DL_NO_AUTH, "nsLcmOpOccId", TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        assertThat(response);
        server.verify();
    }

    @Test
    public void testNsLcmOperationsOccurrencesCancel() throws IOException {

        final MockRestServiceServer
                server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();
        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + BASE_API_ROOT + "/" + API_PREFIX_OP_OCCURRENCES + "/" +
                        TestConstants.TEST_NS_LCM_OP_OCC_ID + "/cancel")).andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withStatus(HttpStatus.ACCEPTED).location(URI.create(
                        TestConstants.TEST_SERVER_BASE_URL + LCM_OP_OCC_ENDPOINT + "/" + TestConstants.TEST_NS_LCM_OP_OCC_ID + "/" + "cancel")));

        final String cancelMode =
                TestConstants.loadFileIntoString("examples/CancelMode.json");

        driver.nsLcmOperationsOccurrencesCancel(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_LCM_OP_OCC_ID, cancelMode, TestConstants.TEST_NS_DRIVER_INSTANCE_ID);

        server.verify();
    }

    @Test
    public void testQueryAllLifecycleSubscriptions() throws IOException {

        final MockRestServiceServer
                server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();
        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT)).andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().body(TestConstants.loadFileIntoString(
                        "examples/LccnSubscription.json")).contentType(MediaType.APPLICATION_JSON));

        final String lccnSubscriptionRequest =
                TestConstants.loadFileIntoString("examples/LccnSubscriptionRequest.json");

        final String response = driver.queryAllLifecycleSubscriptions(TestConstants.TEST_DL_NO_AUTH, lccnSubscriptionRequest);
        assertThat(response);
        server.verify();

    }

    @Test
    public void testQueryLifecycleSubscription() throws IOException {
        final MockRestServiceServer
                server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT + "/" + "subscriptionId")).andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().body(TestConstants.loadFileIntoString(
                        "examples/LccnSubscription.json")).contentType(MediaType.APPLICATION_JSON));

        final String response = driver.queryLifecycleSubscription(TestConstants.TEST_DL_NO_AUTH, "subscriptionId");
        assertThat(response);
        server.verify();

    }

    @Test
    public void testDeleteLifecycleSubscription() throws IOException {

        final MockRestServiceServer
                server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();
        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT + "/" + "subscriptionId")).andExpect(method(HttpMethod.DELETE))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().contentType(MediaType.APPLICATION_JSON));
        driver.deleteLifecycleSubscription(TestConstants.TEST_DL_NO_AUTH, "subscriptionId");
        server.verify();
    }

    @Test
    public void testGetNsInstance() throws IOException {

        final MockRestServiceServer
                server =
                MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
                        (TestConstants.TEST_DL_NO_AUTH)).build();
        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_INSTANCE_ENDPOINT)).andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess().body(TestConstants.loadFileIntoString(
                        "examples/NsInstance.json")).contentType(MediaType.APPLICATION_JSON));
        final String response = driver.getNsInstance(TestConstants.TEST_DL_NO_AUTH);
        assertThat(response);
        server.verify();

    }
}