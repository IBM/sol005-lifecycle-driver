package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents the information that allows addressing a virtualised resource that is used by a VNF instance.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents the information that allows addressing a virtualised resource that is used by a VNF instance.")
public class VirtualStorageResourceInfo {

    @ApiModelProperty(name = "Id", required = true, notes = "Identifier of this VirtualStorageResourceInfo instance.")
    private String id;
    @ApiModelProperty(name = "Virtual Storage Descriptor Id", required = true, notes = "Identifier of the VirtualStorageDesc in the VNFD.")
    private String virtualStorageDescId;
    @ApiModelProperty(name = "Storage Resource", required = true, notes = "Reference to the VirtualStorage resource.")
    private ResourceHandle storageResource;
    @ApiModelProperty(name = "Reservation Id", notes = "The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists.")
    private String reservationId;
    @ApiModelProperty(name = "Metadata", notes = "Metadata about this resource.")
    private Map<String, String> metadata;

}
