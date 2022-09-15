package com.ibm.nfvodriver.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.input.BoundedInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component("loggingRequestInterceptor")
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private NFVODriverProperties nfvoDriverProperties;

    @Autowired
    public LoggingRequestInterceptor(NFVODriverProperties nfvoDriverProperties) {
        super();
        this.nfvoDriverProperties = nfvoDriverProperties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (logger.isInfoEnabled()) {
            traceRequest(request, body);
        }
        ClientHttpResponse response = execution.execute(request, body);
        if (logger.isInfoEnabled()) {
            traceResponse(response);
        }
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        StringBuilder requestDetails = new StringBuilder();
        requestDetails.append("Performing a " + request.getMethod() + " request to " + request.getURI() + LINE_SEPARATOR);
        requestDetails.append("Headers     : " + request.getHeaders() + LINE_SEPARATOR);
        requestDetails.append("Request body:" + LINE_SEPARATOR + new String(body, StandardCharsets.UTF_8));
        logger.info(requestDetails.toString());
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        inputStringBuilder.append("Received response " + response.getStatusCode() + "/" + response.getStatusText() + LINE_SEPARATOR);
        inputStringBuilder.append("Headers      : " + response.getHeaders() + LINE_SEPARATOR);
        inputStringBuilder.append("Response body:" + LINE_SEPARATOR);

        InputStream boundedInputStream = new BoundedInputStream(response.getBody(), nfvoDriverProperties.getLogging().getLoggingRequestInterceptMaxBodySize());
        // bufferedReader must not be closed as it is used elsewhere.
        @SuppressWarnings("resource")
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(boundedInputStream, StandardCharsets.UTF_8));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append(LINE_SEPARATOR);
            line = bufferedReader.readLine();
        }
        logger.info(inputStringBuilder.toString());
    }
}