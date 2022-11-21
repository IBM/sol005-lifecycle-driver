package org.etsi.sol005.common;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Represents parameters needed to connect to a VIM for managing the resources of a VNF instance.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents parameters needed to connect to a VIM for managing the resources of a VNF instance.")
public class VimConnectionInfo {

    @Schema(name = "VIM Connection Id", required = true, description = "The identifier of the VIM Connection. This identifier is managed by the NFVO.")
    private String id;
    @Schema(name = "VIM Instance Id", description = "The identifier of the VIM instance. This identifier is managed by the NFVO.")
    private String vimId;
    @Schema(name = "VIM Type", required = true, description = "Discriminator for the different types of the VIM information.")
    private String vimType;
    @Schema(name = "VIM Interface Information", description = "Information about the interface or interfaces to the VIM, if applicable, such as the URI of an interface endpoint to communicate with the VIM. The applicable keys are dependent on the content of vimType.")
    private Map<String, String> interfaceInfo;
    @Schema(name = "VIM Authentication Information", description = "Authentication credentials for accessing the VIM, and other access-related information such as tenants or infrastructure resource groups (see note). The applicable keys are dependent on the content of vimType.")
    private Map<String, String> accessInfo;
    @Schema(name = "VIM-specific Additional Information", description = "VIM type specific additional information. The applicable structure, and whether or not this attribute is available, is dependent on the content of vimType.")
    private Map<String, String> extra;

}
