package com.ibm.nfvodriver.model;

import com.ibm.nfvodriver.model.alm.ResourceManagerDeploymentLocation;

import static com.ibm.nfvodriver.utils.Constants.KAFKA_MESSAGE_VERSION;

public class LcmOpOccPollingRequest {

    private final ResourceManagerDeploymentLocation deploymentLocation;
    private final String nsLcmOpOccId;
    private final String version = KAFKA_MESSAGE_VERSION;

    public LcmOpOccPollingRequest(ResourceManagerDeploymentLocation deploymentLocation, String nsLcmOpOccId) {
        this.deploymentLocation = deploymentLocation;
        this.nsLcmOpOccId = nsLcmOpOccId;
    }

    public ResourceManagerDeploymentLocation getDeploymentLocation() {
        return deploymentLocation;
    }

    public String getNsLcmOpOccId() {
        return nsLcmOpOccId;
    }

    public String getVersion() {
        return version;
    }

}
