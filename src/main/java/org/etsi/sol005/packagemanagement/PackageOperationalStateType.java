package org.etsi.sol005.packagemanagement;

/**
 * Operational state of the on-boarded instance of the VNF package.
 */
public enum PackageOperationalStateType {
    /**
     * The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances.
     */
    ENABLED,
    /**
     * The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled).
     */
    DISABLED;

}
