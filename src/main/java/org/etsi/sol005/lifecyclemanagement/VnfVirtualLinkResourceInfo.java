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
 * Represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance.")
public class VnfVirtualLinkResourceInfo {

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of this VnfVirtualLinkResourceInfo instance.")
    private String id;
    @ApiModelProperty(name = "Virtual Link Descriptor Id", required = true, notes = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD.")
    private String vnfVirtualLinkDescId;
    @ApiModelProperty(name = "Network Resource", required = true, notes = "Reference to the VirtualNetwork resource.")
    private ResourceHandle networkResource;
    @ApiModelProperty(name = "Reservation Id", notes = "The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists.")
    private String reservationId;
    @ApiModelProperty(name = "VNFC Link Ports", notes = "Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPort). May be present otherwise.")
    private List<VnfLinkPortInfo> vnfLinkPorts;
    @ApiModelProperty(name = "Metadata", notes = "Metadata about this resource.")
    private Map<String, String> metadata;

}
