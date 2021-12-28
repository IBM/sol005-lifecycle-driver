package org.etsi.ifa013;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NsDescriptor {

    private String nsddId;
    private String nsProvider;
    private String nsProductName;
    private String nsSoftwareVersion;
    private String nsdVersion;
    private String nsProductInfoName;
    private String nsProductInfoDescription;
    private List<String> nfvoInfo;
    private List<String> localizationLanguage;
    private String defaultLocalizationLanguage;

    // This is a deficient model, we have only implemented the sections that are currently used.
    private List<NsDf> deploymentFlavour;

}
