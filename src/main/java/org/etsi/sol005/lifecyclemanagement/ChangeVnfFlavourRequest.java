package org.etsi.sol005.lifecyclemanagement;

import java.util.List;
import java.util.Map;

import org.etsi.sol005.common.ExtManagedVirtualLinkData;
import org.etsi.sol005.common.ExtVirtualLinkData;
import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents request parameters for the "Change VNF flavour" operation.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents request parameters for the \"Change VNF flavour\" operation.")
public class ChangeVnfFlavourRequest {

    @ApiModelProperty(name = "New Flavour Id", required = true, notes = "Identifier of the VNF deployment flavour to be instantiated.")
    private String newFlavourId;
    @ApiModelProperty(name = "Instantiation Level Id", notes = "Identifier of the instantiation level of the deployment flavour to be instantiated. If not present, the default instantiation level as declared in the VNFD is instantiated.")
    private String instantiationLevelId;
    @ApiModelProperty(name = "External Virtual Link Information", notes = "Information about external VLs to connect the VNF to.")
    private List<ExtVirtualLinkData> extVirtualLinks;
    @ApiModelProperty(name = "External Managed Virtual Link Information", notes = "Information about internal VLs that are managed by the NFVO.")
    private List<ExtManagedVirtualLinkData> extManagedVirtualLinks;
    @ApiModelProperty(name = "VIM Connection Information", notes = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external / externally-managed virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable.")
    private List<VimConnectionInfo> vimConnectionInfo;
    @ApiModelProperty(name = "Additional Parameters", notes = "Additional input parameters for the flavour change process, specific to the VNF being modified, as declared in the VNFD as part of \"ChangeVnfFlavourOpConfig\".")
    private Map<String, String> additionalParams;

}
