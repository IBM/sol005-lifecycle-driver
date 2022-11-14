package com.ibm.nfvodriver.model.alm;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;


@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Find reference response")
public class FindReferenceResponse {

    @Schema(description = "Associated Topology")
    private Map<String, InternalResourceInstance> associatedTopology = new HashMap<>();

    @Schema(description = "LM resource id")
    private String resourceId;

    @Schema(description = "Outputs")
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
