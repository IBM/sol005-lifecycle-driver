package org.etsi.sol005.granting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * This type provides information regarding a VIM selection constraint. A set of such constraints may be sent by the VNFM to the NFVO to influence the VIM selection decisions made by the NFVO as part
 * of the granting process.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents a resource placement constraint.")
public class ConstraintResourceRef {

    @Schema(name = "Identifer Type", required = true, description = "The type of the identifier.")
    private ResourceIdentifierType idType;
    @Schema(name = "Resource Identifer", required = true, description = "An actual resource-management-level identifier (idType=RES_MGMT), or an identifier that references a \"ResourceDefinition\" structure in the related \"GrantRequest\" structure (idType=GRANT).")
    private String resourceId;
    @Schema(name = "VIM Connection Identifier", description = "Identifier of the VIM connection for managing the resource. It shall only be present when idType = RES_MGMT. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported when VNFrelated resource management in direct mode is applicable.")
    private String vimConnectionId;
    @Schema(name = "Resource Provider Identifier", description = "Identifier of the resource provider. It shall only be present when idType = RES_MGMT. This attribute shall only be supported when VNFrelated resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document.")
    private String resourceProviderId;
}
