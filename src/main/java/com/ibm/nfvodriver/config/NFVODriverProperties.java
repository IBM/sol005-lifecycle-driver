package com.ibm.nfvodriver.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to the NFVO Driver.
 *
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "nfvodriver")
public class NFVODriverProperties {

    private final Async async = new Async();
    private final Topics topics = new Topics();
    private final Logging logging = new Logging();
    private final PackageManagement packageManagement = new PackageManagement();
    private final Grant grant = new Grant();
    private Duration executionResponseDelay = Duration.ofSeconds(5);
    private Duration lcmOpOccPollingDelay = Duration.ofSeconds(10);
    private Duration restConnectTimeout = Duration.ofSeconds(10);
    private Duration restReadTimeout = Duration.ofSeconds(60);

    public Async getAsync() {
        return async;
    }

    public Topics getTopics() {
        return topics;
    }

    public Logging getLogging() {
        return logging;
    }

    public PackageManagement getPackageManagement() {
        return packageManagement;
    }

    public Grant getGrant() {
        return grant;
    }

    public Duration getExecutionResponseDelay() {
        return executionResponseDelay;
    }

    public void setExecutionResponseDelay(Duration executionResponseDelay) {
        this.executionResponseDelay = executionResponseDelay;
    }

    public Duration getLcmOpOccPollingDelay() {
        return lcmOpOccPollingDelay;
    }

    public void setLcmOpOccPollingDelay(Duration lcmOpOccPollingDelay) {
        this.lcmOpOccPollingDelay = lcmOpOccPollingDelay;
    }

    public Duration getRestConnectTimeout() {
        return restConnectTimeout;
    }

    public void setRestConnectTimeout(Duration restConnectTimeout) {
        this.restConnectTimeout = restConnectTimeout;
    }

    public Duration getRestReadTimeout() {
        return restReadTimeout;
    }

    public void setRestReadTimeout(Duration restReadTimeout) {
        this.restReadTimeout = restReadTimeout;
    }

    public static class Async {
        private int corePoolSize = 4;
        private int maxPoolSize = 32;
        private int queueCapacity = 10000;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

    public static class Topics {
        private String lifecycleResponsesTopic;
        private String lcmOpOccPollingTopic;

        public String getLifecycleResponsesTopic() {
            return lifecycleResponsesTopic;
        }

        public void setLifecycleResponsesTopic(String lifecycleResponsesTopic) {
            this.lifecycleResponsesTopic = lifecycleResponsesTopic;
        }

        public String getLcmOpOccPollingTopic() {
            return lcmOpOccPollingTopic;
        }

        public void setLcmOpOccPollingTopic(String lcmOpOccPollingTopic) {
            this.lcmOpOccPollingTopic = lcmOpOccPollingTopic;
        }
    }

    public static class Logging {
        private int loggingRequestInterceptMaxBodySize = 10000000;

        public int getLoggingRequestInterceptMaxBodySize() {
            return loggingRequestInterceptMaxBodySize;
        }

        public void setLoggingRequestInterceptMaxBodySize(int loggingRequestInterceptMaxBodySize) {
            this.loggingRequestInterceptMaxBodySize = loggingRequestInterceptMaxBodySize;
        }
    }

    public static class PackageManagement {
        boolean enabled;
        private String packageRepositoryUrl;
        private String imageArtifactFilter;
        private RepositoryType repositoryType = RepositoryType.NEXUS;
        private String repositoryName;
        private String nexusGroupName;
        private String nsPkgInfoSuffix = ".pkgInfo";
        private String nsPkgSuffix = ".zip";
        private final Map<String, String> authenticationProperties = new HashMap<>();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getPackageRepositoryUrl() {
            return packageRepositoryUrl;
        }

        public void setPackageRepositoryUrl(String packageRepositoryUrl) {
            this.packageRepositoryUrl = packageRepositoryUrl;
        }

        public String getImageArtifactFilter() {
            return imageArtifactFilter;
        }

        public void setImageArtifactFilter(String imageArtifactFilter) {
            this.imageArtifactFilter = imageArtifactFilter;
        }

        public RepositoryType getRepositoryType() {
            return repositoryType;
        }

        public void setRepositoryType(RepositoryType repositoryType) {
            this.repositoryType = repositoryType;
        }

        public String getRepositoryName() {
            return repositoryName;
        }

        public void setRepositoryName(String repositoryName) {
            this.repositoryName = repositoryName;
        }

        public String getNexusGroupName() {
            return nexusGroupName;
        }

        public void setNexusGroupName(String nexusGroupName) {
            this.nexusGroupName = nexusGroupName;
        }

        public String getNsPkgInfoSuffix() {
            return nsPkgInfoSuffix;
        }

        public void setNsPkgInfoSuffix(String nsPkgInfoSuffix) {
            this.nsPkgInfoSuffix = nsPkgInfoSuffix;
        }

        public String getNsPkgSuffix() {
            return nsPkgSuffix;
        }

        public void setNsPkgSuffix(String nsPkgSuffix) {
            this.nsPkgSuffix = nsPkgSuffix;
        }

        public Map<String, String> getAuthenticationProperties() {
            return authenticationProperties;
        }

        public enum RepositoryType {
            NEXUS
        }
    }

    public static class Grant {

        private boolean automatic;

        private final Provider provider = new Provider();

        public boolean isAutomatic() {
            return automatic;
        }

        public void setAutomatic(boolean automatic) {
            this.automatic = automatic;
        }

        public Provider getProvider() {
            return provider;
        }

        public static class Provider {
            private String url;
            private final Authentication authentication = new Authentication();

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Authentication getAuthentication() {
                return authentication;
            }

        }
    }

    public static class Authentication {

        private String type;
        private String username;
        private String password;
        private String authenticationUrl;
        private String accessTokenUri;
        private String clientId;
        private String clientSecret;
        private String scope;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAuthenticationUrl() {
            return authenticationUrl;
        }

        public void setAuthenticationUrl(String authenticationUrl) {
            this.authenticationUrl = authenticationUrl;
        }

        public String getAccessTokenUri() {
            return accessTokenUri;
        }

        public void setAccessTokenUri(String accessTokenUri) {
            this.accessTokenUri = accessTokenUri;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

    }

}
