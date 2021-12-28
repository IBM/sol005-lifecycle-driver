package org.etsi.sol005.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Versions {
    @JsonProperty("vnfSoftwareVersion")
    String vnfSoftwareVersion;

    @JsonProperty("vnfdVersions")
    List<String> vnfdVersions;

    public Versions(String vnfSoftwareVersion, List<String> vnfdVersions) {
        this.vnfSoftwareVersion = vnfSoftwareVersion;
        this.vnfdVersions = vnfdVersions;
    }

    public Versions(String vnfSoftwareVersion) {
        this.vnfSoftwareVersion = vnfSoftwareVersion;
    }

    public Versions() {
    }

    public String getVnfSoftwareVersion() {
        return vnfSoftwareVersion;
    }

    public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
        this.vnfSoftwareVersion = vnfSoftwareVersion;
    }

    public List<String> getVnfdVersions() {
        return vnfdVersions;
    }

    public void setVnfdVersions(List<String> vnfdVersions) {
        this.vnfdVersions = vnfdVersions;
    }
}
