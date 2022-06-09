package com.ibm.nfvodriver.driver;

import com.ibm.nfvodriver.model.alm.ResourceManagerDeploymentLocation;
import com.ibm.nfvodriver.service.AuthenticatedRestTemplateService;
import org.etsi.sol005.lifecyclemanagement.LccnSubscription;
import org.etsi.sol005.lifecyclemanagement.LccnSubscriptionRequest;
import org.etsi.sol005.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static com.ibm.nfvodriver.config.NFVODriverConstants.NFVO_SERVER_URL;


/**
 * Driver implementing the ETSI SOL005 Lifecycle Management interface
 * <p>
 * Endpoints expected to be found under the following structure
 *
 * <ul>
 *     <li>{apiRoot}/nslcm/v1
 *     <li><ul>
 *         <li>/ns_instances</li>
 *         <li><ul>
 *             <li>/{nsInstanceId}
 *             <li><ul>
 *                 <li>/instantiate</li>
 *                 <li>/scale</li>
 *                 <li>/update</li>
 *                 <li>/terminate</li>
 *                 <li>/heal</li>
 *             </ul></li>
 *         </ul></li>
 *     </ul></li>
 *     <li><ul>
 *         <li>/ns_lcm_op_occs</li>
 *         <li><ul>
 *             <li>/{nsLcmOpOccId}</li>
 *             <li><ul>
 *                 <li>/retry</li>
 *                 <li>/rollback</li>
 *                 <li>/continue</li>
 *                 <li>/fail</li>
 *                 <li>/cancel</li>
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
@Service("NSLifecycleManagementDriver")
public class NSLifecycleManagementDriver {

    private final static Logger logger = LoggerFactory.getLogger(NSLifecycleManagementDriver.class);

    private final static String API_CONTEXT_ROOT = "/nslcm/v1";
    private final static String API_PREFIX_OP_OCCURRENCES = "/ns_lcm_op_occs";
    private final static String API_PREFIX_NS_INSTANCES = "/ns_instances";
    private final static String API_PREFIX_SUBSCRIPTIONS = "/subscriptions";

    private AuthenticatedRestTemplateService authenticatedRestTemplateService;

    @Autowired
    public NSLifecycleManagementDriver(AuthenticatedRestTemplateService authenticatedRestTemplateService) {
        this.authenticatedRestTemplateService = authenticatedRestTemplateService;
    }

    /**
     * Creates a new NS instance record in the NFVO
     *\
     * <ul>
     *     <li>Sends CreateNsRequest message via HTTP POST to /ns_instances</li>
     *     <li>Gets 201 Created response with a {@link NsInstance} record as the response body</li>
     *     <li>Postcondition: NS instance created in NOT_INSTANTIATED state</li>
     *     <li>Out of band {@link NsIdentifierCreationNotification} should be received after this returns</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param createNsRequest    request information
     * @return newly created {@link NsInstance} record
     * @throws SOL005ResponseException if there are any errors creating the NS instance
     */
    public String createNsInstance(final ResourceManagerDeploymentLocation deploymentLocation, final String createNsRequest) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_INSTANCES;

        final HttpEntity<Void> requestEntity = getHttpEntity(createNsRequest);
        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class);

        // "Location" header also includes URI of the created instance
        checkResponseEntityMatches(responseEntity, HttpStatus.CREATED, true);
        return responseEntity.getBody();
    }

    /**
     * Delete an "Individual NS instance" resource.
     *
     * <ul>
     *     <li>Precondition: NS instance in NOT_INSTANTIATED state</li>
     *     <li>Sends HTTP DELETE request to /ns_instances/{nsInstanceId}</li>
     *     <li>Gets 204 No Content response</li>
     *     <li>Postcondition: NS instance resource removed</li>
     *     <li>Out of band {@link NsIdentifierDeletionNotification} should be received after this returns</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsInstanceId       Identifier of the {@link NsInstance} record to delete
     * @throws SOL005ResponseException if there are any errors deleting the NS instance
     */
    public void deleteNsInstance(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_INSTANCES + "/{nsInstanceId}";

        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsInstanceId", nsInstanceId);

        final ResponseEntity<Void> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.DELETE, requestEntity, Void.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.NO_CONTENT, false);
    }

    /**
     * Submits an operation to the NFVO to instantiate an existing NS instance
     *
     * <ul>
     *     <li>Precondition: NS instance created and in NOT_INSTANTIATED state</li>
     *     <li>Sends an {@link InstantiateNsRequest} via HTTP POST to /ns_instances/{nsInstanceId}/instantiate</li>
     *     <li>Gets 202 Accepted response with Location header to the {@link NsLcmOpOcc} record</li>
     *     <li>Postcondition: NS instance in INSTANTIATED state</li>
     * </ul>
     *
     * @param deploymentLocation   deployment location
     * @param nsInstanceId         Identifier for the {@link NsInstance} to perform the operation on
     * @param instantiateNsRequest request information
     * @return newly created {@link NsLcmOpOcc} record identifier
     * @throws SOL005ResponseException if there are any errors creating the operation request
     */
    public String instantiateNs(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId, final String instantiateNsRequest) throws SOL005ResponseException {
        return callNsLcmOperation(deploymentLocation, nsInstanceId, "instantiate", instantiateNsRequest);
    }

    /**
     * Submits an operation to the NFVO to scale an existing NS instance
     *
     * <ul>
     *     <li>Precondition: NS instance in INSTANTIATED state</li>
     *     <li>Sends a {@link ScaleNsRequest} via HTTP POST to /ns_instances/{nsInstanceId}/scale</li>
     *     <li>Gets 202 Accepted response with Location header to the {@link NsLcmOpOcc} record</li>
     *     <li>Postcondition: NS instance still in INSTANTIATED state and NS was scaled</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsInstanceId       Identifier for the {@link NsInstance} to perform the operation on
     * @param scaleNsRequest     request information
     * @return newly created {@link NsLcmOpOcc} record identifier
     * @throws SOL005ResponseException if there are any errors creating the operation request
     */
    public String scaleNs(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId, final String scaleNsRequest) throws SOL005ResponseException {
        return callNsLcmOperation(deploymentLocation, nsInstanceId, "scale", scaleNsRequest);
    }

    /**
     * Submits an operation to the NFVO to start or stop (operate) an existing NS instance
     *
     * <ul>
     *     <li>Precondition: NS instance in INSTANTIATED state</li>
     *     <li>Sends an {@link UpdateNsRequest} via HTTP POST to /ns_instances/{nsInstanceId}/operate</li>
     *     <li>Gets 202 Accepted response with Location header to the {@link NsLcmOpOcc} record</li>
     *     <li>Postcondition: NS instance still in INSTANTIATED state and NS operational state changed</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsInstanceId       Identifier for the {@link NsInstance} to perform the operation on
     * @param updateNsRequest    request information
     * @return newly created {@link NsLcmOpOcc} record identifier
     * @throws SOL005ResponseException if there are any errors creating the operation request
     */
    public String updateNs(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId, final String updateNsRequest) throws SOL005ResponseException {
        return callNsLcmOperation(deploymentLocation, nsInstanceId, "update", updateNsRequest);
    }

    /**
     * Submits an operation to the NFVO to heal an existing NS instance
     *
     * <ul>
     *     <li>Precondition: NS instance in INSTANTIATED state</li>
     *     <li>Sends a {@link HealNsRequest} via HTTP POST to /ns_instances/{nsInstanceId}/heal</li>
     *     <li>Gets 202 Accepted response with Location header to the {@link NsLcmOpOcc} record</li>
     *     <li>Postcondition: NS instance still in INSTANTIATED state</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsInstanceId       Identifier for the {@link NsInstance} to perform the operation on
     * @param healNsRequest      request information
     * @return newly created {@link NsLcmOpOcc} record identifier
     * @throws SOL005ResponseException if there are any errors creating the operation request
     */
    public String healNs(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId, final String healNsRequest) throws SOL005ResponseException {
        return callNsLcmOperation(deploymentLocation, nsInstanceId, "heal", healNsRequest);
    }

    /**
     * Submits an operation to the NFVO to terminate an existing NS instance
     *
     * <ul>
     *     <li>Precondition: NS instance in INSTANTIATED state</li>
     *     <li>Sends a {@link TerminateNsRequest} via HTTP POST to /ns_instances/{nsInstanceId}/terminate</li>
     *     <li>Gets 202 Accepted response with Location header to the {@link NsLcmOpOcc} record</li>
     *     <li>Postcondition: NS instance in NOT_INSTANTIATED state</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsInstanceId       Identifier for the {@link NsInstance} to perform the operation on
     * @param terminateNsRequest request information
     * @return newly created {@link NsLcmOpOcc} record identifier
     * @throws SOL005ResponseException if there are any errors creating the operation request
     */
    public String terminateNs(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId, final String terminateNsRequest) throws SOL005ResponseException {
        return callNsLcmOperation(deploymentLocation, nsInstanceId, "terminate", terminateNsRequest);
    }

    /**
     * Submits an operation to the NFVO on an existing NS instance
     *
     * <ul>
     *     <li>Sends &lt;&lt;RequestStructure&gt;&gt; via HTTP POST to /ns_instances/{nsInstanceId}/&lt;&lt;Task&gt;&gt;</li>
     *     <li>Gets 202 Accepted response with Location header to the {@link NsLcmOpOcc} record</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsInstanceId       Identifier for the {@link NsInstance} to perform the operation on
     * @param operationName      Name of the operation to perform (forms the URI)
     * @param updateNsRequest    request information
     * @return newly created {@link NsLcmOpOcc} record identifier
     * @throws SOL005ResponseException if there are any errors creating the operation request
     */
    private String callNsLcmOperation(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId, final String operationName, final String updateNsRequest)
            throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_INSTANCES + "/" + nsInstanceId + "/" + operationName;

        final HttpEntity<Void> requestEntity = getHttpEntity(updateNsRequest);
        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class);

        checkResponseEntityMatches(responseEntity, HttpStatus.ACCEPTED, false);
        // "Location" header contains URI of the created NsLcmOpOcc record
        final URI location = responseEntity.getHeaders().getLocation();
        if (location == null) {
            throw new SOL005ResponseException("No Location header found");
        }
        // Return the NsLcmOpOccId, which is the last part of the path
        return location.getPath().substring(location.getPath().lastIndexOf("/") + 1);
    }


    /**
     * Query information about multiple NS lifecycle management operation occurrences
     *
     * <ul>
     *     <li>Sends HTTP GET request to /ns_lcm_op_occs</li>
     *     <li>Gets 200 OK response with an array of {@link NsLcmOpOcc} records as the response body</li>
     * </ul>
     * <p>
     * The following query parameters can be supplied to the request
     * <ul>
     *     <li>(attribute-based filtering) - e.g. ?operationState=PROCESSING</li>
     *     <li>all_fields</li>
     *     <li>fields=&lt;comma-separated list&gt;</li>
     *     <li>exclude_fields=&lt;comma-separated list&gt;</li>
     *     <li>exclude_default</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return list of matching {@link NsLcmOpOcc} records
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public String queryAllLifecycleOperationOccurrences(final ResourceManagerDeploymentLocation deploymentLocation) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_OP_OCCURRENCES;

        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        // "Location" header also includes URI of the created instance
        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Read information about an individual NS lifecycle management operation occurrence
     *
     * <ul>
     *     <li>Sends HTTP GET request to /ns_lcm_op_occs/{nsLcmOpOccId}</li>
     *     <li>Gets 200 OK response with a {@link NsLcmOpOcc} record as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsLcmOpOccId       Identifier for the {@link NsLcmOpOcc} record
     * @return matching {@link NsLcmOpOcc} record
     * @throws SOL005ResponseException if there are any errors performing the query
     */

    public String queryLifecycleOperationOccurrence(final ResourceManagerDeploymentLocation deploymentLocation, final String nsLcmOpOccId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_OP_OCCURRENCES + "/{nsLcmOpOccId}";

        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsLcmOpOccId", nsLcmOpOccId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Retry an NS lifecycle management operation occurrence.
     *
     * <ul>
     *     <li>Sends HTTP POST request to ns_lcm_op_occs/{nsLcmOpOccId}/retry </li>
     *     <li>Gets 202 ACCEPTED response and the "PROCESSING"
     *         NsLcmOperationOccurrenceNotification can arrive in any order at the OSS/BSS</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsLcmOpOccId       Identifier for the {@link NsLcmOpOcc} record
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public void nsLcmOperationsOccurrencesRetry(final ResourceManagerDeploymentLocation deploymentLocation, final String nsLcmOpOccId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_OP_OCCURRENCES + "/{nsLcmOpOccId}/retry";

        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsLcmOpOccId", nsLcmOpOccId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.ACCEPTED, false);
    }

    /**
     * Rollback an NS lifecycle management operation occurrence
     *
     * <ul>
     *     <li>Sends HTTP POST request to /ns_lcm_op_occs/{nsLcmOpOccId}/rollback </li>
     *     <li>Gets 202 ACCEPTED response and the "PROCESSING"
     *         NsLcmOperationOccurrenceNotification can arrive in any order at the OSS/BSS</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsLcmOpOccId       Identifier for the {@link NsLcmOpOcc} record
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public void nsLcmOperationsOccurrencesRollback(final ResourceManagerDeploymentLocation deploymentLocation, final String nsLcmOpOccId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_OP_OCCURRENCES + "/{nsLcmOpOccId}/rollback";
        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsLcmOpOccId", nsLcmOpOccId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.ACCEPTED, false);
    }

    /**
     * Continue an NS lifecycle management operation occurrence, found by its identifier
     *
     * <ul>
     *     <li>Sends HTTP POST request to /ns_lcm_op_occs/{nsLcmOpOccId}/continue </li>
     *     <li>Gets 202 ACCEPTED response and the "PROCESSING"
     *         NsLcmOperationOccurrenceNotification can arrive in any order at the OSS/BSS</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsLcmOpOccId       Identifier for the {@link NsLcmOpOcc} record
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public void nsLcmOperationsOccurrencesContinue(final ResourceManagerDeploymentLocation deploymentLocation, final String nsLcmOpOccId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_OP_OCCURRENCES + "/{nsLcmOpOccId}/continue";
        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsLcmOpOccId", nsLcmOpOccId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.ACCEPTED, false);
    }

    /**
     * Mark an NS lifecycle management operation occurrence as failed, found by its identifier
     *
     * <ul>
     *     <li>Sends HTTP POST request to /ns_lcm_op_occs/{nsLcmOpOccId}/fail </li>
     *     <li>Gets 200 OK response with a {@link NsLcmOpOcc} record as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsLcmOpOccId       Identifier for the {@link NsLcmOpOcc} record
     * @return matching {@link NsLcmOpOcc} record
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public String nsLcmOperationsOccurrencesFail(final ResourceManagerDeploymentLocation deploymentLocation, final String nsLcmOpOccId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_OP_OCCURRENCES + "/{nsLcmOpOccId}/fail";

        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsLcmOpOccId", nsLcmOpOccId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Cancel an NS lifecycle management operation occurrence, found by its identifier
     *
     * <ul>
     *     <li>Sends HTTP POST request to /ns_lcm_op_occs/{nsLcmOpOccId}/cancel </li>
     *     <li>Gets 202 ACCEPTED response and the "PROCESSING"
     *         NsLcmOperationOccurrenceNotification can arrive in any order at the OSS/BSS</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param nsLcmOpOccId       Identifier for the {@link NsLcmOpOcc} record
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public void nsLcmOperationsOccurrencesCancel(final ResourceManagerDeploymentLocation deploymentLocation, final String nsLcmOpOccId, final String cancelMode) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_OP_OCCURRENCES + "/{nsLcmOpOccId}/cancel";
        final HttpEntity<Void> requestEntity = getHttpEntity(cancelMode);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsLcmOpOccId", nsLcmOpOccId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.ACCEPTED, false);
    }

    /**
     * Creates a subscription for lifecycle change notifications
     *
     * <ul>
     *     <li>Sends LccnSubscriptionRequest message via org.apache.http.protocol.HTTP POST to /subscriptions</li>
     *     <li>Optionally the NFVO may test the notification endpoint here</li>
     *     <li>Gets 201 Created response with a {@link LccnSubscription} record as the response body</li>
     * </ul>
     *
     * @param deploymentLocation      deployment location
     * @param lccnSubscriptionRequest details of the requested subscription
     * @return newly created {@link LccnSubscription} record
     * @throws SOL005ResponseException if there are any errors creating the subscription
     */
    public LccnSubscription createLifecycleSubscription(final ResourceManagerDeploymentLocation deploymentLocation, final LccnSubscriptionRequest lccnSubscriptionRequest)
            throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS;
        final HttpHeaders headers = getHttpHeaders(deploymentLocation);
        final HttpEntity<LccnSubscriptionRequest> requestEntity = new HttpEntity<>(lccnSubscriptionRequest, headers);

        final ResponseEntity<LccnSubscription> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation)
                .exchange(url, HttpMethod.POST, requestEntity, LccnSubscription.class);

        // "Location" header also includes URI of the created instance
        checkResponseEntityMatches(responseEntity, HttpStatus.CREATED, true);
        return responseEntity.getBody();
    }

    /**
     * Query multiple subscriptions.
     *
     * <ul>
     *     <li>Sends HTTP GET request to /subscriptions</li>
     *     <li>Gets 200 OK response with an array of {@link LccnSubscription} records as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return list of matching {@link LccnSubscription} records
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public String queryAllLifecycleSubscriptions(final ResourceManagerDeploymentLocation deploymentLocation, final String lccnSubscriptionRequest) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS;
        final HttpEntity<Void> requestEntity = getHttpEntity(lccnSubscriptionRequest);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation)
                .exchange(url, HttpMethod.GET, requestEntity, String.class);

        // "Shall be returned when the list of subscriptions has been queried successfully."
        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Read an "Individual subscription" resource, found by its identifier
     *
     * <ul>
     *     <li>Sends HTTP GET request to /subscriptions/{subscriptionId}</li>
     *     <li>Gets 200 OK response with a {@link LccnSubscription} record as the response body</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param subscriptionId     Identifier for the {@link LccnSubscription} record
     * @return matching {@link LccnSubscription} record
     * @throws SOL005ResponseException if there are any errors performing the query
     */
    public String queryLifecycleSubscription(final ResourceManagerDeploymentLocation deploymentLocation, final String subscriptionId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS + "/{subscriptionId}";

        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("subscriptionId", subscriptionId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * Deletes a lifecycle change notification subscription record from the NFVO
     *
     * <ul>
     *     <li>Sends HTTP DELETE request to /subscriptions/{subscriptionId}</li>
     *     <li>Gets 204 No Content response and the "PROCESSING"
     *         NsLcmOperationOccurrenceNotification can arrive in any order at the OSS/BSS</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @param subscriptionId     Identifier of the {@link LccnSubscription} record to delete
     * @throws SOL005ResponseException if there are any errors deleting the LccnSubscription
     */
    public void deleteLifecycleSubscription(final ResourceManagerDeploymentLocation deploymentLocation, final String subscriptionId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_SUBSCRIPTIONS + "/{subscriptionId}";

        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("subscriptionId", subscriptionId);

        final ResponseEntity<Void> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.DELETE, requestEntity, Void.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.NO_CONTENT, false);
    }

    /**
     * The GET method queries information about multiple NS instances.
     *
     * <ul>
     *     <li>SShall be returned when information about zero or more NS instances has been queried successfully. via HTTP GET to /ns_instances</li>
     *     <li>Gets 200 OK response with a {@link NsInstance} record as the response body</li>
     *     <li>Query multiple NS instances. </li>
     *     <li>Out of band {@link NsIdentifierCreationNotification} should be received after this returns</li>
     * </ul>
     *
     * @param deploymentLocation deployment location
     * @return Query multiple NS instances {@link NsInstance} record
     * @throws SOL005ResponseException if there are any errors creating the NS instance
     */
    public String getNsInstance(final ResourceManagerDeploymentLocation deploymentLocation) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_INSTANCES;

        final HttpEntity<Void> requestEntity = getHttpEntity(null);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class);

        // "Shall be returned when information about zero or more NS instances has been queried successfully."
        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
        return responseEntity.getBody();
    }

    /**
     * The GET method retrieves information about an NS instance by reading an "Individual NS instance" resource.
     *
     * <ul>
     *     <li>SShall be returned when information about zero or more NS instances has been queried successfully. via HTTP GET to }/ns_instances/{nsInstanceId}</li>
     *     <li>Gets 200 OK response with a {@link NsInstance} record as the response body</li>
     *     <li>Query Individual NS instances. </li>
     *     <li>Out of band {@link NsIdentifierCreationNotification} should be received after this returns</li>
     * </ul>
     *
     * @param deploymentLocation         deployment location
     * @return Read an "Individual NS instance" resource. {@link NsInstance} record
     * @param nsInstanceId request information
     * @throws SOL005ResponseException if there are any errors creating the NS instance
     */

    public String getNsInstanceForIndividual(final ResourceManagerDeploymentLocation deploymentLocation, final String nsInstanceId) throws SOL005ResponseException {
        final String url = deploymentLocation.getProperties().get(NFVO_SERVER_URL) + API_CONTEXT_ROOT + API_PREFIX_NS_INSTANCES + "/{nsInstanceId}";
        final HttpEntity<Void> requestEntity = getHttpEntity(null);
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("nsInstanceId", nsInstanceId);

        final ResponseEntity<String> responseEntity = authenticatedRestTemplateService.getRestTemplate(deploymentLocation).exchange(url, HttpMethod.GET, requestEntity, String.class, uriVariables);

        checkResponseEntityMatches(responseEntity, HttpStatus.OK, true);
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
     * Creates HTTP Entity, populating the entity request.
     *
     * @param request request information
     * @return org.springframework.http.HttpEntity request consisting of headers and body.
     */
    private HttpEntity<Void> getHttpEntity(String request) {

        final HttpEntity httpEntity;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(request!=null){
            httpEntity=new HttpEntity<>(request,headers);
        }else{
            httpEntity=new HttpEntity<>(null,headers);        }
        return httpEntity;
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
