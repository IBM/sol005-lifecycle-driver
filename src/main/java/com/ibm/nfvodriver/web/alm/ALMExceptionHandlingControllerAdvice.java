package com.ibm.nfvodriver.web.alm;

import java.util.HashMap;
// import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;

import com.ibm.nfvodriver.driver.SOL005ResponseException;
import com.ibm.nfvodriver.model.web.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Handles the conversion of Exceptions thrown by ALM-facing Rest API calls
 */
@RestControllerAdvice("com.ibm.nfvodriver.web.alm")
public class ALMExceptionHandlingControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ALMExceptionHandlingControllerAdvice.class);

    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorInfo handleHttpMessageConversionException(HttpServletRequest req, HttpMessageConversionException cause) {
        return defaultHandle("Unable to parse request", req, cause);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorInfo handleHttpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException cause) {
        return defaultHandle("Invalid content provided to request", req, cause);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorInfo handleMissingServletRequestParameterException(HttpServletRequest req, MissingServletRequestParameterException cause) {
        return defaultHandle(cause.getLocalizedMessage(), req, cause);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    protected ErrorInfo handleHttpMediaTypeNotSupportedException(HttpServletRequest req, HttpMediaTypeNotSupportedException cause) {
        return defaultHandle("Invalid content provided to request", req, cause);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    protected ErrorInfo handleHttpMediaTypeNotAcceptableException(HttpServletRequest req, HttpMediaTypeNotAcceptableException cause) {
        return defaultHandle("Invalid content provided to request", req, cause);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    protected ErrorInfo handleHttpRequestMethodNotSupportedException(HttpServletRequest req, HttpRequestMethodNotSupportedException cause) {
        return defaultHandle("Invalid method used in request", req, cause);
    }

    @ExceptionHandler(NotImplementedException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    protected ErrorInfo handleNotImplementedException(HttpServletRequest req, NotImplementedException cause) {
        return defaultHandle("Method not yet implemented", req, cause);
    }

    @ExceptionHandler(SOL005ResponseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorInfo handleSOL003ResponseException(HttpServletRequest req, SOL005ResponseException cause) {
        ErrorInfo errorInfo = defaultHandle(cause.getLocalizedMessage(), req, cause);
        if (cause.getProblemDetails().getDetail() != null) {
            errorInfo.getDetails().put("nfvoDetail", cause.getProblemDetails().getDetail());
        }
        if (cause.getProblemDetails().getStatus() != null) {
            errorInfo.getDetails().put("nfvoStatus", cause.getProblemDetails().getStatus());
        }
        if (cause.getProblemDetails().getInstance() != null) {
            errorInfo.getDetails().put("nfvoInstance", cause.getProblemDetails().getInstance());
        }
        if (cause.getProblemDetails().getTitle() != null) {
            errorInfo.getDetails().put("nfvoTitle", cause.getProblemDetails().getTitle());
        }
        if (cause.getProblemDetails().getType() != null) {
            errorInfo.getDetails().put("nfvoType", cause.getProblemDetails().getType());
        }
        return errorInfo;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorInfo handleAllExceptions(HttpServletRequest req, Exception cause) {
        return defaultHandle(cause.getLocalizedMessage(), req, cause);
    }

    private ErrorInfo defaultHandle(String message, HttpServletRequest req, Exception cause) {
        logError(message, cause);
        return buildBasicErrorInfoObject(req, cause);
    }

    private ErrorInfo buildBasicErrorInfoObject(final HttpServletRequest req, final Exception cause) {
        Throwable rootCause = cause;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        final HashMap<String, Object> details = new HashMap<>();
        if (rootCause != cause) {
            details.put("rootCause", rootCause.getLocalizedMessage());
        }
        return new ErrorInfo(req.getRequestURL().toString(), cause.getLocalizedMessage(), details);
    }

    private void logError(String message, Throwable cause) {
        if (logger.isDebugEnabled()) {
            logger.warn(message, cause);
        } else {
            logger.warn(String.format("%s: %s", message, cause.getMessage()));
        }
    }

}