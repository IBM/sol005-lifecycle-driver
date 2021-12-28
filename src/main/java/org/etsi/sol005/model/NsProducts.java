package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NsProducts {
    @JsonProperty("versions")
    private List<Versions> versions;

    public List<Versions> getVersions() {
        return versions;
    }

    public void setVersions(List<Versions> versions) {
        this.versions = versions;
    }

    @JsonProperty("nsProductName")
    public String nsProductName;

    public NsProducts(String nsProductName, List<Versions> versions) {
        this.nsProductName = nsProductName;
        this.versions = versions;
    }

    public NsProducts(String nsProductName) {
        this.nsProductName = nsProductName;
    }

    public NsProducts() {
    }
    
    public String getNsProductName() {
        return nsProductName;
    }

    public void setNsProductName(String nsProductName) {
        this.nsProductName = nsProductName;
    }
}
