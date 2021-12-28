package org.etsi.sol005.granting;

/**
 * References the type of the identifier.
 */
public enum ResourceIdentifierType {

    /**
     * Resource-management-level identifier; this identifier is managed by the VIM in the direct mode of VNF-related resource management, and is managed by the NFVO in the indirect mode)
     */
    RES_MGMT,

    /**
     * Reference to the identifier of a "ResourceDefinition" structure in the "GrantRequest" structure.
     */
    GRANT
}
