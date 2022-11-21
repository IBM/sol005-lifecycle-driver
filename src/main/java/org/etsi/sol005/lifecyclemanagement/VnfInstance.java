package org.etsi.sol005.lifecyclemanagement;

import java.util.List;
import java.util.Map;

import org.etsi.sol005.common.Link;
import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents a VNF instance
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a VNF instance")
public class VnfInstance {

    @Schema(name = "Id", required = true, description = "Identifier of the VNF instance.")
    private String id;
    @Schema(name = "VNF Instance Name", description = "Name of the VNF instance.")
    private String vnfInstanceName;
    @Schema(name = "VNF Instance Description", description = "Human-readable description of the VNF instance.")
    private String vnfInstanceDescription;
    @Schema(name = "VNFD Id", required = true, description = "Identifier of the VNFD on which the VNF instance is based.")
    private String vnfdId;
    @Schema(name = "VNF Provider", required = true, description = "Provider of the VNF and the VNFD. The value is copied from the VNFD.")
    private String vnfProvider;
    @Schema(name = "VNF Product Name", required = true, description = "Name to identify the VNF Product. The value is copied from the VNFD.")
    private String vnfProductName;
    @Schema(name = "VNF Software Version", required = true, description = "Software version of the VNF. The value is copied from the VNFD.")
    private String vnfSoftwareVersion;
    @Schema(name = "VNFD Version", required = true, description = "Identifies the version of the VNFD. The value is copied from the VNFD.")
    private String vnfdVersion;
    @Schema(name = "Onboarded VNF Package Information Id", required = true, description = "Identifier of information held by the NFVO about the specific VNF package on which the VNF is based.")
    private String vnfPkgId;
    @Schema(name = "VNF Configurable Properties", description = "Current values of the configurable properties of the VNF instance.")
    private Map<String, String> vnfConfigurableProperties;
    @Schema(name = "VIM Connection Information", description = "Information about VIM connections to be used for managing the resources for the VNF instance. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.")
    private List<VimConnectionInfo> vimConnectionInfo;
    @Schema(name = "Instantiation State", required = true, description = "The instantiation state of the VNF.")
    private InstantiationState instantiationState;
    @Schema(name = "Instantiated VNF Information", description = "Information specific to an instantiated VNF instance. This attribute shall be present if the instantiateState attribute value is INSTANTIATED.")
    private InstantiatedVnfInfo instantiatedVnfInfo;
    @Schema(name = "Metadata", description = "Additional VNF-specific metadata describing the VNF instance.")
    private Map<String, String> metadata;
    @Schema(name = "Extensions", description = "VNF-specific attributes that affect the lifecycle management of this VNF instance by the VNFM, or the lifecycle management scripts.")
    private Map<String, String> extensions;
    @Schema(name = "Links", required = true, description = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Information specific to an instantiated VNF instance.")
    public static class InstantiatedVnfInfo {

        @Schema(name = "Flavour Id", required = true, description = "Identifier of the VNF deployment flavour applied to this VNF instance.")
        private String flavourId;
        @Schema(name = "VNF Operational State", required = true, description = "State of the VNF instance.")
        private VnfOperationalStateType vnfState;
        @Schema(name = "Scale Status", description = "Scale status of the VNF, one entry per aspect. Represents for every scaling aspect how \"big\" the VNF has been scaled w.r.t. that aspect.")
        private List<ScaleInfo> scaleStatus;
        @Schema(name = "External Connection Point Information", required = true, description = "Information about the external CPs exposed by the VNF instance.")
        private List<VnfInstance.ExtCpInfo> extCpInfo;
        @Schema(name = "External Virtual Link Information", description = "Information about the external VLs the VNF instance is connected to.")
        private List<ExtVirtualLinkInfo> extVirtualLinkInfo;
        @Schema(name = "External Managed Virtual Link Information", description = "Information about the externally-managed internal VLs of the VNF instance.")
        private List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo;
        @Schema(name = "Monitoring Parameters", description = "Active monitoring parameters.")
        private List<MonitoringParameter> monitoringParameters;
        @Schema(name = "Localization Language", description = "Information about localization language of the VNF (includes e.g. strings in the VNFD). The localization languages supported by a VNF can be declared in the VNFD, and localization language selection can take place at instantiation time. The value shall comply with the format defined in IETF RFC 5646.")
        private String localizationLanguage;
        @Schema(name = "VNFC Resource Information", description = "Information about the virtualised compute and storage resources used by the VNFCs of the VNF instance.")
        private List<VnfcResourceInfo> vnfcResourceInfo;
        @Schema(name = "VNF Virtual Link Resource Information", description = "Information about the virtualised network resources used by the VLs of the VNF instance.")
        private List<VnfVirtualLinkResourceInfo> vnfVirtualLinkResourceInfo;
        @Schema(name = "Virtual Storage Resource Information", description = "Information about the virtualised storage resources used as storage for the VNF instance.")
        private List<VirtualStorageResourceInfo> virtualStorageResourceInfo;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Information about the external CPs exposed by the VNF instance.")
    public static class ExtCpInfo {

        @Schema(name = "External CP Id", required = true, description = "Identifier of the external CP instance and the related information instance.")
        private String id;
        @Schema(name = "External CPD Id", required = true, description = "Identifier of the external CPD, VnfExtCpd, in the VNFD.")
        private String cpdId;
        @Schema(name = "CP Protocol Information", description = "Network protocol information for this CP.")
        private List<CpProtocolInfo> cpProtocolInfo;
        @Schema(name = "External Link Port Id", description = "Identifier of the \"extLinkPortInfo\" structure inside the \"extVirtualLinkInfo\" structure. Shall be present if the CP is associated to a link port.")
        private String extLinkPortId;

    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Links to resources related to this resource.")
    public static class Links {

        @Schema(name = "self", required = true, description = "URI of this resource.")
        private Link self;
        @Schema(name = "indicators", description = "Indicators related to this VNF instance, if applicable.")
        private Link indicators;
        @Schema(name = "instantiate", description = "Link to the \"instantiate\" task resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance in NOT_INSTANTIATED state).")
        private Link instantiate;
        @Schema(name = "terminate", description = "Link to the \"terminate\" task resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state).")
        private Link terminate;
        @Schema(name = "scale", description = "Link to the \"scale\" task resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state).")
        private Link scale;
        @Schema(name = "scaleToLevel", description = "Link to the \"scale_to_level\" task resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state).")
        private Link scaleToLevel;
        @Schema(name = "changeFlavour", description = "Link to the \"change_flavour\" task resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state).")
        private Link changeFlavour;
        @Schema(name = "heal", description = "Link to the \"heal\" task resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state).")
        private Link heal;
        @Schema(name = "operate", description = "Link to the \"operate\" task resource, if the related operation is supported for this VNF instance, and is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state).")
        private Link operate;
        @Schema(name = "changeExtConn", description = "Link to the \"change_ext_conn\" task resource, if the related operation is possible based on the current status of this VNF instance resource (i.e. VNF instance is in INSTANTIATED state).")
        private Link changeExtConn;

    }
}
