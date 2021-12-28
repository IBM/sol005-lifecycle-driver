package org.etsi.ifa013;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NsDf {

    private String flavourId;
    private String description;

    // This is a deficient model, only the parts currently used have been implemented.
    private NsLcmOperationsConfiguration NsLcmOperationsConfiguration;

}
