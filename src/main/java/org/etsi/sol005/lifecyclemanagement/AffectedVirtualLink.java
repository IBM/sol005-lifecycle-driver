package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ResourceHandle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents information about added, deleted, modified and temporary VLs.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents information about added, deleted, modified and temporary VLs.")
public class AffectedVirtualLink {

    @Schema(name = "Id", required = true, description = "Identifier of the virtual link instance, identifying the applicable \"vnfVirtualLinkResourceInfo\" entry in the \"VnfInstance\" data type.")
    private String id;
    @Schema(name = "Virtual Link Descriptor Id", required = true, description = "Identifier of the related VLD in the VNFD.")
    private String virtualLinkDescId;
    @Schema(name = "Change Type", required = true, description = "Signals the type of change.")
    private ChangeType changeType;
    @Schema(name = "Network Resource", required = true, description = "Reference to the VirtualNetwork resource. Detailed information is (for new and modified resources) or has been (for removed resources) available from the VIM.")
    private ResourceHandle networkResource;
    @Schema(name = "Metadata", description = "Metadata about this resource. The content of this attribute shall be a copy of the content of the \"metadata\" attribute of the VnfcResourceInfo structure.")
    private Map<String, String> metadata;

    public enum ChangeType {
        ADDED, REMOVED, MODIFIED, TEMPORARY, LINK_PORT_ADDED, LINK_PORT_REMOVED
    }

}
