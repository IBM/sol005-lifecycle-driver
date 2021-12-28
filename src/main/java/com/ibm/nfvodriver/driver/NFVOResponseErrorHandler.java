package com.ibm.nfvodriver.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("NFVOResponseErrorHandler")
public class NFVOResponseErrorHandler extends SOL005ResponseErrorHandler {

    @Autowired
    public NFVOResponseErrorHandler(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected String endpointDescription() {
        return "NFVO";
    }

}
