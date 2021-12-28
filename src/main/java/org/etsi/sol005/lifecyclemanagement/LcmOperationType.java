package org.etsi.sol005.lifecyclemanagement;

/**
 * Represents those lifecycle operations that trigger a VNF lifecycle management operation occurrence notification
 */
public enum LcmOperationType {

    /**
     * Represents the "Instantiate VNF" LCM operation.
     */
    INSTANTIATE,

    /**
     * Represents the "Scale VNF" LCM operation.
     */
    SCALE,

    /**
     * Represents the "Scale VNF to Level" LCM operation.
     */
    SCALE_TO_LEVEL,

    /**
     * Represents the "Change VNF Flavour" LCM operation.
     */
    CHANGE_FLAVOUR,

    /**
     * Represents the "Terminate VNF" LCM operation.
     */
    TERMINATE,

    /**
     * Represents the "Heal VNF" LCM operation.
     */
    HEAL,

    /**
     * Represents the "Operate VNF" LCM operation.
     */
    OPERATE,

    /**
     * Represents the "Change external VNF connectivity" LCM operation.
     */
    CHANGE_EXT_CONN,

    /**
     * Represents the "Modify VNF Information" LCM operation.
     */
    MODIFY_INFO

}
