package com.ibm.nfvodriver.driver;

import com.ibm.nfvodriver.service.AuthenticatedRestTemplateService;
import com.ibm.nfvodriver.test.TestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RestClientTest({NSDManagementDriver.class, SOL005ResponseErrorHandler.class, AuthenticatedRestTemplateService.class})
@AutoConfigureWireMock(port = 0)
public class NSDManagementDriverTest {

    private static final String BASE_API_ROOT = "/nsd/v1";
    private static final String NS_DESCRIPTORS_ENDPOINT = BASE_API_ROOT + "/ns_descriptors";
    private static final String PNF_DESCRIPTORS_ENDPOINT = BASE_API_ROOT + "/pnf_descriptors";
    private final static String API_NSD_CONTENT = "/nsd_content";
    private final static String API_NSD = "/nsd";
    private final static String API_PNFD_CONTENT = "/pnfd_content";
    private final static String API_PNFD = "/pnfd";
    private final static String API_MANIFEST = "/manifest";
    private final static String API_ARTIFACTS = "/artifacts";
    private final static String API_ARTIFACTS_PATH = "/artifactsPath";
    private static final String SUBSCRIPTIONS_ENDPOINT = BASE_API_ROOT + "/subscriptions";

    @Autowired
    private NSDManagementDriver driver;
    @Autowired
    private AuthenticatedRestTemplateService authenticatedRestTemplateService;

    @Value("${wiremock.server.port}")
    private int wiremockServerPort;

    @Test
    public void testCreateNsDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withCreatedEntity(URI.create(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT))
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String createNsdInfoRequest = TestConstants.loadFileIntoString("examples/CreateNsdInfoRequest.json");

