package com.ibm.nfvodriver.driver;

public class GrantProviderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GrantProviderException(String message) {
        super(message);
    }

    public GrantProviderException(String message, Throwable t) {
        super(message, t);
    }

}
