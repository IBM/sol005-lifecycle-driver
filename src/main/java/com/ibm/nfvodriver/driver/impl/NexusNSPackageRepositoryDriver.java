/**
 *  This file is not used currently and kept it for future requirements if any
 */
package com.ibm.nfvodriver.driver.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.nfvodriver.config.NFVODriverProperties;
import com.ibm.nfvodriver.driver.NSPackageNotFoundException;
import com.ibm.nfvodriver.driver.NSPackageRepositoryDriver;
import com.ibm.nfvodriver.driver.NSPackageRepositoryException;
import com.ibm.nfvodriver.service.AuthenticatedRestTemplateService;
import com.sonatype.nexus.AssetInformation;
import com.sonatype.nexus.ComponentInformation;
import com.sonatype.nexus.PaginatedResults;
import org.apache.logging.log4j.util.Strings;
import org.etsi.sol005.packagemanagement.NsPkgInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class NexusNSPackageRepositoryDriver implements NSPackageRepositoryDriver {

    private static final String COMPONENT_SEARCH_URL = "/service/rest/v1/search";
    private static final String ASSET_SEARCH_URL = "/service/rest/v1/search/assets";
    private static final Logger logger = LoggerFactory.getLogger(NexusNSPackageRepositoryDriver.class);

    private final NFVODriverProperties nfvoDriverProperties;
    private final AuthenticatedRestTemplateService authenticatedRestTemplateService;
    private final ObjectMapper objectMapper;
    private final Map<String, NsPkgInfo> localNsPkgInfoCache = new ConcurrentHashMap<>();

    @Autowired
    public NexusNSPackageRepositoryDriver(NFVODriverProperties nfvoDriverProperties, AuthenticatedRestTemplateService authenticatedRestTemplateService, ObjectMapper objectMapper) {
        this.nfvoDriverProperties = nfvoDriverProperties;
        this.authenticatedRestTemplateService = authenticatedRestTemplateService;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<NsPkgInfo> queryAllNsPkgInfos(String groupName) {
        return queryNsPkgInfos(groupName, null);
    }

    @Override
    public NsPkgInfo getNsPkgInfo(String nsPackageId) throws NSPackageNotFoundException {
        List<NsPkgInfo> nsPkgInfoList = queryNsPkgInfos(null, nsPackageId);
        if (nsPkgInfoList.size() == 1) {
            return nsPkgInfoList.get(0);
        } else if (nsPkgInfoList.size() > 1) {
            throw new NSPackageNotFoundException(String.format("Too many matches found when searching for package information for NS package [%s]", nsPackageId));
        } else {
            throw new NSPackageNotFoundException(String.format("Cannot find package information for NS package [%s]", nsPackageId));
        }
    }

    private List<NsPkgInfo> queryNsPkgInfos(String groupName, String nsPackageId) {
        final MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.set("repository", nfvoDriverProperties.getPackageManagement().getRepositoryName());
        if (StringUtils.hasText(groupName)) {
            queryParameters.set("group", groupName);
        }
        if (StringUtils.hasText(nsPackageId)) {
            queryParameters.set("keyword", "*" + nsPackageId + nfvoDriverProperties.getPackageManagement().getNsPkgInfoSuffix() + "*");
        }

        // Get a list of components from Nexus
        List<ComponentInformation> componentList = getPaginatedResultsAsList(COMPONENT_SEARCH_URL, queryParameters, ComponentInformation.class);

        // Reduce list to only contain unique NsPkgInfo assets (and populate local cache, keyed by MD5 sum)
        return componentList.stream()
                .flatMap(c -> c.getAssets().stream())
                .filter(a -> a.getPath().endsWith(nfvoDriverProperties.getPackageManagement().getNsPkgInfoSuffix()))
                .map(a -> {
                    // TODO What about if there's no md5 checksum?
                    if (!localNsPkgInfoCache.containsKey(a.getChecksum().getMd5())) {
                        localNsPkgInfoCache.put(a.getChecksum().getMd5(), getNsPkgInfoFromUrl(a.getDownloadUrl()));
                    }
                    return localNsPkgInfoCache.get(a.getChecksum().getMd5());
                })
                .collect(Collectors.toList());
    }

    public Resource getNsPackage(String nsPackageId) throws NSPackageNotFoundException {

        // TODO some local caching would be nice

        String nsRepositoryUrl = nfvoDriverProperties.getPackageManagement().getPackageRepositoryUrl();
        if (Strings.isEmpty(nsRepositoryUrl)) {
            throw new NSPackageRepositoryException("A valid NS Package Repository URL must be configured.");
        }

        final MultiValueMap<String, String> queryParameters = new LinkedMultiValueMap<>();
        queryParameters.set("repository", nfvoDriverProperties.getPackageManagement().getRepositoryName());
        queryParameters.set("keyword", "*" + nsPackageId + nfvoDriverProperties.getPackageManagement().getNsPkgSuffix() + "*");

        // Get a list of components from Nexus
        List<AssetInformation> assetList = getPaginatedResultsAsList(ASSET_SEARCH_URL, queryParameters, AssetInformation.class);

        if (assetList.isEmpty()) {
            throw new NSPackageNotFoundException(String.format("NS Package [%s] not found in repository [%s]", nsPackageId, nsRepositoryUrl));
        } else if (assetList.size() > 1) {
            throw new NSPackageNotFoundException(String.format("Too many results [%s] when searching for NS Package [%s] in repository [%s]", assetList.size(), nsPackageId, nsRepositoryUrl));
        }

        final String nsDownloadPath = assetList.get(0).getDownloadUrl();
        logger.info("Attempting to load NS Package from location {}", nsDownloadPath);

        try {
            UrlResource nsPackage = new UrlResource(nsDownloadPath);

            if (!nsPackage.exists()) {
                throw new NSPackageNotFoundException(String.format("NS Package not found in repository at location [%s].", nsDownloadPath));
            }

            logger.info(" NS Package found at location {}", nsDownloadPath);

            return nsPackage;
        } catch (MalformedURLException e) {
            throw new NSPackageRepositoryException(String.format("The configured NS Package Repository location was invalid [%s].", nsDownloadPath), e);
        }

    }

    private RestTemplate getRestTemplate() {
        return authenticatedRestTemplateService.getRestTemplate(nfvoDriverProperties.getPackageManagement().getPackageRepositoryUrl(),
                nfvoDriverProperties.getPackageManagement().getAuthenticationProperties());
    }

    private NsPkgInfo getNsPkgInfoFromUrl(String downloadUrl) {
        logger.debug("Downloading NsPkgInfo: {}", downloadUrl);
        final ResponseEntity<String> responseEntity = getRestTemplate().exchange(downloadUrl, HttpMethod.GET, null, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String nsPkgInfoString = responseEntity.getBody();
            logger.debug("Got response\n{}", nsPkgInfoString);
            NsPkgInfo nsPkgInfo = null;
            try {
                nsPkgInfo = objectMapper.readValue(nsPkgInfoString, NsPkgInfo.class);
            } catch (IOException e) {
                throw new NSPackageRepositoryException(String.format("Exception parsing NsPkgInfo record from [%s]", downloadUrl), e);
            }
            return nsPkgInfo;
        }
        return null;
    }

    private <T> List<T> getPaginatedResultsAsList(final String baseUrl, final MultiValueMap<String, String> queryParameters, Class<T> returnClass) {
        final List<T> paginatedResults = new ArrayList<>();
        String continuationToken = null;
        int pageCount = 0;

        do {
            final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(nfvoDriverProperties.getPackageManagement().getPackageRepositoryUrl() + baseUrl).queryParams(queryParameters);
            if (continuationToken != null) {
                uriBuilder.queryParam("continuationToken", continuationToken);
            }

            logger.debug("Making paginated call to {}", uriBuilder.toUriString());
            ResponseEntity<PaginatedResults<T>> responseEntity = getRestTemplate()
                    .exchange(uriBuilder.toUriString(), HttpMethod.GET, null, ParameterizedTypeReference.forType(ResolvableType.forClassWithGenerics(PaginatedResults.class, returnClass).getType()));

            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                pageCount++;
                paginatedResults.addAll(responseEntity.getBody().getItems());
                continuationToken = responseEntity.getBody().getContinuationToken();
            } else {
                throw new NSPackageRepositoryException(String.format("Invalid status code [%s] while searching Nexus repository at [%s]", responseEntity.getStatusCode(), uriBuilder.toUriString()));
            }
        } while (continuationToken != null);

        logger.debug("Got paginated response with {} element(s) over {} page(s)", paginatedResults.size(), pageCount);
        return paginatedResults;
    }

}
