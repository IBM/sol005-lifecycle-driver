package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Request to find a reference")
public class FindReferenceRequest {

    @Schema(description = "Instance Name")
    private String instanceName;
    @Schema(description = "Deployment Location")
    private ResourceManagerDeploymentLocation deploymentLocation;
    @Schema(description = "driverFiles")
    private String driverFiles;

    public FindReferenceRequest() {}

    public FindReferenceRequest(String instanceName, String driverFiles, ResourceManagerDeploymentLocation deploymentLocation) {
        this.instanceName = instanceName;
        this.deploymentLocation = deploymentLocation;
        this.driverFiles = driverFiles;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public ResourceManagerDeploymentLocation getDeploymentLocation() {
        return deploymentLocation;
    }

    public void setDeploymentLocation(ResourceManagerDeploymentLocation deploymentLocation) {
        this.deploymentLocation = deploymentLocation;
    }

    public String getDriverFiles() {
        return driverFiles;
    }

    public void setDriverFiles(String driverFiles) {
        this.driverFiles = driverFiles;
    }

    @Override
    public String toString() {
        return "FindInfrastructureRequest{" +
                   "instanceName=" + instanceName +
                   ", deploymentLocation=" + deploymentLocation +
                   ", driverFiles='" + driverFiles + '\'' +
                   '}';
    }
}
