package org.etsi.sol005.granting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * This type contains a mapping between a software image definition the VNFD and the corresponding software image managed by the NFVO in the VIM which is needed during compute resource instantiation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents the mapping between a virtual compute descriptor in the VNFD and the corresponding compute resource flavour.")
public class VimSoftwareImage {

    @Schema(name = "VIM Connection Identifier", description = "Identifier of the VIM connection to access the software image referenced in this structure. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.")
    private String vimConnectionId;
    @Schema(name = "Resource Provider Identifier", description = "Identifies the entity responsible for the management of the virtualised resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document.")
    private String resourceProviderId;
    @Schema(name = "VNFD Software Image Identifier", required = true, description = "Identifier which references the software image descriptor in the VNFD.")
    private String vnfdSoftwareImageId;
    @Schema(name = "VIM Software Image Identifier", required = true, description = "Identifier of the software image in the resource management layer (i.e. VIM).")
    private String vimSoftwareImageId;

}
