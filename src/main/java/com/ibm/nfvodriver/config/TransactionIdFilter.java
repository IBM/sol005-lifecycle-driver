package com.ibm.nfvodriver.config;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ibm.common.utils.LoggingUtils;

@WebFilter
@Component("TransactionIdFilter")
public class TransactionIdFilter implements Filter {

    @Override public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        try {
            if (!StringUtils.isEmpty(request.getContentType()) && request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                // for a request of content type "multipart/form-data" getInputStream has already been called by previous filter so do not read it again and just continue the filter chain.
                chain.doFilter(request, response);
            } else {
                BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper((HttpServletRequest) request);
                Map<String, String> loggingContext = LoggingUtils.getContextMapFromHttpHeaders(bufferedRequest);
                LoggingUtils.populateMDCFromContextMap(loggingContext);
                HttpServletResponse bufferedResponse = (HttpServletResponse) response;
                chain.doFilter(bufferedRequest, bufferedResponse);
            }
        } finally {
            MDC.clear();
        }
    }

    @Override public void init(final FilterConfig filterConfig) {}

    @Override public void destroy() {}

}