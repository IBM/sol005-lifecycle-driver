package com.ibm.common.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;

public class LoggingUtils {

    private static final String LOGGING_CONTEXT_KEY_PREFIX = "traceCtx.".toLowerCase();
    private static final String LOGGING_CONTEXT_HEADER_PREFIX = "X-TraceCtx-".toLowerCase();
    private static final String TRANSACTION_ID_HEADER_KEY = "TransactionId".toLowerCase();

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

}
