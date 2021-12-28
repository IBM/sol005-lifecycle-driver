package org.etsi.sol005.lifecyclemanagement;

/**
 * Indicates the type of the scale operation requested.
 */
public enum ScaleType {
    /**
     * adding additional VNFC instances to the VNF to increase capacity.
     */
    SCALE_OUT,

    /**
     * removing VNFC instances from the VNF in order to release unused capacity.
     */
    SCALE_IN
}
