package com.ibm.nfvodriver.service;

import java.util.Map;

import com.ibm.nfvodriver.model.alm.ExecutionRequest;

public interface MessageConversionService {

    String generateMessageFromRequest(String messageType, ExecutionRequest executionRequest) throws MessageConversionException;
    Map<String, Object> extractPropertiesFromMessage(String messageType, ExecutionRequest executionRequest, String message) throws MessageConversionException;

}
