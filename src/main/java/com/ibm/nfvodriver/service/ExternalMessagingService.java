package com.ibm.nfvodriver.service;

import java.time.Duration;

import com.ibm.nfvodriver.model.LcmOpOccPollingRequest;
import com.ibm.nfvodriver.model.alm.ExecutionAsyncResponse;

public interface ExternalMessagingService {

    void sendExecutionAsyncResponse(ExecutionAsyncResponse request);

    void sendDelayedExecutionAsyncResponse(ExecutionAsyncResponse request, Duration delay);

    void sendLcmOpOccPollingRequest(LcmOpOccPollingRequest request);

}
