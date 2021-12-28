package org.etsi.sol005.common;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents parameters needed to connect to a VIM for managing the resources of a VNF instance.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents parameters needed to connect to a VIM for managing the resources of a VNF instance.")
public class VimConnectionInfo {

    @ApiModelProperty(name = "VIM Connection Id", required = true, notes = "The identifier of the VIM Connection. This identifier is managed by the NFVO.")
    private String id;
    @ApiModelProperty(name = "VIM Instance Id", notes = "The identifier of the VIM instance. This identifier is managed by the NFVO.")
    private String vimId;
    @ApiModelProperty(name = "VIM Type", required = true, notes = "Discriminator for the different types of the VIM information.")
    private String vimType;
    @ApiModelProperty(name = "VIM Interface Information", notes = "Information about the interface or interfaces to the VIM, if applicable, such as the URI of an interface endpoint to communicate with the VIM. The applicable keys are dependent on the content of vimType.")
    private Map<String, String> interfaceInfo;
    @ApiModelProperty(name = "VIM Authentication Information", notes = "Authentication credentials for accessing the VIM, and other access-related information such as tenants or infrastructure resource groups (see note). The applicable keys are dependent on the content of vimType.")
    private Map<String, String> accessInfo;
    @ApiModelProperty(name = "VIM-specific Additional Information", notes = "VIM type specific additional information. The applicable structure, and whether or not this attribute is available, is dependent on the content of vimType.")
    private Map<String, String> extra;

}
