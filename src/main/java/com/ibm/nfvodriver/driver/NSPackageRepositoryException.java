package com.ibm.nfvodriver.driver;

public class NSPackageRepositoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NSPackageRepositoryException(String message) {
        super(message);
    }

    public NSPackageRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
