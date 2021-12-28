package org.etsi.sol005.lifecyclemanagement;

import java.util.List;
import java.util.Map;

import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Links to resources related to this notification.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Links to resources related to this notification.")
public class VnfcResourceInfo {

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of this VnfcResourceInfo instance.")
    private String id;
    @ApiModelProperty(name = "VDU Id", required = true, notes = "Reference to the applicable VDU in the VNFD.")
    private String vduId;
    @ApiModelProperty(name = "Compute Resource", required = true, notes = "Reference to the VirtualCompute resource.")
    private ResourceHandle computeResource;
    @ApiModelProperty(name = "Storage Resource Ids", notes = "References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance.")
    private List<String> storageResourceIds;
    @ApiModelProperty(name = "Reservation Id", notes = "The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists.")
    private String reservationId;
    @ApiModelProperty(name = "VNFC Connection Point Information", notes = "CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is associated to an external CP of the VNF instance. May be present otherwise.")
    private List<VnfcCpInfo> vnfcCpInfo;
    @ApiModelProperty(name = "Metadata", notes = "Metadata about this resource.")
    private Map<String, String> metadata;

    /**
     * Represents VNFC Connection Point Information
     */
    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ApiModel(description = "Represents VNFC Connection Point Information.")
    public static class VnfcCpInfo {

        @ApiModelProperty(name = "Id", required = true, notes = "Identifier of this VNFC CP instance and the associated array entry.")
        private String id;
        @ApiModelProperty(name = "CPD Id", required = true, notes = "Identifier of the VDU CPD, cpdId, in the VNFD.")
        private String cpdId;
        @ApiModelProperty(name = "VNF External Connection Point Id", notes = "When the VNFC CP is exposed as external CP of the VNF, the identifier of this external VNF CP.")
        private String vnfExtCpId;
        @ApiModelProperty(name = "Connection Point Protocol Information", notes = "Network protocol information for this CP.")
        private CpProtocolInfo cpProtocolInfo;
        @ApiModelProperty(name = "VNF Link Port Id", notes = "Identifier of the \"vnfLinkPorts\" structure in the \"vnfVirtualLinkResourceInfo\" structure. Shall be present if the CP is associated to a link port.")
        private String vnfLinkPortId;

    }

}
