package com.ibm.nfvodriver.driver;

import com.ibm.nfvodriver.model.alm.ResourceManagerDeploymentLocation;
import com.ibm.nfvodriver.service.AuthenticatedRestTemplateService;
import org.etsi.sol005.nsd.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import static com.ibm.nfvodriver.config.NFVODriverConstants.NFVO_SERVER_URL;


/**
 * Driver implementing the ETSI SOL005 NSD Management interface
 * <p>
 * Endpoints expected to be found under the following structure
 *
 * <ul>
 *     <li>{apiRoot}/nsd/v1
 *     <li><ul>
 *         <li>/ns_descriptors</li>
 *         <li><ul>
 *             <li>/{nsdInfoId}</li>
 *             <li>/manifest</li>
 *             <li>/nsd</li>
 *             <li>/nsd_archive_content</li>
 *             <li>/artifacts</li>
 *             <li><ul>
 *                 <li>/artifactPath</li>
 *             </ul></li>
 *         </ul></li>
 *     </ul></li>
 *     <li><ul>
 *         <li>/pnf_descriptors</li>
 *         <li><ul>
 *             <li>/{pnfdInfoId}</li>
 *             <li>/manifest</li>
 *             <li>/pnfd</li>
 *             <li>/pnfd_archive_content</li>
 *             <li>/artifacts</li>
 *             <li><ul>
 *                 <li>/artifactPath</li>
 *             </ul></li>
 *         </ul></li>
 *     </ul></li>
 *     <li><ul>
 *         <li>/subscriptions</li>
 *         <li><ul>
 *             <li>/{subscriptionId}</li>
 *         </ul></li>
 *     </ul></li>
 * </ul>
 */
@Service("NSDManagementDriver")
public class NSDManagementDriver {

    private final static Logger logger = LoggerFactory.getLogger(NSDManagementDriver.class);
    private final static String API_CONTEXT_ROOT = "/nsd/v1";
    private final static String API_PREFIX_NS_DESCRIPTORS = "/ns_descriptors";
    private final static String API_PREFIX_PNF_DESCRIPTORS = "/pnf_descriptors";
    private final static String API_PREFIX_SUBSCRIPTIONS = "/subscriptions";

    private final static String API_NSD_CONTENT = "/nsd_content";
    private final static String API_NSD = "/nsd";
    private final static String API_MANIFEST = "/manifest";
    private final static String API_ARTIFACTS = "/artifacts";

    private final static String API_PNFD_CONTENT = "/pnfd_content";
    private final static String API_PNFD = "/pnfd";

    private final AuthenticatedRestTemplateService authenticatedRestTemplateService;

    @Autowired
    public NSDManagementDriver(AuthenticatedRestTemplateService authenticatedRestTemplateService) {
        this.authenticatedRestTemplateService = authenticatedRestTemplateService;
    }

