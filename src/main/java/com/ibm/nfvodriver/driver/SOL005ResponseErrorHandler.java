package com.ibm.nfvodriver.driver;

import java.io.IOException;

import org.etsi.sol005.common.ProblemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientResponseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class SOL005ResponseErrorHandler extends DefaultResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Autowired
    public SOL005ResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        try {
            super.handleError(clientHttpResponse);
        } catch (RestClientResponseException e) {
            // First, check that the response contains JSON
            if (e.getResponseHeaders() != null && e.getResponseHeaders().getContentType() != null && e.getResponseHeaders().getContentType().isCompatibleWith(MediaType.APPLICATION_JSON)) {
                // Retrieve the body of the response and check it's not empty
                String responseBody = e.getResponseBodyAsString();
                if (!StringUtils.isEmpty(responseBody)) {
                    // Attempt to parse a ProblemDetails record out of the body of the response
                    ProblemDetails problemDetails = objectMapper.readValue(responseBody, ProblemDetails.class);
                    // Check mandatory fields to see if this is indeed a valid ETSI SOL005-compliant error response
                    if (problemDetails.getStatus() != null && problemDetails.getDetail() != null) {
                        throw new SOL005ResponseException(String.format("Received SOL005-compliant error when communicating with %s: %s", endpointDescription(), problemDetails.getDetail()), e,
                                                          problemDetails);
                    }
                }
            }
            // Else, attempt to extract information out of the error response (as best as possible)
            final String responseBody = e.getResponseBodyAsString();
            String detailsMessage = e.getStatusText();
            if (!StringUtils.isEmpty(responseBody)) {
                detailsMessage += ": " + responseBody;
            }
            throw new SOL005ResponseException(String.format("Caught REST client exception when communicating with %s", endpointDescription()),
                                              new ProblemDetails(e.getRawStatusCode(), detailsMessage));
        } catch (Exception e) {
            throw new SOL005ResponseException(String.format("Caught general exception when communicating with %s", endpointDescription()), e);
        }
    }

    protected abstract String endpointDescription();

}
