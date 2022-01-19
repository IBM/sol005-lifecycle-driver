/**
 *  This file is not used currently and kept it for future requirements if any
 */


/*
 * package com.ibm.nfvodriver.driver.impl;
 * 
 * import com.ibm.nfvodriver.config.NFVODriverConstants; import
 * com.ibm.nfvodriver.config.NFVODriverProperties; import
 * com.ibm.nfvodriver.driver.NSPackageNotFoundException; import
 * com.ibm.nfvodriver.driver.NSPackageRepositoryDriver; import
 * com.ibm.nfvodriver.driver.NSPackageRepositoryException; import
 * com.ibm.nfvodriver.driver.SOL005ResponseException; import
 * com.ibm.nfvodriver.model.AuthenticationType; import
 * com.ibm.nfvodriver.service.AuthenticatedRestTemplateService; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.google.common.io.ByteStreams; import
 * org.etsi.sol005.packagemanagement.NsPkgInfo; import org.junit.Test; import
 * org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.core.io.Resource; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.context.ActiveProfiles; import
 * org.springframework.test.context.junit4.SpringRunner; import
 * org.springframework.test.web.client.MockRestServiceServer;
 * 
 * import java.util.List;
 * 
 * import static com.ibm.nfvodriver.test.TestConstants.loadFileIntoString;
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.assertj.core.api.Assertions.assertThatThrownBy; import static
 * org.springframework.test.web.client.ExpectedCount.between; import static
 * org.springframework.test.web.client.ExpectedCount.times; import static
 * org.springframework.test.web.client.match.MockRestRequestMatchers.method;
 * import static
 * org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
 * import static
 * org.springframework.test.web.client.response.MockRestResponseCreators.
 * withSuccess; import static
 * org.springframework.test.web.client.response.MockRestResponseCreators.
 * withUnauthorizedRequest;
 * 
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * 
 * @ActiveProfiles({ "test" }) public class NexusNsPackageRepositoryDriverTest {
 * 
 * public static final String EMPTY_SEARCH_RESULTS =
 * "{\"items\": [], \"continuationToken\": null}";
 * 
 * @Autowired private NSPackageRepositoryDriver nsPackageDriver;
 * 
 * @Autowired private NFVODriverProperties nfvoDriverProperties;
 * 
 * @Autowired private AuthenticatedRestTemplateService
 * authenticatedRestTemplateService;
 * 
 * @Autowired private ObjectMapper objectMapper;
 * 
 * private MockRestServiceServer getMockRestServiceServer(NFVODriverProperties
 * nfvoDriverProperties) { return
 * MockRestServiceServer.bindTo(authenticatedRestTemplateService.getRestTemplate
 * (nfvoDriverProperties.getPackageManagement().getPackageRepositoryUrl(),
 * nfvoDriverProperties.getPackageManagement().getAuthenticationProperties())).
 * build(); }
 * 
 * @Test public void testGetNSPackage() throws Exception { final
 * MockRestServiceServer server =
 * getMockRestServiceServer(nfvoDriverProperties);
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search/assets?repository=test-repository&keyword=*vMRF.zip*"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString(
 * "examples/pkgMgmt-AssetSearch.json"), MediaType.APPLICATION_JSON));
 * 
 * Resource nsPackage = nsPackageDriver.getNsPackage("vMRF");
 * assertThat(ByteStreams.toByteArray(nsPackage.getInputStream())).isNotEmpty();
 * }
 * 
 * @Test public void testGetNSPackageNotFound() { final MockRestServiceServer
 * server = getMockRestServiceServer(nfvoDriverProperties);
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search/assets?repository=test-repository&keyword=*not-present-id.zip*"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(EMPTY_SEARCH_RESULTS, MediaType.APPLICATION_JSON));
 * 
 * assertThatThrownBy(() -> nsPackageDriver.getNsPackage("not-present-id"))
 * .isInstanceOf(NSPackageNotFoundException.class)
 * .hasMessage("NS Package [not-present-id] not found in repository [http://does-not-exist:8081]"
 * ); }
 * 
 * @Test public void testGetNSPackageNoConfiguredRepoUrl() {
 * NFVODriverProperties properties = new NFVODriverProperties();
 * properties.getPackageManagement().setPackageRepositoryUrl(null);
 * NSPackageRepositoryDriver nsPackageDriver = new
 * NexusNSPackageRepositoryDriver(properties, authenticatedRestTemplateService,
 * objectMapper);
 * 
 * assertThatThrownBy(() -> nsPackageDriver.getNsPackage("vMRF"))
 * .isInstanceOf(NSPackageRepositoryException.class)
 * .hasMessageStartingWith("A valid NS Package Repository URL must be configured."
 * ); }
 * 
 * @Test public void testQueryNSPkgInfo() throws Exception { final
 * MockRestServiceServer server =
 * getMockRestServiceServer(nfvoDriverProperties);
 * 
 * server.expect(times(2), requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search?repository=test-repository&group=/group/NSdId"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString(
 * "examples/pkgMgmt-ComponentSearch.json"), MediaType.APPLICATION_JSON));
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/repository/test-repository/group/NSdId/NSPackageId.pkgInfo"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString("examples/nsPackageId.pkgInfo"),
 * MediaType.TEXT_PLAIN));
 * 
 * // Create new instance of the driver to ensure we don't get accidental cache
 * hits) final NSPackageRepositoryDriver nsPackageDriver = new
 * NexusNSPackageRepositoryDriver(nfvoDriverProperties,
 * authenticatedRestTemplateService, objectMapper);
 * 
 * List<NsPkgInfo> nsPkgInfoList =
 * nsPackageDriver.queryAllNsPkgInfos("/group/NSdId");
 * assertThat(nsPkgInfoList).hasSize(1);
 * 
 * // Query for a second time and check we don't re-fetch the NSPkgInfo (should
 * be cached) nsPkgInfoList =
 * nsPackageDriver.queryAllNsPkgInfos("/group/NSdId");
 * assertThat(nsPkgInfoList).hasSize(1);
 * 
 * // Verify all expectations met server.verify(); }
 * 
 * @Test public void testQueryNSPkgInfoNoGroupName() throws Exception { final
 * MockRestServiceServer server =
 * getMockRestServiceServer(nfvoDriverProperties);
 * 
 * server.expect(times(2), requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search?repository=test-repository"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString(
 * "/examples/pkgMgmt-ComponentSearch.json"), MediaType.APPLICATION_JSON));
 * 
 * // This is optional due to the potential caching within the driver
 * server.expect(between(0, 1), requestTo(
 * "http://does-not-exist:8081/repository/test-repository/group/NSdId/NSPackageId.pkgInfo"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString("/examples/nsPackageId.pkgInfo"),
 * MediaType.TEXT_PLAIN));
 * 
 * List<NsPkgInfo> nsPkgInfoList = nsPackageDriver.queryAllNsPkgInfos("");
 * assertThat(nsPkgInfoList).hasSize(1);
 * 
 * // Query for a second time and check we don't re-fetch the NsPkgInfo (should
 * be cached) nsPkgInfoList = nsPackageDriver.queryAllNsPkgInfos(null);
 * assertThat(nsPkgInfoList).hasSize(1);
 * 
 * // Verify all expectations met server.verify(); }
 * 
 * @Test public void testQueryNSPkgInfoWithAuthentication() throws Exception {
 * NFVODriverProperties properties = new NFVODriverProperties();
 * properties.getPackageManagement().setPackageRepositoryUrl(
 * "http://does-not-exist:8081");
 * properties.getPackageManagement().setRepositoryName("test-repository");
 * properties.getPackageManagement().getAuthenticationProperties().put(
 * NFVODriverConstants.AUTHENTICATION_TYPE,
 * AuthenticationType.BASIC.toString());
 * properties.getPackageManagement().getAuthenticationProperties().put(
 * NFVODriverConstants.AUTHENTICATION_USERNAME, "admin");
 * properties.getPackageManagement().getAuthenticationProperties().put(
 * NFVODriverConstants.AUTHENTICATION_PASSWORD, "admin123"); final
 * MockRestServiceServer server = getMockRestServiceServer(properties);
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search?repository=test-repository&group=/group/NSdId"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString(
 * "/examples/pkgMgmt-ComponentSearch.json"), MediaType.APPLICATION_JSON));
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/repository/test-repository/group/NSdId/NSPackageId.pkgInfo"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString("/examples/nsPackageId.pkgInfo"),
 * MediaType.TEXT_PLAIN));
 * 
 * NSPackageRepositoryDriver nsPackageDriver = new
 * NexusNSPackageRepositoryDriver(properties, authenticatedRestTemplateService,
 * objectMapper);
 * 
 * List<NsPkgInfo> nsPkgInfoList =
 * nsPackageDriver.queryAllNsPkgInfos("/group/NSdId");
 * assertThat(nsPkgInfoList).hasSize(1); }
 * 
 * @Test public void testQueryNSPkgInfoWithAuthenticationFailure() {
 * NFVODriverProperties properties = new NFVODriverProperties();
 * properties.getPackageManagement().setPackageRepositoryUrl(
 * "http://does-not-exist:8081");
 * properties.getPackageManagement().setRepositoryName("test-repository");
 * properties.getPackageManagement().getAuthenticationProperties().put(
 * NFVODriverConstants.AUTHENTICATION_TYPE,
 * AuthenticationType.BASIC.toString());
 * properties.getPackageManagement().getAuthenticationProperties().put(
 * NFVODriverConstants.AUTHENTICATION_USERNAME, "jack");
 * properties.getPackageManagement().getAuthenticationProperties().put(
 * NFVODriverConstants.AUTHENTICATION_PASSWORD, "jack"); final
 * MockRestServiceServer server = getMockRestServiceServer(properties);
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search?repository=test-repository&group=/group/nsdId"
 * )).andExpect(method(HttpMethod.GET)) .andRespond(withUnauthorizedRequest());
 * 
 * NSPackageRepositoryDriver nsPackageDriver = new
 * NexusNSPackageRepositoryDriver(properties, authenticatedRestTemplateService,
 * objectMapper);
 * 
 * assertThatThrownBy(() -> nsPackageDriver.queryAllNsPkgInfos("/group/nsdId"))
 * .isInstanceOf(SOL005ResponseException.class)
 * .hasFieldOrPropertyWithValue("problemDetails.status",
 * HttpStatus.UNAUTHORIZED.value()); }
 * 
 * @Test public void testGetNSPkgInfo() throws Exception { final
 * MockRestServiceServer server =
 * getMockRestServiceServer(nfvoDriverProperties);
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search?repository=test-repository&keyword=*nsPackageId.pkgInfo*"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString(
 * "/examples/pkgMgmt-ComponentSearchNsdPkgInfo.json"),
 * MediaType.APPLICATION_JSON));
 * 
 * // This is optional due to the potential caching within the driver
 * server.expect(between(0, 1), requestTo(
 * "http://does-not-exist:8081/repository/test-repository/group/NSdId/nsPackageId.pkgInfo"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(loadFileIntoString("/examples/nsPackageId.pkgInfo"),
 * MediaType.TEXT_PLAIN));
 * 
 * NsPkgInfo nsPkgInfo = nsPackageDriver.getNsPkgInfo("nsPackageId");
 * assertThat(nsPkgInfo).isNotNull(); }
 * 
 * @Test public void testGetNSPkgInfoNotFound() { final MockRestServiceServer
 * server = getMockRestServiceServer(nfvoDriverProperties);
 * 
 * server.expect(requestTo(
 * "http://does-not-exist:8081/service/rest/v1/search?repository=test-repository&keyword=*not-present-id.pkgInfo*"
 * )).andExpect(method(HttpMethod.GET))
 * .andRespond(withSuccess(EMPTY_SEARCH_RESULTS, MediaType.APPLICATION_JSON));
 * 
 * assertThatThrownBy(() -> nsPackageDriver.getNsPkgInfo("not-present-id"))
 * .isInstanceOf(NSPackageNotFoundException.class)
 * .hasMessage("Cannot find package information for NS package [not-present-id]"
 * ); }
 * 
 * }
 */