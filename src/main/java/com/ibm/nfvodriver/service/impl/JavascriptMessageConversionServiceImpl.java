package com.ibm.nfvodriver.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.etsi.ifa013.NsDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.nfvodriver.model.alm.ExecutionRequest;
import com.ibm.nfvodriver.model.alm.GenericExecutionRequestPropertyValue;
import com.ibm.nfvodriver.service.MessageConversionException;
import com.ibm.nfvodriver.service.MessageConversionService;
import com.ibm.nfvodriver.utils.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("JavascriptMessageConversionServiceImpl")
public class JavascriptMessageConversionServiceImpl implements MessageConversionService {

    private static final Logger logger = LoggerFactory.getLogger(JavascriptMessageConversionServiceImpl.class);
    private static final String DEFAULT_ETSI_SOL005_VERSION = "3.3.1";
    private static final String SCRIPTS_PATH = "scripts/";

    private final ObjectMapper objectMapper;

    @Autowired
    public JavascriptMessageConversionServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override public String generateMessageFromRequest(final String messageType, final ExecutionRequest executionRequest) throws MessageConversionException {
        final String script = getScriptFromExecutionRequest(executionRequest, messageType);
        final String nsdString = FileUtils.getFileFromLifecycleScripts(executionRequest.getDriverFiles(), "nsd.yaml");
        final ScriptEngine scriptEngine = getScriptEngine();

        try {
            // Create a new bindings object and attach objects to be used by the scripts
            final Bindings bindings = scriptEngine.createBindings();
            bindings.put("executionRequest", executionRequest);
            bindings.put("logger", logger);

            if (nsdString != null) {
                final NsDescriptor nsd = objectMapper.readValue(nsdString, NsDescriptor.class);
                bindings.put("nsd", nsd);
            }

            final Object returnVal = scriptEngine.eval(script, bindings);
            logger.info("Message conversion script successfully run, returnVal is\n{}", returnVal);
            if (returnVal instanceof String) {
                return (String) returnVal;
            } else if (returnVal == null) {
                throw new MessageConversionException("Script did not return a value, expected a String");
            } else {
                throw new MessageConversionException(String.format("Script returned invalid object of type [%s], expected a String", returnVal.getClass().getSimpleName()));
            }
        } catch (ScriptException | IOException e) {
            throw new MessageConversionException("Exception caught executing a script", e);
        }
    }

    @Override public Map<String, Object> extractPropertiesFromMessage(String messageType, ExecutionRequest executionRequest, String message) throws MessageConversionException {
        final String script = getScriptFromExecutionRequest(executionRequest, messageType);
        final ScriptEngine scriptEngine = getScriptEngine();

        try {
            // Create a new bindings object and attach objects to be used by the scripts
            final Bindings bindings = scriptEngine.createBindings();
            bindings.put("message", message);
            bindings.put("logger", logger);
            final Map<String, Object> outputs = new HashMap<>();
            bindings.put("outputs", outputs);

            scriptEngine.eval(script, bindings);
            logger.info("Message conversion script successfully run, outputs are\n{}", outputs);
            return outputs;
        } catch (ScriptException e) {
            throw new MessageConversionException("Exception caught executing a script", e);
        }
    }

    private ScriptEngine getScriptEngine() {
        // Retrieve a Javascript engine (should be Nashorn in JRE 8+)
        final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByMimeType("application/javascript");
        logger.debug("Retrieved an instance of a [{}] script engine", scriptEngine);
        return scriptEngine;
    }

    private String getScriptFromExecutionRequest(final ExecutionRequest executionRequest, final String scriptName) {
        final String fullScriptName = scriptName + ".js";

        String scriptContents = FileUtils.getFileFromLifecycleScripts(executionRequest.getDriverFiles(), SCRIPTS_PATH + fullScriptName);

        if (scriptContents == null) {
            // If we can't find it in the zip file, try searching in out default locations
            String interfaceVersion =  ((GenericExecutionRequestPropertyValue)executionRequest.getResourceProperties().getOrDefault("interfaceVersion", new GenericExecutionRequestPropertyValue(DEFAULT_ETSI_SOL005_VERSION))).getValue().toString();
            try (InputStream inputStream = JavascriptMessageConversionServiceImpl.class.getResourceAsStream("/" + SCRIPTS_PATH + interfaceVersion + "/" + fullScriptName)) {
                if (inputStream != null) {
                    scriptContents = IOUtils.toString(inputStream, Charset.defaultCharset());
                }
            } catch (IOException e) {
                logger.error("Exception raised looking up default lifecycle script", e);
            }
        }

        if (scriptContents != null) {
            return scriptContents;
        } else {
            throw new IllegalArgumentException(String.format("Unable to find a script called [%s]", fullScriptName));
        }
    }

}
