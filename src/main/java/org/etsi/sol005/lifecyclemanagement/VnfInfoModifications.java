package org.etsi.sol005.lifecyclemanagement;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import org.etsi.sol005.common.VimConnectionInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



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
@Schema(description = "Represents attribute modifications that were performed on an \"Individual VNF instance\" resource. The attributes that can be included consist of those requested to be modified explicitly in the \"VnfInfoModificationRequest\" data structure, and additional attributes of the \"VnfInstance\" data structure that were modified implicitly e.g. when modifying the referenced VNF package.")
public class VnfInfoModifications {

    @Schema(name = "VNF Instance Name", description = "If present, this attribute signals modifications of the \"vnfInstanceName\" attribute in \"VnfInstance\".")
    private String vnfInstanceName;
    @Schema(name = "VNF Instance Description", description = "If present, this attribute signals modifications of the \"vnfInstanceDescription\" attribute in \"VnfInstance\".")
    private String vnfInstanceDescription;
    @Schema(name = "VNF Configurable Properties", description = "If present, this attribute signals modifications of the \"vnfConfigurableProperties\" attribute in \"VnfInstance\".")
    private Map<String, String> vnfConfigurableProperties;
    @Schema(name = "Metadata", description = "If present, this attribute signals modifications of the \"metadata\" attribute in \"VnfInstance\".")
    private Map<String, String> metadata;
    @Schema(name = "Extensions", description = "If present, this attribute signals modifications of the \"extensions\" attribute in \"VnfInstance\".")
    private Map<String, String> extensions;
    @Schema(name = "VIM Connection Information", description = "If present, this attribute signals modifications of certain entries in the \"vimConnectionInfo\" attribute array in \"VnfInstance\".")
    private VimConnectionInfo vimConnectionInfo;
    @Schema(name = "VNF Package Id", description = "If present, this attribute signals modifications of the \"vnfPkgId\" attribute in \"VnfInstance\".")
    private String vnfPkgId;
    @Schema(name = "VNF Descriptor Id", description = "If present, this attribute signals modifications of the \"vnfdId\" attribute in \"VnfInstance\".")
    private String vnfdId;
    @Schema(name = "VNF Provider", description = "If present, this attribute signals modifications of the \"vnfProvider\" attribute in \"VnfInstance\".")
    private String vnfProvider;
    @Schema(name = "VNF Product Name", description = "If present, this attribute signals modifications of the \"vnfProductName\" attribute in \"VnfInstance\".")
    private String vnfProductName;
    @Schema(name = "VNF Software Version", description = "If present, this attribute signals modifications of the \"vnfSoftwareVersion\" attribute in \"VnfInstance\".")
    private String vnfSoftwareVersion;
    @Schema(name = "VNF Descriptor Version", description = "If present, this attribute signals modifications of the \"vnfdVersion\" attribute in \"VnfInstance\".")
    private String vnfdVersion;

}
