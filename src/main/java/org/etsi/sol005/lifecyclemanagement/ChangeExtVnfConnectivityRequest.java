package org.etsi.sol005.lifecyclemanagement;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.ExtVirtualLinkData;
import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



import lombok.Data;

/**
 * Represents request parameters for the "Change external VNF connectivity" operation to modify the external connectivity of a VNF instance.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents request parameters for the \"Change external VNF connectivity\" operation to modify the external connectivity of a VNF instance.")
public class ChangeExtVnfConnectivityRequest {

    @Schema(name = "External Virtual Link Information", required = true, description = "Information about external VLs to change (e.g. connect the VNF to).")
    private List<ExtVirtualLinkData> extVirtualLinks;
    @Schema(name = "VIM Connection Information", description = "Information about VIM connections to be used for managing the resources for the VNF instance, or refer to external virtual links. This attribute shall only be supported and may be present if VNF-related resource management in direct mode is applicable.")
    private List<VimConnectionInfo> vimConnectionInfo;
    @Schema(name = "Additional Parameters", description = "Additional parameters passed by the NFVO as input to the process, specific to the VNF of which the external connectivity is changed, as declared in the VNFD as part of \" ChangeExtVnfConnectivityOpConfig\".")
    private Map<String, String> additionalParams;

}
