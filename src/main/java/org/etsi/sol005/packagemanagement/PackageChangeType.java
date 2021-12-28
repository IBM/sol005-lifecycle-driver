package org.etsi.sol005.packagemanagement;

/**
 * Represents the type of change of the VNF package
 */
public enum PackageChangeType {
    /**
     * The "operationalState" attribute has been changed.
     */
    OP_STATE_CHANGE,
    /**
     * The "deletionPending" attribute has been changed.
     */
    DELETE_PEND_CHANGE,
    /**
     * The VNF package has been deleted.
     */
    PKG_DELETE;
}