        final String nsdInfoResponse = driver.createNsDescriptor(TestConstants.TEST_DL_NO_AUTH, createNsdInfoRequest);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetNsDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdInfoResponse = driver.getNsDescriptor(TestConstants.TEST_DL_NO_AUTH);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetIndividualNsDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdInfoResponse = driver.getIndividualNsDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }



    @Test
    public void testPatchIndividualNsDescriptor() throws Exception {

        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID))
                .andExpect(method(HttpMethod.PATCH))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, "application/merge-patch+json"))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdInfpModificationsRequest = TestConstants.loadFileIntoString("examples/NsdInfoModifications.json");

        final String nsdInfoModificationsResponse = driver.patchIndividualNsDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID, nsdInfpModificationsRequest);

        assertThat(nsdInfoModificationsResponse).isNotNull();
    }


    @Test
    public void testDeleteIndividualNsDescriptor() {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID))
                .andExpect(method(HttpMethod.DELETE))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withNoContent());

        final String noContentResponse = driver.deleteIndividualNsDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(noContentResponse).isNull();
    }


    @Test
    public void testGetNsdArchiveContentDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_NSD_CONTENT))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getNsdArchiveContentDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testPutNsdArchiveContentDescriptor() {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_NSD_CONTENT))
                .andExpect(method(HttpMethod.PUT))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withNoContent());
        byte[] inputfile = new byte[10];
        final String noContentResponse = driver.putNsdArchiveContentDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID, inputfile);

        assertThat(noContentResponse).isNull();
    }

    @Test
    public void testGetNsdContent() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_NSD))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getNsdContent(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetNsdManifest() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_MANIFEST))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getNsdManifest(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetNsdArtifact() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + NS_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_ARTIFACTS +API_ARTIFACTS_PATH))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getNsdArtifacts(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID, API_ARTIFACTS_PATH);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testCreatePnfDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withCreatedEntity(URI.create(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT))
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String createPnfdInfoRequest = TestConstants.loadFileIntoString("examples/CreateNsdInfoRequest.json");

        final String nsdInfoResponse = driver.createPnfDescriptor(TestConstants.TEST_DL_NO_AUTH, createPnfdInfoRequest);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetPnfDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdInfoResponse = driver.getPnfDescriptor(TestConstants.TEST_DL_NO_AUTH);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetIndividualPnfDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdInfoResponse = driver.getIndividualPnfDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }



    @Test
    public void testPatchIndividualPnfDescriptor() throws Exception {

        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID))
                .andExpect(method(HttpMethod.PATCH))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, "application/merge-patch+json"))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/NsdInfo.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdInfpModificationsRequest = TestConstants.loadFileIntoString("examples/NsdInfoModifications.json");

        final String nsdInfoModificationsResponse = driver.patchIndividualPnfDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID, nsdInfpModificationsRequest);

        assertThat(nsdInfoModificationsResponse).isNotNull();
    }


    @Test
    public void testDeleteIndividualPnfDescriptor() {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID))
                .andExpect(method(HttpMethod.DELETE))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withNoContent());

        final String noContentResponse = driver.deleteIndividualPnfDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(noContentResponse).isNull();
    }


    @Test
    public void testGetPnfdArchiveContentDescriptor() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_PNFD_CONTENT))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getPnfdArchiveContentDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testPutPnfdArchiveContentDescriptor() {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_PNFD_CONTENT))
                .andExpect(method(HttpMethod.PUT))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withNoContent());
        byte[] inputfile = new byte[10];
        final String noContentResponse = driver.putPnfdArchiveContentDescriptor(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID, inputfile);

        assertThat(noContentResponse).isNull();
    }

    @Test
    public void testGetPnfdContent() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_PNFD))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getPnfdContent(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetPnfdManifest() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_MANIFEST))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getPnfdManifest(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID);

        assertThat(nsdInfoResponse).isNotNull();
    }

    @Test
    public void testGetPnfdArtifact() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + PNF_DESCRIPTORS_ENDPOINT + "/"+ TestConstants.TEST_NS_DESCRIPTORS_ID+API_ARTIFACTS +API_ARTIFACTS_PATH))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/nsdPackage-vMRF.zip"))
                        .contentType(MediaType.parseMediaType("application/zip")));

        final byte[] nsdInfoResponse = driver.getPnfdArtifacts(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NS_DESCRIPTORS_ID, API_ARTIFACTS_PATH);

        assertThat(nsdInfoResponse).isNotNull();
    }


    @Test
    public void testSubscriptions() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withCreatedEntity(URI.create(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT))
                        .body(TestConstants.loadFileIntoString("examples/NsdmSubscription.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdmSubscriptionResponse = driver.subscriptions(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NSDM_SUBSCRIPTION_REQUEST);

        assertThat(nsdmSubscriptionResponse).isNotNull();
    }


    @Test
    public void testQuerySubscriptions() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                    .body(TestConstants.loadFileIntoString("examples/NsdmSubscription.json"))
                    .contentType(MediaType.APPLICATION_JSON));

        final String nsdmSubscriptionResponse = driver.querySubscriptions(TestConstants.TEST_DL_NO_AUTH);

        assertThat(nsdmSubscriptionResponse).isNotNull();
    }


    @Test
    public void testQueryIndividualSubscriptions() throws Exception {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT + "/"+ TestConstants.TEST_NSD_SUBSCRIPTION_ID))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withSuccess()
                        .body(TestConstants.loadFileIntoString("examples/NsdmSubscription.json"))
                        .contentType(MediaType.APPLICATION_JSON));

        final String nsdmSubscriptionResponse = driver.queryIndividualSubscription(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NSD_SUBSCRIPTION_ID);

        assertThat(nsdmSubscriptionResponse).isNotNull();
    }

    @Test
    public void testDeleteIndividualSubscriptions() {
        final MockRestServiceServer server = MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate(TestConstants.TEST_DL_NO_AUTH)).build();

        server.expect(requestTo(TestConstants.TEST_SERVER_BASE_URL + SUBSCRIPTIONS_ENDPOINT + "/"+ TestConstants.TEST_NSD_SUBSCRIPTION_ID))
                .andExpect(method(HttpMethod.DELETE))
                .andExpect(header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andRespond(withNoContent());
        final String nsdmSubscriptionResponse = driver.deleteIndividualSubscription(TestConstants.TEST_DL_NO_AUTH, TestConstants.TEST_NSD_SUBSCRIPTION_ID);

        assertThat(nsdmSubscriptionResponse).isNull();
    }
}