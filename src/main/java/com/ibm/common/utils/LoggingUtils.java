package com.ibm.common.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import jakarta.servlet.http.HttpServletRequest;

import com.ibm.nfvodriver.driver.SOL005ResponseException;
import com.ibm.nfvodriver.model.MessageDirection;
import com.ibm.nfvodriver.model.MessageType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;

public class LoggingUtils {

    private final static Logger logger = LoggerFactory.getLogger(LoggingUtils.class);

    private static final String LOGGING_CONTEXT_KEY_PREFIX = "traceCtx.".toLowerCase();
    private static final String LOGGING_CONTEXT_HEADER_PREFIX = "X-TraceCtx-".toLowerCase();
    private static final String TRANSACTION_ID_HEADER_KEY = "TransactionId".toLowerCase();

    private static final String LOG_MESSAGE_DIRECTION_KEY = "message_direction";

    private static final String LOG_EXTERNAL_REQUEST_ID_KEY = "tracectx.externalrequestid";

    private static final String LOG_CONTENT_TYPE_KEY = "content_type";

    private static final String LOG_PROTOCOL_KEY = "protocol";

    private static final String LOG_PROTOCOL_METADATA_KEY = "protocol_metadata";

    private static final String LOG_MSG_TYP_KEY= "message_type";

    private static final String LOG_DRIVER_REQUEST_ID ="tracectx.driverrequestid";

    public static Map<String, String> getContextMapFromHttpHeaders(HttpServletRequest servletRequest) {
        final Map<String, String> loggingContext = new HashMap<>();
        final Enumeration<String> headerNames = servletRequest.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (headerName.toLowerCase().startsWith(LOGGING_CONTEXT_HEADER_PREFIX)) {
                loggingContext.put(LOGGING_CONTEXT_KEY_PREFIX + headerName.substring(LOGGING_CONTEXT_HEADER_PREFIX.length()).toLowerCase(), servletRequest.getHeader(headerName));
            }
        }

        // Generate a random transaction Id, if one is not found
        if (!loggingContext.containsKey(LOGGING_CONTEXT_KEY_PREFIX + TRANSACTION_ID_HEADER_KEY)) {
            final String mdcValue = MDC.get(LOGGING_CONTEXT_KEY_PREFIX + TRANSACTION_ID_HEADER_KEY);
            if (mdcValue != null) {
                loggingContext.put(LOGGING_CONTEXT_KEY_PREFIX + TRANSACTION_ID_HEADER_KEY, mdcValue);
            } else {
                loggingContext.put(LOGGING_CONTEXT_KEY_PREFIX + TRANSACTION_ID_HEADER_KEY, UUID.randomUUID().toString());
            }
        }

