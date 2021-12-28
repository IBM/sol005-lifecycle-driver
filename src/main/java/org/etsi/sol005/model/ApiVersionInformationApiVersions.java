package org.etsi.sol005.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * ApiVersionInformationApiVersions
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ApiVersionInformationApiVersions   {

  private String version;

  private Boolean isDeprecated;

  private OffsetDateTime retirementDate = null;
}

