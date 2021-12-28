package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Represents attribute modifications that were performed on an "Individual VNF
 * instance" resource. The attributes that can be included consist of those
 * requested to be modified explicitly in the "VnfInfoModificationRequest" data
 * structure, and additional attributes of the "VnfInstance" data structure
 * that were modified implicitly e.g. when modifying the referenced VNF
 * package.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents attribute modifications that were performed on an \"Individual VNF instance\" resource. The attributes that can be included consist of those requested to be modified explicitly in the \"VnfInfoModificationRequest\" data structure, and additional attributes of the \"VnfInstance\" data structure that were modified implicitly e.g. when modifying the referenced VNF package.")
public class VnfInfoModifications {

    @ApiModelProperty(name = "VNF Instance Name", notes = "If present, this attribute signals modifications of the \"vnfInstanceName\" attribute in \"VnfInstance\".")
    private String vnfInstanceName;
    @ApiModelProperty(name = "VNF Instance Description", notes = "If present, this attribute signals modifications of the \"vnfInstanceDescription\" attribute in \"VnfInstance\".")
    private String vnfInstanceDescription;
    @ApiModelProperty(name = "VNF Configurable Properties", notes = "If present, this attribute signals modifications of the \"vnfConfigurableProperties\" attribute in \"VnfInstance\".")
    private Map<String, String> vnfConfigurableProperties;
    @ApiModelProperty(name = "Metadata", notes = "If present, this attribute signals modifications of the \"metadata\" attribute in \"VnfInstance\".")
    private Map<String, String> metadata;
    @ApiModelProperty(name = "Extensions", notes = "If present, this attribute signals modifications of the \"extensions\" attribute in \"VnfInstance\".")
    private Map<String, String> extensions;
    @ApiModelProperty(name = "VIM Connection Information", notes = "If present, this attribute signals modifications of certain entries in the \"vimConnectionInfo\" attribute array in \"VnfInstance\".")
    private VimConnectionInfo vimConnectionInfo;
    @ApiModelProperty(name = "VNF Package Id", notes = "If present, this attribute signals modifications of the \"vnfPkgId\" attribute in \"VnfInstance\".")
    private String vnfPkgId;
    @ApiModelProperty(name = "VNF Descriptor Id", notes = "If present, this attribute signals modifications of the \"vnfdId\" attribute in \"VnfInstance\".")
    private String vnfdId;
    @ApiModelProperty(name = "VNF Provider", notes = "If present, this attribute signals modifications of the \"vnfProvider\" attribute in \"VnfInstance\".")
    private String vnfProvider;
    @ApiModelProperty(name = "VNF Product Name", notes = "If present, this attribute signals modifications of the \"vnfProductName\" attribute in \"VnfInstance\".")
    private String vnfProductName;
    @ApiModelProperty(name = "VNF Software Version", notes = "If present, this attribute signals modifications of the \"vnfSoftwareVersion\" attribute in \"VnfInstance\".")
    private String vnfSoftwareVersion;
    @ApiModelProperty(name = "VNF Descriptor Version", notes = "If present, this attribute signals modifications of the \"vnfdVersion\" attribute in \"VnfInstance\".")
    private String vnfdVersion;

}
