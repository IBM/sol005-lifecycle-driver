package com.ibm.nfvodriver.service;

public class GrantRejectedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String reason;

    public GrantRejectedException(String reason) {
        super();
        this.reason = reason;
    }

    public GrantRejectedException(String reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String getMessage() {
        return reason;
    }

}
