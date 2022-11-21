package org.etsi.sol005.lifecyclemanagement;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ExtManagedVirtualLinkData;
import org.etsi.sol005.common.ExtVirtualLinkData;
import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Represents request parameters for the "Change VNF flavour" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents request parameters for the \"Change VNF flavour\" operation.")
public class ChangeVnfFlavourRequest {

    @Schema(name = "New Flavour Id", required = true, description = "Identifier of the VNF deployment flavour to be instantiated.")
    private String newFlavourId;
    @Schema(name = "Instantiation Level Id", description = "Identifier of the instantiation level of the deployment flavour to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated.")
    private String instantiationLevelId;
    @Schema(name = "External Virtual Link Information", description = "Information about external VLs to connect the VNF to.")
    private List<ExtVirtualLinkData> extVirtualLinks;
    @Schema(name = "External Managed Virtual Link Information", description = "Information about internal VLs that are managed by the NFVO.")
    private List<ExtManagedVirtualLinkData> extManagedVirtualLinks;
    @Schema(name = "VIM Connection Information", description = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable.")
    private List<VimConnectionInfo> vimConnectionInfo;
    @Schema(name = "Additional Parameters", description = "Additional input parameters for the flavour change process, specific to the VNF being modified, as declared in the VNFD as part of \"ChangeVnfFlavourOpConfig\".")
    private Map<String, String> additionalParams;

}