        return loggingContext;
    }
    public static void logEnabledMDC(String message, MessageType messageType, MessageDirection messageDirection, String externalRequestId, String contentType, String protocol, Map<String,Object> protocolMetadata,String driverRequestId){
        try{
            MDC.put(LOG_MESSAGE_DIRECTION_KEY, messageDirection.toString());
            MDC.put(LOG_EXTERNAL_REQUEST_ID_KEY, externalRequestId);
            MDC.put(LOG_CONTENT_TYPE_KEY, contentType);
            MDC.put(LOG_PROTOCOL_KEY, protocol.toLowerCase());
            ObjectMapper jsonMapper = new ObjectMapper();
            try {
                MDC.put(LOG_PROTOCOL_METADATA_KEY, jsonMapper.writeValueAsString(protocolMetadata));
            } catch (JsonProcessingException e) {
                throw  new SOL005ResponseException("Error in parsing protocol_metadata "+ protocolMetadata, e);
            }
            MDC.put(LOG_MSG_TYP_KEY,messageType.toString());
            MDC.put(LOG_DRIVER_REQUEST_ID,driverRequestId);

            logger.info(message);
        }finally{
            MDC.remove(LOG_MESSAGE_DIRECTION_KEY);
            MDC.remove(LOG_EXTERNAL_REQUEST_ID_KEY);
            MDC.remove(LOG_CONTENT_TYPE_KEY);
            MDC.remove(LOG_PROTOCOL_KEY);
            MDC.remove(LOG_PROTOCOL_METADATA_KEY);
            MDC.remove(LOG_MSG_TYP_KEY);
            MDC.remove(LOG_DRIVER_REQUEST_ID);
        }
    }

    public static void setHttpHeadersFromMDC(final HttpHeaders httpHeaders) {
        Map<String, String> mdcContext = MDC.getCopyOfContextMap();
        if (mdcContext == null) {
            // Can happen if there is no context set (mostly in unit tests)
            return;
        }
        mdcContext.keySet()
                .stream()
                .filter(k -> k.startsWith(LOGGING_CONTEXT_KEY_PREFIX))
                .forEach(k -> httpHeaders.set(LOGGING_CONTEXT_HEADER_PREFIX + k.substring(LOGGING_CONTEXT_KEY_PREFIX.length()), mdcContext.get(k)));
    }

    public static void populateMDCFromContextMap(Map<String, String> context) {
        context.keySet()
                .stream()
                .filter(k -> k.startsWith(LOGGING_CONTEXT_KEY_PREFIX))
                .forEach(k -> MDC.put(k, context.get(k)));
    }


    public static String getReasonPhrase(int statusCode) {
        switch(statusCode) {
            case 100: return "Continue";
            case 101: return "Switching Protocols";
            case 102: return "Processing";
            case 103: return "Early Hints";
            case 200: return "OK";
            case 201: return "Created";
            case 202: return "Accepted";
            case 203: return "Non-Authoritative Information";
            case 204: return "No Content";
            case 205: return "Reset Content";
            case 206: return "Partial Content";
            case 207: return "Multi-Status";
            case 208: return "Already Reported";
            case 226: return "IM Used";
            case 300: return "Multiple Choices";
            case 301: return "Moved Permanently";
            case 302: return "Found";
            case 303: return "See Other";
            case 304: return "Not Modified";
            case 305: return "Use Proxy";
            case 306: return "Switch Proxy";  // Deprecated but added for completeness
            case 307: return "Temporary Redirect";
            case 308: return "Permanent Redirect";
            case 400: return "Bad Request";
            case 401: return "Unauthorized";
            case 402: return "Payment Required";
            case 403: return "Forbidden";
            case 404: return "Not Found";
            case 405: return "Method Not Allowed";
            case 406: return "Not Acceptable";
            case 407: return "Proxy Authentication Required";
            case 408: return "Request Timeout";
            case 409: return "Conflict";
            case 410: return "Gone";
            case 411: return "Length Required";
            case 412: return "Precondition Failed";
            case 413: return "Payload Too Large";
            case 414: return "URI Too Long";
            case 415: return "Unsupported Media Type";
            case 416: return "Range Not Satisfiable";
            case 417: return "Expectation Failed";
            case 421: return "Misdirected Request";
            case 422: return "Unprocessable Entity";
            case 423: return "Locked";
            case 424: return "Failed Dependency";
            case 425: return "Too Early";
            case 426: return "Upgrade Required";
            case 428: return "Precondition Required";
            case 429: return "Too Many Requests";
            case 431: return "Request Header Fields Too Large";
            case 451: return "Unavailable For Legal Reasons";
            case 500: return "Internal Server Error";
            case 501: return "Not Implemented";
            case 502: return "Bad Gateway";
            case 503: return "Service Unavailable";
            case 504: return "Gateway Timeout";
            case 505: return "HTTP Version Not Supported";
            case 506: return "Variant Also Negotiates";
            case 507: return "Insufficient Storage";
            case 508: return "Loop Detected";
            case 510: return "Not Extended";
            case 511: return "Network Authentication Required";
            default: return "Unknown Status";
    }
    }
}
