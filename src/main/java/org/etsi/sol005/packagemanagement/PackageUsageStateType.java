package org.etsi.sol005.packagemanagement;

/**
 * Represents usage state of the on-boarded instance of the VNF package.
 */
public enum PackageUsageStateType {
    /**
     * VNF instances instantiated from this VNF package exist.
     */
    IN_USE,
    /**
     * No existing VNF instance is instantiated from this VNF package.
     */
    NOT_IN_USE
}
