package org.etsi.sol005.granting;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * This type provides information regarding a resource placement constraint. A set of such constraints may be sent by the VNFM to the NFVO to influence the resource placement decisions made by the
 * NFVO as part of the granting process. A placement constraint defines a condition to the placement of new resources, considering other new resources as well as existing resources.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Represents a resource placement constraint.")
public class PlacementConstraint {

    @ApiModelProperty(name = "Type", required = true, notes = "The type of the constraint.")
    private AffinityOrAntiAffinity affinityOrAntiAffinity;
    @ApiModelProperty(name = "Scope", required = true, notes = "The scope of the placement constraint indicating the category of the \"place\" where the constraint applies.")
    private Scope scope;
    @ApiModelProperty(name = "Resource Reference", required = true, notes = "References to resources in the constraint rule.")
    @JsonProperty("resource")
    private List<ConstraintResourceRef> resources;

    /**
     * Represents Affinity or Anti-affinity
     */
    public static enum AffinityOrAntiAffinity {
        AFFINITY, ANTI_AFFINITY;
    }

    /**
     * Represents the Scope of the Placement Constraint
     */
    public static enum Scope {
        NFVI_POP, ZONE, ZONE_GROUP, NFVI_NODE
    }
}
