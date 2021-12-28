package org.etsi.sol005.granting;

import java.util.List;
import java.util.Map;

import org.etsi.sol005.common.ExtManagedVirtualLinkData;
import org.etsi.sol005.common.ExtVirtualLinkData;
import org.etsi.sol005.common.Link;
import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents a Grant resource.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a grant resource.")
public class Grant {

    @ApiModelProperty(name = "Grant Id", required = true, notes = "Identifier of the grant.")
    private String id;
    @ApiModelProperty(name = "VNF Instance Id", required = true, notes = "Identifier of the related VNF instance.")
    private String vnfInstanceId;
    @ApiModelProperty(name = "VNF Lifecycle Management Operation Occurrence Id", required = true, notes = "Identifier of the related VNF lifecycle management operation occurrence.")
    private String vnfLcmOpOccId;
    @ApiModelProperty(name = "List of VIM Connections", notes = "Provides information regarding VIM connections that are approved to be used by the VNFM to allocate resources, and provides parameters of these VIM connections. The VNFM shall update the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure by adding unknown entries received in this attribute. This attribute is not intended for the modification of VimConnection entries passed  earlier; for that, the VnfInfoModifications structure shall be used. This attribute shall only be supported when VNFrelated Resource Management in direct mode is applicable. In direct mode, this parameter shall be absent if the VIM information was configured to the VNFM in another way, present otherwise.")
    private List<VimConnectionInfo> vimConnections;
    @ApiModelProperty(name = "Resource zones", notes = "Identifies resource zones where the resources are approved to be allocated by the VNFM.")
    private List<ZoneInfo> zones;
    @ApiModelProperty(name = "Resource zones", notes = "Information about groups of resource zones that are related and that the NFVO has chosen to fulfil a zoneGroup constraint in the GrantVnfLifecycleOperation request. This information confirms that the NFVO has honoured the zoneGroup constraints that were passed as part of \"placementConstraints\" in the GrantRequest.")
    private List<ZoneGroupInfo> zoneGroups;
    @ApiModelProperty(name = "Compute Reservation Identifier", notes = "Information that identifies a reservation applicable to the compute resource requirements of the corresponding grant request.")
    private String computeReservationId;
    @ApiModelProperty(name = "Network Reservation Identifier", notes = "Information that identifies a reservation applicable to the network resource requirements of the corresponding grant request.")
    private String networkReservationId;
    @ApiModelProperty(name = "Storage Reservation Identifier", notes = "Information that identifies a reservation applicable to the storage resource requirements of the corresponding grant request.")
    private String storageReservationId;
    @ApiModelProperty(name = "List of Resources to Add", notes = "List of resources that are approved to be added, with one entry per resource.")
    private List<GrantInfo> addResources;
    @ApiModelProperty(name = "List of Resources to Temporarily Instantiate", notes = "List of resources that are approved to be temporarily instantiated during the runtime of the lifecycle operation, with one entry per resource.")
    private List<GrantInfo> tempResources;
    @ApiModelProperty(name = "List of Resources to Remove", notes = "List of resources that are approved to be removed, with one entry per resource.")
    private List<GrantInfo> removeResources;
    @ApiModelProperty(name = "List of Resources to Update", notes = "List of resources that are approved to be modified, with one entry per resource.")
    private List<GrantInfo> updateResources;
    @ApiModelProperty(name = "VIM Assets", notes = "Information about assets for the VNF that are managed by the NFVO in the VIM, such as software images and virtualised compute resource flavours.")
    private VimAssets vimAssets;
    @ApiModelProperty(name = "External Virtual Links", notes = "Information about external VLs to connect the VNF to.")
    private List<ExtVirtualLinkData> extVirtualLinks;
    @ApiModelProperty(name = "Externally Managed Virtual Links", notes = "Information about internal VLs that are managed by other entities than the VNFM.")
    private List<ExtManagedVirtualLinkData> extManagedVirtualLinks;
    @ApiModelProperty(name = "Additional Parameters", notes = "Additional parameters passed by the NFVO, specific to the VNF and the LCM operation.")
    private Map<String, String> additionalParams;
    @ApiModelProperty(name = "Links", required = true, notes = "Links to resources related to this resource.")
    @JsonProperty("_links")
    private Links links;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Links to resources related to this resource.")
    public static class VimAssets {

        @ApiModelProperty(name = "Compute Resource Flavours", notes = "Mappings between virtual compute descriptors defined in the VNFD and compute resource flavours managed in the VIM.")
        private List<VimComputeResourceFlavour> computeResourceFlavours;
        @ApiModelProperty(name = "Software Images", notes = "Mappings between software images defined in the VNFD and software images managed in the VIM.")
        private List<VimSoftwareImage> softwareImages;
    }

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Links to resources related to this resource.")
    public static class Links {

        @ApiModelProperty(name = "self", required = true, notes = "URI of this resource.")
        private Link self;
        @ApiModelProperty(name = "VNF Lifecycle Management Operation Occurrence", required = true, notes = "Related VNF lifecycle management operation occurrence.")
        private Link vnfLcmOpOcc;
        @ApiModelProperty(name = "VNF Instance", notes = "Related VNF instance.")
        private Link vnfInstance;

    }
}
