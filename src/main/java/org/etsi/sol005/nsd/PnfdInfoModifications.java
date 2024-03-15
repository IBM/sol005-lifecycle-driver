package org.etsi.sol005.nsd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.validation.Valid;
import java.util.Map;

/**
 * This type represents attribute modifications for an individual PNF descriptor resource based on the "PnfdInfo" data type.
 * The attributes of "PnfdInfo" that can be modified are included in the "PnfdInfoModifications" data type.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PnfdInfoModifications {

    @JsonProperty("userDefinedData")
    @Valid
    private Map<String, String> userDefinedData = null;
}
