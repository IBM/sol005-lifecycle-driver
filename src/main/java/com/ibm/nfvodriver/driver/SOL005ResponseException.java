package com.ibm.nfvodriver.driver;

import org.etsi.sol005.common.ProblemDetails;
import org.springframework.web.client.RestClientException;

public class SOL005ResponseException extends RestClientException {

    public static final int DEFAULT_STATUS_VALUE = 0;

    private final ProblemDetails problemDetails;

    public SOL005ResponseException(String msg) {
        this(msg, new ProblemDetails(DEFAULT_STATUS_VALUE, msg));
    }

    public SOL005ResponseException(Throwable ex) {
        this(ex.getLocalizedMessage(), ex, new ProblemDetails(DEFAULT_STATUS_VALUE, ex.getLocalizedMessage()));
    }

    public SOL005ResponseException(String msg, Throwable ex) {
        this(msg, ex, new ProblemDetails(DEFAULT_STATUS_VALUE, String.format("%s: %s", msg, ex.getLocalizedMessage())));
    }

    public SOL005ResponseException(String msg, ProblemDetails problemDetails) {
        super(msg);
        this.problemDetails = problemDetails;
    }

    public SOL005ResponseException(String msg, Throwable ex, ProblemDetails problemDetails) {
        super(msg, ex);
        this.problemDetails = problemDetails;
    }

    public ProblemDetails getProblemDetails() {
        return problemDetails;
    }

}
