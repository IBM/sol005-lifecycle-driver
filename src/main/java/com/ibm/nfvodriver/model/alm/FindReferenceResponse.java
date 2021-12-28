package com.ibm.nfvodriver.model.alm;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Find reference response")
public class FindReferenceResponse {

    @ApiModelProperty(value = "Associated Topology")
    private Map<String, InternalResourceInstance> associatedTopology = new HashMap<>();

    @ApiModelProperty(value = "LM resource id")
    private String resourceId;

    @ApiModelProperty(value = "Outputs")
    private Map<String, String> outputs = new HashMap<>();

    public FindReferenceResponse() {}

    public FindReferenceResponse(String resourceId, Map<String, InternalResourceInstance> associatedTopology, Map<String, String> outputs) {
        this.resourceId = resourceId;
        this.associatedTopology.putAll(associatedTopology);
        this.outputs = outputs;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Map<String, InternalResourceInstance> getAssociatedTopology() {
        return associatedTopology;
    }

    public void setAssociatedTopology(Map<String, InternalResourceInstance> associatedTopology) {
        this.associatedTopology.putAll(associatedTopology);
    }

    public Map<String, String> getOutputs() {
        return outputs;
    }

    public void setOutputs(Map<String, String> outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return "FindInfrastructureResult{" +
                "associatedTopology=" + associatedTopology +
                "resourceId=" + resourceId +
                ", outputs=" + outputs +
                '}';
    }
}
