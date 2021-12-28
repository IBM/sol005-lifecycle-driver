package org.etsi.sol005.lifecyclemanagement;

public enum LcmOperationStateType {

    /**
     * The LCM operation is starting.
     */
    STARTING,

    /**
     * The LCM operation is currently in execution.
     */
    PROCESSING,

    /**
     * The LCM operation has been completed successfully.
     */
    COMPLETED,

    /**
     * The LCM operation has failed and execution has stopped, but the execution of the
     * operation is not considered to be closed.
     */
    FAILED_TEMP,

    /**
     * The LCM operation has failed and it cannot be retried or rolled back, as it is
     * determined that such action won't succeed.
     */
    FAILED,

    /**
     * The LCM operation is currently being rolled back.
     */
    ROLLING_BACK,

    /**
     * The LCM operation has been successfully rolled back, i.e. The state of the VNF
     * prior to the original operation invocation has been restored as closely as possible.
     */
    ROLLED_BACK

}