    /**
     * Creates a new NS descriptor record in the NFVO
     *
     * <ul>
     *     <li>Sends CreateNsdInfoRequest message via HTTP POST to /ns_descriptors</li>
     *     <li>Gets 201 Created response with a {@link NsdInfo} record as the response body</li>
     *     <li>Postcondition: NS descriptor created with following states: </li>
     *     <li>nsdOnboardingState = "CREATED" , nsdOperationalState = "DISABLED" and nsdUsageState = "NOT_IN_USE"</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param CreateNsdInfoRequest   request information
     * @return newly created {@link NsdInfo} record
     * @throws SOL005ResponseException if there are any errors creating the NS descriptors
     */
    public String createNsDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, final String CreateNsdInfoRequest) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS;

        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, CreateNsdInfoRequest);
        /*
        final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(CreateNsdInfoRequest, headers);
        * */
        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.CREATED, true);
        return responseEntity.getBody();
    }

    /**
     * Query information about multiple NS descriptor resources in the NFVO
     *
     * <ul>
     *     <li>No message via HTTP GET to /ns_descriptors</li>
     *     <li>Gets 200 OK response with list of {@link NsdInfo} records as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return list of {@link NsdInfo} records
     * @throws SOL005ResponseException if there are any errors getting the NS descriptors
     */
    public String getNsDescriptor(final ResourceManagerDeploymentLocation deploymentLocation) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS;

        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*
        final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        */
        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }


    /**
     * Read information about an individual NS descriptor resource in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId via HTTP GET to /ns_descriptors/{nsdInfoId}</li>
     *     <li>Gets 200 OK response with a record {@link NsdInfo} as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a {@link NsdInfo} record
     * @throws SOL005ResponseException if there are any errors getting the individual NS descriptors
     */
    public String getIndividualNsDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId;

        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        */
        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Modify the operational state and/or the user defined data of an individual NS descriptor resource in the NFVO
     *
     * <ul>
     *      <li>Sends nsdInfoId via HTTP UPDATE to /ns_descriptors/{nsdInfoId} and NsdInfoModifications as payload</li>
     *     <li>Gets 200 OK response with list of {@link NsdInfoModifications} records as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a {@link NsdInfoModifications} record
     * @throws SOL005ResponseException if there are any errors patching the NS descriptors
     */
    public String patchIndividualNsDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId, String nsdInfoModifications) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.valueOf("application/merge-patch+json"), nsdInfoModifications);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);

        MediaType patchContentType = MediaType.valueOf("application/merge-patch+json");
        headers.setContentType(patchContentType);

        final HttpEntity<String> requestEntity = new HttpEntity<>(nsdInfoModifications, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.PATCH, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Delete an individual NS descriptor resource in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId via HTTP DELETE to /ns_descriptors/{nsdInfoId}</li>
     *     <li>Gets 204 NO_CONTENT as the response</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return No Content (HTTP status- 204)
     * @throws SOL005ResponseException if there are any errors deleting the NS descriptors
     */
    public String deleteIndividualNsDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.DELETE, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.NO_CONTENT, false);
        return responseEntity.getBody();
    }

    /**
     * Fetch the content of an NS descriptor archive in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId via HTTP GET to /ns_descriptors/{nsdInfoId}/nsd_content</li>
     *     <li>Gets 200 OK response with the payload body shall contain a copy of the ZIP file that contains the NSD file structure</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a copy of the ZIP file that contains the NSD file structure as byte[]
     * @throws SOL005ResponseException if there are any errors getting the NS descriptor archive
     */
    public byte[] getNsdArchiveContentDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId + API_NSD_CONTENT;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Upload the content of an NS descriptor archive in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId via HTTP GET to /ns_descriptors/{nsdInfoId}/nsd_content and ZIP file that represents the NSD archive</li>
     *     <li>Gets 204 NO_CONTENT when used synchronous call</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return  No Content (HTTP status- 204)
     * @throws SOL005ResponseException if there are any errors uploading the NS descriptor archive
     */
    public String putNsdArchiveContentDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId, byte[] nsdArchiveContent) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId + API_NSD_CONTENT;
        final HttpEntity<byte[]> requestEntity = constructHttpByteHeader(deploymentLocation, MediaType.APPLICATION_JSON, nsdArchiveContent);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<byte[]> requestEntity = new HttpEntity<>(nsdArchiveContent, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.PUT, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.NO_CONTENT, false);
        return responseEntity.getBody();
    }

    /**
     * Read an onboarded NS descriptor in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId via HTTP GET to /ns_descriptors/{nsdInfoId}/nsd</li>
     *     <li>Gets 200 OK response with the payload body shall contain a copy of the file representing the NSD or
     *     a ZIP file that contains the file or multiple files representing the NSD</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return A copy of the file representing the NSD or a ZIP file that contains the file or multiple files representing the NSD as byte[]
     * @throws SOL005ResponseException if there are any errors reading the onboarded NS descriptors
     */
    public byte[] getNsdContent(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId + API_NSD;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }


    /**
     * Fetch the manifest file of an onboarded NSD archive in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId via HTTP GET to /ns_descriptors/{nsdInfoId}/manifest</li>
     *     <li>Gets 200 OK response with the payload body shall contain a ZIP archive which includes: </li>
     *     <li> - a copy of the manifest file of the NSD archive </li>
     *     <li> - a copy of the related individual certificate file </li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a ZIP archive as byte[]
     * @throws SOL005ResponseException if there are any errors reading the NS descriptor manifest
     */
    public byte[] getNsdManifest(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId + API_MANIFEST;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Fetch individual NSD archive artifact in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId and artifactsPath via HTTP GET to /ns_descriptors/{nsdInfoId}/artifacts/{artifactPath}</li>
     *     <li>Gets 200 OK response with the payload body shall contain a ZIP archive which includes: </li>
     *     <li> - a copy of the artifact file from the VNF package, as defined by ETSI GS NFV-SOL 007 [18]; </li>
     *     <li> - the related security information (individual signature file and optional related individual certificate file) </li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a ZIP archive as byte[]
     * @throws SOL005ResponseException if there are any errors fetching the individual NS descriptor archive artifact
     */
    public byte[] getNsdArtifacts(final ResourceManagerDeploymentLocation deploymentLocation, String nsdInfoId, String artifactPath) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_DESCRIPTORS + "/" + nsdInfoId + API_ARTIFACTS+ "/" + artifactPath;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Create a new PNF descriptor resource record in the NFVO
     *
     * <ul>
     *     <li>Sends CreatePnfdInfoRequest message via HTTP POST to /pnf_descriptors</li>
     *     <li>Gets 201 Created response with a {@link PnfdInfo} record as the response body</li>
     *     <li>Postcondition: "Individual PNF descriptor" resource and the associated PNF descriptor identifier </li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param CreatePnfdInfoRequest   request information
     * @return newly created {@link PnfdInfo} record
     * @throws SOL005ResponseException if there are any errors creating the PNF descriptors
     */
    public String createPnfDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, final String CreatePnfdInfoRequest) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, CreatePnfdInfoRequest);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(CreatePnfdInfoRequest, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.CREATED, true);
        return responseEntity.getBody();
    }


    /**
     * Query information about multiple PNF descriptor resources in the NFVO
     *
     * <ul>
     *     <li>No message via HTTP GET to /pnf_descriptors</li>
     *     <li>Gets 200 OK response with list of {@link PnfdInfo} records as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return list of {@link PnfdInfo} records
     * @throws SOL005ResponseException if there are any errors querying the PNF descriptors
     */
    public String getPnfDescriptor(final ResourceManagerDeploymentLocation deploymentLocation) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Read information about an individual PNF descriptor resource in the NFVO
     *
     * <ul>
     *     <li>Sends pnfdInfoId via HTTP GET to /pnf_descriptors/{pnfdInfoId}</li>
     *     <li>Gets 200 OK response with a record {@link PnfdInfo} as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a {@link PnfdInfo} record
     * @throws SOL005ResponseException if there are any errors reading the individual PNF descriptors
     */
    public String getIndividualPnfDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Modify the user defined data of an individual PNF descriptor resource in the NFVO
     *
     * <ul>
     *      <li>Sends pnfdInfoId via HTTP UPDATE to /pnf_descriptors/{pnfdInfoId} and PnfdInfoModifications as payload</li>
     *     <li>Gets 200 OK response with list of {@link PnfdInfoModifications} records as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a {@link PnfdInfoModifications} record
     * @throws SOL005ResponseException if there are any errors modifying the individual PNF descriptors
     */
    public String patchIndividualPnfDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId, String pnfdInfoModifications) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.valueOf("application/merge-patch+json"), pnfdInfoModifications);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);

        MediaType patchContentType = MediaType.valueOf("application/merge-patch+json");
        headers.setContentType(patchContentType);

        final HttpEntity<String> requestEntity = new HttpEntity<>(pnfdInfoModifications, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.PATCH, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Delete an individual PNF descriptor resource in the NFVO
     *
     * <ul>
     *     <li>Sends pnfdInfoId via HTTP DELETE to /pnf_descriptors/{pnfdInfoId}</li>
     *     <li>Gets 204 NO_CONTENT as the response</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return No Content (HTTP status- 204)
     * @throws SOL005ResponseException if there are any errors deleting the individual PNF descriptors
     */
    public String deleteIndividualPnfDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
       /* final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.DELETE, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.NO_CONTENT, false);
        return responseEntity.getBody();
    }

    /**
     * Fetch the content of an PNF descriptor archive in the NFVO
     *
     * <ul>
     *     <li>Sends nsdInfoId via HTTP GET to /pnf_descriptors/{pnfdInfoId}/pnfd_content</li>
     *     <li>Gets 200 OK response with the payload body shall contain a copy of the ZIP file that contains the PNFD file structure</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a copy of the ZIP file that contains the PNFD file structure as byte[]
     * @throws SOL005ResponseException if there are any errors fetching the PNF descriptor archive
     */
    public byte[] getPnfdArchiveContentDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId + API_PNFD_CONTENT;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Upload the content of an PNF descriptor archive in the NFVO
     *
     * <ul>
     *     <li>Sends pnfdInfoId via HTTP GET to /pnf_descriptors/{pnfdInfoId}/pnfd_content and ZIP file that represents the PNFD archive</li>
     *     <li>Gets 204 NO_CONTENT when used synchronous call</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return  No Content (HTTP status- 204)
     * @throws SOL005ResponseException if there are any errors uploading the PNF descriptor archive
     */
    public String putPnfdArchiveContentDescriptor(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId, byte[] nsdArchiveContent) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId + API_PNFD_CONTENT;
        final HttpEntity<byte[]> requestEntity = constructHttpByteHeader(deploymentLocation, MediaType.APPLICATION_JSON, nsdArchiveContent);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<byte[]> requestEntity = new HttpEntity<>(nsdArchiveContent, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.PUT, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.NO_CONTENT, false);
        return responseEntity.getBody();
    }

    /**
     * Read an onboarded PNF descriptor in the NFVO
     *
     * <ul>
     *     <li>Sends pnfdInfoId via HTTP GET to /pnf_descriptors/{pnfdInfoId}/pnfd</li>
     *     <li>Gets 200 OK response with the payload body shall contain a copy of the file representing the PNFD or
     *     a ZIP file that contains the file or multiple files representing the PNFD</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return A copy of the file representing the NSD or a ZIP file that contains the file or multiple files representing the PNFD as byte[]
     * @throws SOL005ResponseException if there are any errors reading the onboarded PNF descriptors
     */
    public byte[] getPnfdContent(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId + API_PNFD;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }


    /**
     * Fetch the manifest file of an onboarded PNFD archive in the NFVO
     *
     * <ul>
     *     <li>Sends pnfdInfoId via HTTP GET to /pnf_descriptors/{pnfdInfoId}/manifest</li>
     *     <li>Gets 200 OK response with the payload body shall contain a ZIP archive which includes: </li>
     *     <li> - a copy of the manifest file of the PNFD archive </li>
     *     <li> - a copy of the related individual certificate file </li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a ZIP archive as byte[]
     * @throws SOL005ResponseException if there are any errors fetching the manifest file of an onboarded PNFD archive
     */
    public byte[] getPnfdManifest(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId + API_MANIFEST;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Fetch individual PNFD archive artifact in the NFVO
     *
     * <ul>
     *     <li>Sends pnfdInfoId and artifactsPath via HTTP GET to /pnf_descriptors/{pnfdInfoId}/artifacts/{artifactPath}</li>
     *     <li>Gets 200 OK response with the payload body shall contain a ZIP archive which includes: </li>
     *     <li> - a copy of the artifact file from the VNF package, as defined by ETSI GS NFV-SOL 004 [5]; </li>
     *     <li> - the related security information (individual signature file and optional related individual certificate file) </li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return a ZIP archive as byte[]
     * @throws SOL005ResponseException if there are any errors fetching individual PNF descriptor archive artifact
     */
    public byte[] getPnfdArtifacts(final ResourceManagerDeploymentLocation deploymentLocation, String pnfdInfoId, String artifactPath) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_PNF_DESCRIPTORS + "/" + pnfdInfoId + API_ARTIFACTS+ "/" + artifactPath;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<byte[]> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }


    /**
     * Subscribe to NS descriptor and PNF descriptor change notifications from the NFVO
     *
     * <ul>
     *     <li>Sends nsdmSubscriptionRequest via HTTP POST to /subscriptions</li>
     *     <li>Gets 201 CREATED response with the payload body shall contain "Individual subscription" resource </li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return A representation of the created "Individual subscription" resource shall be returned in the response body, as defined in clause 5.5.2.8.
     * @throws SOL005ResponseException if there are any errors subscribing the NSD/PNF descriptors
     */
    public String subscriptions(final ResourceManagerDeploymentLocation deploymentLocation, String nsdmSubscriptionRequest) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, nsdmSubscriptionRequest);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(nsdmSubscriptionRequest, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.CREATED, true);
        return responseEntity.getBody();
    }

    /**
     * Query multiple subscriptions from the NFVO
     *
     * <ul>
     *     <li>No message via HTTP GET to /subscriptions</li>
     *     <li>Gets 200 OK response with the payload body shall contain list of subscriptions has been queried successfully.</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return The response body shall contain in an array the representations of all active subscriptions of the functional block that invokes the method, i.e. zero or more representations of NSD management subscriptions as defined in clause 5.5.2.8.
     * @throws SOL005ResponseException if there are any errors querying subscriptions of the NSD/PNF descriptors
     */
    public String querySubscriptions(final ResourceManagerDeploymentLocation deploymentLocation) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Read an individual subscription resource from the NFVO
     *
     * <ul>
     *     <li>Sends subscriptionId via HTTP GET to /subscriptions/{subscriptionId}</li>
     *     <li>Gets 200 OK response with the payload body shall contain individual subscription has been read successfully.</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return The response body shall contain a representation of the subscription resource.
     * @throws SOL005ResponseException if there are any errors querying individual subscription of the NSD/PNF descriptors
     */
    public String queryIndividualSubscription(final ResourceManagerDeploymentLocation deploymentLocation, String subscriptionId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS+ "/"+ subscriptionId;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Terminate a subscription resource from the NFVO
     *
     * <ul>
     *     <li>Sends subscriptionId via HTTP GET to /subscriptions/{subscriptionId}</li>
     *     <li>Gets 204 NO_CONTENT response with no payload body.</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return The response body shall be empty.
     * @throws SOL005ResponseException if there are any errors querying individual subscription of the NSD/PNF descriptors
     */
    public String deleteIndividualSubscription(final ResourceManagerDeploymentLocation deploymentLocation, String subscriptionId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS+ "/"+ subscriptionId;
        final HttpEntity<String> requestEntity = constructHttpHeader(deploymentLocation, MediaType.APPLICATION_JSON, null);
        /*final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);*/

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.DELETE, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.NO_CONTENT, false);
        return responseEntity.getBody();
    }


    /**
     * Creates HTTP headers, populating the content type (as application/json)
     *
     * @param deploymentLocation deployment location
     * @return org.apache.http.protocol.HTTP headers containing appropriate authentication parameters
     */
    private HttpHeaders getHttpHeaders(ResourceManagerDeploymentLocation deploymentLocation) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Construct HTTP headers, populating the content type
     *
     * @param deploymentLocation deployment location
     * @param mediaType Media Type
     * @param nsdRequest NSD Request Type
     * @return HttpEntity containing appropriate http entity
     */
    private HttpEntity<String> constructHttpHeader(ResourceManagerDeploymentLocation deploymentLocation, MediaType mediaType, String nsdRequest) {
        final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(mediaType);
        return new HttpEntity<>(nsdRequest, headers);
    }

    /**
     * Construct HTTP headers, populating the content type
     *
     * @param deploymentLocation deployment location
     * @param mediaType Media Type
     * @param nsdRequest NSD Request Type
     * @return HttpEntity containing appropriate http entity
     */
    private HttpEntity<byte[]> constructHttpByteHeader(ResourceManagerDeploymentLocation deploymentLocation, MediaType mediaType, byte[] nsdRequest) {
        final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        headers.setContentType(mediaType);
        return new HttpEntity<>(nsdRequest, headers);
    }

    /**
     * Utility method that checks if the HTTP status code matches the expected value and that it contains a response body (if desired)
     *
     * @param responseEntity       response to check
     * @param expectedStatusCode   HTTP status code to check against
     * @param containsResponseBody whether the response should contain a body
     */
    private void checkResponseEntityMatches(final ResponseEntity responseEntity, final HttpStatus expectedStatusCode, final boolean containsResponseBody) {
        // Check response code matches expected value (log a warning if incorrect 2xx status seen)
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getStatusCode() != expectedStatusCode) {
            // Be lenient on 2xx response codes
            logger.warn("Invalid status code [{}] received, was expecting [{}]", responseEntity.getStatusCode(), expectedStatusCode);
        } else if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new SOL005ResponseException(String.format("Invalid status code [%s] received", responseEntity.getStatusCode()));
        }
        // Check if the response body is populated (or not) as expected
        if (containsResponseBody && responseEntity.getBody() == null) {
            throw new SOL005ResponseException("No response body");
        } else if (!containsResponseBody && responseEntity.getBody() != null) {
            throw new SOL005ResponseException("No response body expected");
        }
    }

}
