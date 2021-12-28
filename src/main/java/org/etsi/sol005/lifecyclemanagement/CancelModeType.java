package org.etsi.sol005.lifecyclemanagement;

public enum CancelModeType {

    /**
     * The VNFM shall not start any new resource management operation and shall wait
     * for the ongoing resource management operations in the underlying system, typically
     * the VIM, to finish execution or to time out. After that, the VNFM shall put the
     * operation occurrence into the FAILED_TEMP state.
     */
    GRACEFUL,

    /**
     * The VNFM shall not start any new resource management operation, shall cancel the
     * ongoing resource management operations in the underlying system, typically the
     * VIM, and shall wait for the cancellation to finish or to time out. After that, the VNFM
     * shall put the operation occurrence into the FAILED_TEMP state.
     */
    FORCEFUL

}
