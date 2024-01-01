package com.ibm.nfvodriver.web.alm;

import com.ibm.nfvodriver.model.alm.ExecutionAcceptedResponse;
import com.ibm.nfvodriver.model.alm.ExecutionRequest;
import com.ibm.nfvodriver.service.LifecycleManagementService;
import com.ibm.nfvodriver.service.MessageConversionException;
import io.swagger.v3.oas.annotations.Operation;
import org.etsi.sol005.model.FindReferenceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController("LifecycleController")
@RequestMapping("/api/driver")
public class LifecycleController {

    private final static Logger logger = LoggerFactory.getLogger(LifecycleController.class);

    private final LifecycleManagementService lifecycleManagementService;

    @Autowired
    public LifecycleController(final LifecycleManagementService lifecycleManagementService) {
        this.lifecycleManagementService = lifecycleManagementService;
    }

    @PostMapping("/lifecycle/execute")
    @Operation(summary = "Execute a lifecycle against a NFVO", description = "Initiates a lifecycle against a NS, managed by a NFVO")
    public ResponseEntity<ExecutionAcceptedResponse> executeLifecycle(@RequestBody ExecutionRequest executionRequest, HttpServletRequest servletRequest) throws MessageConversionException {
        try (BufferedReader messageReader = servletRequest.getReader()) {
            String rawMessage = messageReader.lines().collect(Collectors.joining("\n"));
            logger.info("Received ExecutionRequest:\n{}", rawMessage);
        } catch (IOException e) {
            logger.warn(String.format("Exception caught logging ExecutionRequest message: %s", e.getMessage()), e);
        }
        logger.info("Received request to execute a lifecycle [{}] at deployment location [{}]", executionRequest.getLifecycleName(), executionRequest.getDeploymentLocation().getName());
        final ExecutionAcceptedResponse executionAcceptedResponse = lifecycleManagementService.executeLifecycle(executionRequest);
        return ResponseEntity.accepted().body(executionAcceptedResponse);
    }


    @PostMapping("/references/find")
    @Operation(summary = "Execute a lifecycle against a NFVO", description = "Initiates a lifecycle against a NS, managed by a NFVO")
    public ResponseEntity<ExecutionAcceptedResponse> findReference(@RequestBody FindReferenceRequest findReferenceRequest, HttpServletRequest servletRequest) throws MessageConversionException, NotImplementedException {
        throw new NotImplementedException("Find References API is not implemented");
    }


}
