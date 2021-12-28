package org.etsi.ifa013;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NsLcmOperationsConfiguration {

    // NOTE: This does not specifically exist in the ETSI IFA013 3.3.1 version of the specification, but it allows us to define additionalParams passed in the CreateNsRequest message
    private CreateNsOpConfig createNsOpConfig;
    private InstantiateNsOpConfig instantiateNsOpConfig;
    private ScaleNsOpConfig scaleNsOpConfig;
    private ScaleNsToLevelOpConfig scaleNsToLevelOpConfig;
    private ChangeNsFlavourOpConfig changeNsFlavourOpConfig;
    private HealNsOpConfig healNsOpConfig;
    private TerminateNsOpConfig terminateNsOpConfig;
    private OperateNsOpConfig operateNsOpConfig;
    private ChangeExtNsConnectivityOpConfig changeExtNsConnectivityOpConfig;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CreateNsOpConfig {

        private Map<String, String> parameter;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InstantiateNsOpConfig {

        private Map<String, String> parameter;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ScaleNsOpConfig {

        private Map<String, String> parameter;
        private Boolean scalingByMoreThanOneStepSupported;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ScaleNsToLevelOpConfig {

        private Map<String, String> parameter;
        private Boolean arbitraryTargetLevelsSupported;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChangeNsFlavourOpConfig {

        private Map<String, String> parameter;
        private Boolean arbitraryTargetLevelsSupported;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class HealNsOpConfig {

        private Map<String, String> parameter;
        private List<String> cause;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TerminateNsOpConfig {

        private Map<String, String> parameter;
        private Integer minGracefulTerminationTimeout;
        private Integer maxRecommendedGracefulTerminationTimeout;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OperateNsOpConfig {

        private Map<String, String> parameter;
        private Integer minGracefulStopTimeout;
        private Integer maxRecommendedGracefulStopTimeout;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChangeExtNsConnectivityOpConfig {

        private Map<String, String> parameter;

    }



}
