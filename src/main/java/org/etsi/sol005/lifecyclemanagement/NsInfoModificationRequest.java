package org.etsi.sol005.lifecyclemanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

//import org.etsi.sol005.common.VimConnectionInfo;

/**
 * Represents attribute modifications for an "Individual NS instance" resource, i.e. modifications to a resource representation based on the "NsInstance" data type.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents attribute modifications for an \"Individual  instance\" resource, i.e. modifications to a resource representation based on the \"NsInstance\" data type.")
public class NsInfoModificationRequest {

    @ApiModelProperty(name = "NS Instance Name", notes = "New value of the \"nsInstanceName\" attribute in \"NsInstance\", or \"null\" to remove the attribute.")
    private String nsInstanceName;
    @ApiModelProperty(name = "NS Instance Description", notes = "New value of the \"nsInstanceDescription\" attribute in \"NsInstance\", or \"null\" to remove the attribute.")
    private String nsInstanceDescription;
    @ApiModelProperty(name = "Onboarded NS Package Information Id", notes = "New value of the \"nsPkgId\" attribute in \"NsInstance\". The value \"null\" is not permitted.")
    private String nsPkgId;
    @ApiModelProperty(name = "NS Configurable Properties", notes = "Modifications of the \"nsConfigurableProperties\" attribute in \"NsInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396)")
    private Map<String, String> nsConfigurableProperties;
    @ApiModelProperty(name = "Metadata", notes = "Modifications of the \"metadata\" attribute in \"NsInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396)")
    private Map<String, String> metadata;
    @ApiModelProperty(name = "Extensions", notes = "Modifications of the \"extensions\" attribute in \"NsInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396).")
    private Map<String, String> extensions;
//    @ApiModelProperty(name = "VIM Connection Information", notes = "New content of certain entries in the \"vimConnectionInfo\" attribute array in \"NsInstance\", as defined below this table.")
   // private List<VimConnectionInfo> vimConnectionInfo;

}
