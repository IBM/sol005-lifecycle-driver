package org.etsi.sol005.lifecyclemanagement;

import java.util.List;
import java.util.Map;

import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents attribute modifications for an "Individual VNF instance" resource, i.e. modifications to a resource representation based on the "VnfInstance" data type.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents attribute modifications for an \"Individual VNF instance\" resource, i.e. modifications to a resource representation based on the \"VnfInstance\" data type.")
public class VnfInfoModificationRequest {

    @ApiModelProperty(name = "VNF Instance Name", notes = "New value of the \"vnfInstanceName\" attribute in \"VnfInstance\", or \"null\" to remove the attribute.")
    private String vnfInstanceName;
    @ApiModelProperty(name = "VNF Instance Description", notes = "New value of the \"vnfInstanceDescription\" attribute in \"VnfInstance\", or \"null\" to remove the attribute.")
    private String vnfInstanceDescription;
    @ApiModelProperty(name = "Onboarded VNF Package Information Id", notes = "New value of the \"vnfPkgId\" attribute in \"VnfInstance\". The value \"null\" is not permitted.")
    private String vnfPkgId;
    @ApiModelProperty(name = "VNF Configurable Properties", notes = "Modifications of the \"vnfConfigurableProperties\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396)")
    private Map<String, String> vnfConfigurableProperties;
    @ApiModelProperty(name = "Metadata", notes = "Modifications of the \"metadata\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396)")
    private Map<String, String> metadata;
    @ApiModelProperty(name = "Extensions", notes = "Modifications of the \"extensions\" attribute in \"VnfInstance\". If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396).")
    private Map<String, String> extensions;
    @ApiModelProperty(name = "VIM Connection Information", notes = "New content of certain entries in the \"vimConnectionInfo\" attribute array in \"VnfInstance\", as defined below this table.")
    private List<VimConnectionInfo> vimConnectionInfo;

}
