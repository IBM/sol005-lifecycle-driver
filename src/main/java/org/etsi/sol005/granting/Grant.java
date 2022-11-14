package org.etsi.sol005.granting;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ExtManagedVirtualLinkData;
import org.etsi.sol005.common.ExtVirtualLinkData;
import org.etsi.sol005.common.Link;
import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



import lombok.Data;

/**
 * Represents a Grant resource.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a grant resource.")
public class Grant {

    @Schema(name = "Grant Id", required = true, description = "Identifier of the grant.")
    private String id;
    @Schema(name = "VNF Instance Id", required = true, description = "Identifier of the related VNF instance.")
    private String vnfInstanceId;
    @Schema(name = "VNF Lifecycle Management Operation Occurrence Id", required = true, description = "Identifier of the related VNF lifecycle management operation occurrence.")
    private String vnfLcmOpOccId;
    @Schema(name = "List of VIM Connections", description = "Provides information regarding VIM connections that are approved to be used by the VNFM to allocate resources, and provides parameters of these VIM connections. The VNFM shall update the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure by adding unknown entries received in this attribute. This attribute is not intended for the modification of VimConnection entries passed  earlier; for that, the VnfInfoModifications structure shall be used. This attribute shall only be supported when VNFrelated Resource Management in direct mode is applicable. In direct mode, this parameter shall be absent if the VIM information was configured to the VNFM in another way, present otherwise.")
    private List<VimConnectionInfo> vimConnections;
    @Schema(name = "Resource zones", description = "Identifies resource zones where the resources are approved to be allocated by the VNFM.")
    private List<ZoneInfo> zones;
    @Schema(name = "Resource zones", description = "Information about groups of resource zones that are related and that the NFVO has chosen to fulfil a zoneGroup constraint in the GrantVnfLifecycleOperation request. This information confirms that the NFVO has honoured the zoneGroup constraints that were passed as part of \"placementConstraints\" in the GrantRequest.")
    private List<ZoneGroupInfo> zoneGroups;
    @Schema(name = "Compute Reservation Identifier", description = "Information that identifies a reservation applicable to the compute resource requirements of the corresponding grant request.")
    private String computeReservationId;
    @Schema(name = "Network Reservation Identifier", description = "Information that identifies a reservation applicable to the network resource requirements of the corresponding grant request.")
    private String networkReservationId;
    @Schema(name = "Storage Reservation Identifier", description = "Information that identifies a reservation applicable to the storage resource requirements of the corresponding grant request.")
    private String storageReservationId;
    @Schema(name = "List of Resources to Add", description = "List of resources that are approved to be added, with one entry per resource.")
    private List<GrantInfo> addResources;
    @Schema(name = "List of Resources to Temporarily Instantiate", description = "List of resources that are approved to be temporarily instantiated during the runtime of the lifecycle operation, with one entry per resource.")
    private List<GrantInfo> tempResources;
    @Schema(name = "List of Resources to Remove", description = "List of resources that are approved to be removed, with one entry per resource.")
    private List<GrantInfo> removeResources;
    @Schema(name = "List of Resources to Update", description = "List of resources that are approved to be modified, with one entry per resource.")
    private List<GrantInfo> updateResources;
    @Schema(name = "VIM Assets", description = "Information about assets for the VNF that are managed by the NFVO in the VIM, such as software images and virtualised compute resource flavours.")
    private VimAssets vimAssets;
    @Schema(name = "External Virtual Links", description = "Information about external VLs to connect the VNF to.")
    private List<ExtVirtualLinkData> extVirtualLinks;
    @Schema(name = "Externally Managed Virtual Links", description = "Information about internal VLs that are managed by other entities than the VNFM.")
    private List<ExtManagedVirtualLinkData> extManagedVirtualLinks;
    @Schema(name = "Additional Parameters", description = "Additional parameters passed by the NFVO, specific to the VNF and the LCM operation.")
    private Map<String, String> additionalParams;
    @Schema(name = "Links", required = true, description = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Links to resources related to this resource.")
    public static class VimAssets {

        @Schema(name = "Compute Resource Flavours", description = "Mappings between virtual compute descriptors defined in the VNFD and compute resource flavours managed in the VIM.")
        private List<VimComputeResourceFlavour> computeResourceFlavours;
        @Schema(name = "Software Images", description = "Mappings between software images defined in the VNFD and software images managed in the VIM.")
        private List<VimSoftwareImage> softwareImages;
    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Schema(description = "Links to resources related to this resource.")
    public static class Links {

        @Schema(name = "self", required = true, description = "URI of this resource.")
        private Link self;
        @Schema(name = "VNF Lifecycle Management Operation Occurrence", required = true, description = "Related VNF lifecycle management operation occurrence.")
        private Link vnfLcmOpOcc;
        @Schema(name = "VNF Instance", description = "Related VNF instance.")
        private Link vnfInstance;

    }
}
