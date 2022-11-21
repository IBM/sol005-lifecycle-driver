package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

//import org.etsi.sol005.common.VimConnectionInfo;

/**
 * Represents attribute modifications for an "Individual NS instance" resource, i.e. modifications to a resource representation based on the "NsInstance" data type.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Represents attribute modifications for an \"Individual  instance\" resource, i.e. modifications to a resource representation based on the \"NsInstance\" data type.")
public class NsInfoModificationRequest {

    @Schema(name = "NS Instance Name", description = "New value of the \"nsInstanceName\" attribute in \"NsInstance\", or \"null\" to remove the attribute.")
    private String nsInstanceName;
    @Schema(name = "NS Instance Description", description = "New value of the \"nsInstanceDescription\" attribute in \"NsInstance\", or \"null\" to remove the attribute.")
    private String nsInstanceDescription;
    @Schema(name = "Onboarded NS Package Information Id", description = "New value of the \"nsPkgId\" attribute in \"NsInstance\". The value \"null\" is not permitted.")
    private String nsPkgId;
    @Schema(name = "NS Configurable Properties", description = "Modifications of the \"nsConfigurableProperties\" attribute in \"NsInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396)")
    private Map<String, String> nsConfigurableProperties;
    @Schema(name = "Metadata", description = "Modifications of the \"metadata\" attribute in \"NsInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396)")
    private Map<String, String> metadata;
    @Schema(name = "Extensions", description = "Modifications of the \"extensions\" attribute in \"NsInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396).")
    private Map<String, String> extensions;
//    @Schema(name = "VIM Connection Information", description = "New content of certain entries in the \"vimConnectionInfo\" attribute array in \"NsInstance\", as defined below this table.")
   // private List<VimConnectionInfo> vimConnectionInfo;

}
