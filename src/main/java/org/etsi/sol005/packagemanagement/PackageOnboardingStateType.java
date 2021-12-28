package org.etsi.sol005.packagemanagement;

public enum PackageOnboardingStateType {

    /**
     * The VNF package resource has been created.
     */
    CREATED,
    /**
     * The associated VNF package content is being uploaded.
     */
    UPLOADING,
    /**
     * The associated VNF package content is being processed, e.g. validation.
     */
    PROCESSING,
    /**
     * The associated VNF package content has been successfully on-boarded.
     */
    ONBOARDED

}
