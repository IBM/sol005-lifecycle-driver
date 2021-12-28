package org.etsi.sol005.granting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents information regarding a resource zone.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a resource zone.")
public class ZoneInfo {

    @ApiModelProperty(name = "ZoneInfo Identifier", required = true, notes = "The identifier of this ZoneInfo instance, for the purpose of referencing it from other structures in the \"Grant\" structure.")
    private String id;
    @ApiModelProperty(name = "Zone Identifier", required = true, notes = "The identifier of the resource zone, as managed by the resource management layer (typically, the VIM).")
    private String zoneId;
    @ApiModelProperty(name = "VIM Connection Identifier", notes = "Identifier of the connection to the VIM that manages the resource zone. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \" vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported and present when VNF-related Resource Management in direct mode is applicable.")
    private String vimConnectionId;
    @ApiModelProperty(name = "Resource ProviderId Identifier", notes = "Identifies the entity responsible for the management the resource zone. This attribute shall only be supported and present when VNF-related Resource Management in indirect mode is applicable. The identification scheme is outside the scope of the present document.")
    private String resourceProviderId;

}
