package com.ibm.nfvodriver.service.impl;

import com.ibm.nfvodriver.model.alm.ExecutionRequest;
import com.ibm.nfvodriver.model.alm.GenericExecutionRequestPropertyValue;
import com.ibm.nfvodriver.model.alm.PropertyType;
import com.ibm.nfvodriver.service.MessageConversionService;
import com.ibm.nfvodriver.test.TestConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JsonContent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.ibm.nfvodriver.test.TestConstants.TEST_DL_NO_AUTH;
import static com.ibm.nfvodriver.test.TestConstants.loadFileIntoString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JavascriptMessageConversionServiceImplTest {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void setUpClass() {

        objectMapper.findAndRegisterModules();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

   @Test
    public void testGenerateMessageFromRequestUsingProvidedScript() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Configure");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);
        executionRequest.getResourceProperties().put("description", new GenericExecutionRequestPropertyValue("testing testing 123"));
        executionRequest.setDriverFiles(loadZipIntoBase64String("examples/lifecyclescripts.zip"));

        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);
        final String message = messageConversionService.generateMessageFromRequest("InstantiateNsRequest", executionRequest);

        assertThat(message).isEqualTo("{\"extVirtualLinks\":[],\"extManagedVirtualLinks\":[],\"vimConnectionInfo\":[],\"additionalParams\":{}}");
    }

    public static String loadZipIntoBase64String(final String fileName) throws IOException {
        try (
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                InputStream inputStream = TestConstants.class.getResourceAsStream(fileName.startsWith("/") ? fileName : "/" + fileName)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return Base64.getEncoder().encodeToString(result.toByteArray());
        }
    }

    @Test
    public void testGenerateMessageFromRequestUsingSpecifiedVersion() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Configure");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);
        executionRequest.getResourceProperties().put("description", new GenericExecutionRequestPropertyValue("testing testing 123"));
        executionRequest.getResourceProperties().put("interfaceVersion", new GenericExecutionRequestPropertyValue("3.3.1"));

        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);
        final String message = messageConversionService.generateMessageFromRequest("InstantiateNsRequest", executionRequest);

        assertThat(message).isEqualTo("{\"extVirtualLinks\":[],\"extManagedVirtualLinks\":[],\"vimConnectionInfo\":[],\"additionalParams\":{}}");
    }

    @Test
    public void testGenerateMessageFromRequestUsingDefault() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Install");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);
        executionRequest.getResourceProperties().put("nsdId", new GenericExecutionRequestPropertyValue("xyz-xyz-xyz-xyz"));
        executionRequest.getResourceProperties().put("nsName", new GenericExecutionRequestPropertyValue("Install"));
        executionRequest.getResourceProperties().put("nsDescription", new GenericExecutionRequestPropertyValue("testing testing 123"));

        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);
        final String message = messageConversionService.generateMessageFromRequest("CreateNsRequest", executionRequest);

        assertThat(message).isEqualTo("{\"additionalParams\":{},\"nsdId\":\"xyz-xyz-xyz-xyz\",\"nsName\":\"Install\",\"nsDescription\":\"testing testing 123\"}");
    }

    @Test
    public void testNoScriptFound() {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);
        assertThatThrownBy(() -> messageConversionService.generateMessageFromRequest("UnknownMessageType", executionRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unable to find a script called [UnknownMessageType.js]");
    }

    @Test
    public void testGenerateInstantiateMessage() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Configure");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);
        executionRequest.getResourceProperties().put("nsdId", new GenericExecutionRequestPropertyValue("fa2343af-2a81-4e84-a667-e40662e5ed93"));
        executionRequest.getResourceProperties().put("nsInstanceId", new GenericExecutionRequestPropertyValue("0000-0002-0000-0001"));
        executionRequest.getResourceProperties().put("nsInstanceName", new GenericExecutionRequestPropertyValue("HelloWorld1"));
        executionRequest.getResourceProperties().put("nsPkgId", new GenericExecutionRequestPropertyValue("316aa140-c99a-4a08-b8f5-8e2cb73c83e8"));
        executionRequest.getResourceProperties().put("nsProvider", new GenericExecutionRequestPropertyValue("ACME"));
        executionRequest.getResourceProperties().put("nsProductName", new GenericExecutionRequestPropertyValue("ACME-Product"));
        executionRequest.getResourceProperties().put("nsSoftwareVersion", new GenericExecutionRequestPropertyValue("1.0"));
        executionRequest.getResourceProperties().put("nsdVersion", new GenericExecutionRequestPropertyValue("1.0"));

        executionRequest.getResourceProperties().put("nsFlavourId", new GenericExecutionRequestPropertyValue("Chocolate"));
        executionRequest.getResourceProperties().put("nsInstantiationLevelId", new GenericExecutionRequestPropertyValue("1"));

        executionRequest.getResourceProperties().put("extVirtualLinks.0.id", new GenericExecutionRequestPropertyValue("45672c22-7c12-49ed-8a4f-e4532b3026fb"));
        // executionRequest.getResourceProperties().put("extVirtualLinks.0.vimConnectionId", new GenericExecutionRequestPropertyValue("f03a29d5-1fc6-11e9-83ea-fa163e045578"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.resourceId", new GenericExecutionRequestPropertyValue("45672c22-5r54-49ed-8a4f-e4532b3026fb"));

        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpdId", new GenericExecutionRequestPropertyValue("SERVICES_ExtCp"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpInstanceId", new GenericExecutionRequestPropertyValue("cpInstanceId111"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.linkPortId", new GenericExecutionRequestPropertyValue("linkPortId222"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.layerProtocol", new GenericExecutionRequestPropertyValue("IP_OVER_ETHERNET"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.ipOverEthernet.macAddress", new GenericExecutionRequestPropertyValue("fa:16:3e:23:fd:d7"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.ipOverEthernet.ipAddresses.0.type", new GenericExecutionRequestPropertyValue("IPV4"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.ipOverEthernet.ipAddresses.0.fixedAddresses.0", new GenericExecutionRequestPropertyValue("131.160.162.32"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.ipOverEthernet.ipAddresses.0.numDynamicAddresses", new GenericExecutionRequestPropertyValue("1"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.ipOverEthernet.ipAddresses.0.addressRange.minAddress", new GenericExecutionRequestPropertyValue("131.160.162.32"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.ipOverEthernet.ipAddresses.0.addressRange.maxAddress", new GenericExecutionRequestPropertyValue("131.160.162.36"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extCps.0.cpConfig.0.cpProtocolData.0.ipOverEthernet.ipAddresses.0.subnetId", new GenericExecutionRequestPropertyValue(""));

        executionRequest.getResourceProperties().put("extVirtualLinks.0.extLinkPorts.0.id", new GenericExecutionRequestPropertyValue("id"));
        //executionRequest.getResourceProperties().put("extVirtualLinks.0.extLinkPorts.0.resourceHandle.vimConnectionId", new GenericExecutionRequestPropertyValue("47772c22-7c12-49ed-8a4f-e7625b3026fb"));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extLinkPorts.0.resourceHandle.resourceProviderId", new GenericExecutionRequestPropertyValue(""));
        executionRequest.getResourceProperties().put("extVirtualLinks.0.extLinkPorts.0.resourceHandle.resourceId", new GenericExecutionRequestPropertyValue("45672c22-5r54-49ed-8a4f-e4532b3026fb"));
        // executionRequest.getResourceProperties().put("extVirtualLinks.0.extLinkPorts.0.resourceHandle.vimLevelResourceType", new GenericExecutionRequestPropertyValue(""));

        executionRequest.getResourceProperties().put("vimConnectionInfo.0.id", new GenericExecutionRequestPropertyValue("4408b119-eb54-11e7-bae0-fa163eb90b5c"));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.vimId", new GenericExecutionRequestPropertyValue("vim1"));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.vimType", new GenericExecutionRequestPropertyValue("Openstack"));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.interfaceInfo.identityEndPoint", new GenericExecutionRequestPropertyValue("https://openstack:5000/v2.0"));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.accessInfo.projectId", new GenericExecutionRequestPropertyValue("cab32f669c18404d8bed0fae6bf088aa"));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.accessInfo.credentials.username", new GenericExecutionRequestPropertyValue("dummy"));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.accessInfo.credentials.password", new GenericExecutionRequestPropertyValue("RXJpY3Nzb24uLjYyNA=="));

        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);
        final String message = messageConversionService.generateMessageFromRequest("InstantiateNsRequest", executionRequest);

        assertThat(new JsonContent<>(JavascriptMessageConversionServiceImplTest.class, null, message)).isEqualToJson("{\n"
                + " \"nsFlavourId\": \"Chocolate\",\n"
                + "  \"nsInstantiationLevelId\": \"1\",\n"
                + "  \"extVirtualLinks\": [\n"
                + "    {\n"
                + "      \"id\": \"45672c22-7c12-49ed-8a4f-e4532b3026fb\",\n"
                + "       \"resourceId\": \"45672c22-5r54-49ed-8a4f-e4532b3026fb\",\n"
                + "      \"extCps\": [\n"
                + "        {\n"
                + "          \"cpdId\": \"SERVICES_ExtCp\",\n"
                + "          \"cpConfig\": [\n"
                + "            {\n"
                + "              \"cpInstanceId\": \"cpInstanceId111\",\n"
                + "              \"linkPortId\": \"linkPortId222\",\n"
                + "              \"cpProtocolData\": [\n"
                + "                {\n"
                + "                  \"layerProtocol\": \"IP_OVER_ETHERNET\",\n"
                + "                  \"ipOverEthernet\": {\n"
                + "                    \"macAddress\": \"fa:16:3e:23:fd:d7\",\n"
                + "                    \"ipAddresses\": [\n"
                + "                      {\n"
                + "                        \"type\": \"IPV4\",\n"
                + "                        \"fixedAddresses\": [\n"
                + "                          \"131.160.162.32\"\n"
                + "                        ],\n"
                + "                        \"numDynamicAddresses\": \"1\",\n"
                + "                        \"addressRange\": {\n"
                + "                          \"minAddress\": \"131.160.162.32\",\n"
                + "                          \"maxAddress\": \"131.160.162.36\"\n"
                + "                        },\n"
                + "                        \"subnetId\": \"\"\n"
                + "                      }\n"
                + "                    ]\n"
                + "                  }\n"
                + "                }\n"
                + "              ]\n"
                + "            }\n"
                + "          ]\n"
                + "        }\n"
                + "      ],\n"
                + "      \"extLinkPorts\": [\n"
                + "        {\n"
                + "          \"id\": \"id\",\n"
                + "          \"resourceHandle\": {\n"
                + "          \"resourceProviderId\": \"\",\n"
                + "            \"resourceId\": \"45672c22-5r54-49ed-8a4f-e4532b3026fb\"\n"
                + "          }\n"
                + "        }\n"
                + "      ]\n"
                + "    }\n"
                + "  ]\n"
                /*+ "  {\n"
                + "      \"id\": \"4408b119-eb54-11e7-bae0-fa163eb90b5c\",\n"
                + "       \"interfaceInfo\": {\n"
                + "        \"identityEndPoint\": \"https://openstack:5000/v2.0\"\n"
                + "      },\n"
                + "      \"accessInfo\": {\n"
                + "        \"projectId\": \"cab32f669c18404d8bed0fae6bf088aa\",\n"
                + "        \"credentials\": {\n"
                + "          \"username\": \"dummy\",\n"
                + "          \"password\": \"RXJpY3Nzb24uLjYyNA==\"\n"
                + "        }\n"
                + "      }\n"
                + "    }\n"
                + "  ]\n"*/
                + "}");
    }

    @Test
    public void testGenerateInstantiateMessageWithComplexProperties() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        executionRequest.setLifecycleName("Configure");
        executionRequest.setDeploymentLocation(TEST_DL_NO_AUTH);
        executionRequest.getResourceProperties().put("nsdId", new GenericExecutionRequestPropertyValue("fa2343af-2a81-4e84-a667-e40662e5ed93"));
        executionRequest.getResourceProperties().put("nsInstanceId", new GenericExecutionRequestPropertyValue("0000-0002-0000-0001"));
        executionRequest.getResourceProperties().put("nsInstanceName", new GenericExecutionRequestPropertyValue("HelloWorld1"));
        executionRequest.getResourceProperties().put("nsPkgId", new GenericExecutionRequestPropertyValue("316aa140-c99a-4a08-b8f5-8e2cb73c83e8"));
        executionRequest.getResourceProperties().put("nsProvider", new GenericExecutionRequestPropertyValue("ACME"));
        executionRequest.getResourceProperties().put("nsProductName", new GenericExecutionRequestPropertyValue("ACME-Product"));
        executionRequest.getResourceProperties().put("nsSoftwareVersion", new GenericExecutionRequestPropertyValue("1.0"));
        executionRequest.getResourceProperties().put("nsdVersion", new GenericExecutionRequestPropertyValue("1.0"));

        executionRequest.getResourceProperties().put("nsFlavourId", new GenericExecutionRequestPropertyValue("Chocolate"));
        executionRequest.getResourceProperties().put("nsInstantiationLevelId", new GenericExecutionRequestPropertyValue("1"));

        executionRequest.getResourceProperties().put("vimConnectionInfo.0.stringProperty", new GenericExecutionRequestPropertyValue("stringvalue"));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.intProperty", new GenericExecutionRequestPropertyValue(12345, PropertyType.INTEGER.getValue()));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.floatProperty", new GenericExecutionRequestPropertyValue(0.12345, PropertyType.FLOAT.getValue()));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.booleanProperty", new GenericExecutionRequestPropertyValue(true, PropertyType.BOOLEAN.getValue()));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.timestampProperty", new GenericExecutionRequestPropertyValue(OffsetDateTime.parse("2020-12-03T18:37:13.367Z"), PropertyType.TIMESTAMP.getValue()));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.listProperty", new GenericExecutionRequestPropertyValue(Arrays.asList("listVal", 12345, 0.12345, true, OffsetDateTime.parse("2020-12-03T18:37:13.367Z")), PropertyType.LIST.getValue()));

        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("key1", "val1");
        propertyMap.put("key2", 12345);
        propertyMap.put("key4", OffsetDateTime.parse("2020-12-03T18:37:13.367Z"));
        propertyMap.put("key3", Arrays.asList("listVal", 12345, 0.12345, true));
        executionRequest.getResourceProperties().put("vimConnectionInfo.0.mapProperty", new GenericExecutionRequestPropertyValue(propertyMap, PropertyType.MAP.getValue()));

        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);
        final String message = messageConversionService.generateMessageFromRequest("InstantiateNsRequest", executionRequest);

        assertThat(new JsonContent<>(JavascriptMessageConversionServiceImplTest.class, null, message)).isEqualToJson("{\n"
                + " \"nsFlavourId\": \"Chocolate\",\n"
                + "  \"nsInstantiationLevelId\": \"1\",\n"
                + "  \"vimConnectionInfo\": [\n"
                + "    {\n"
                + "      \"stringProperty\": \"stringvalue\",\n"
                + "      \"intProperty\": 12345,\n"
                + "      \"floatProperty\": 0.12345,\n"
                + "      \"booleanProperty\": true,\n"
                + "      \"timestampProperty\": \"2020-12-03T18:37:13.367Z\",\n"
                + "      \"listProperty\": [\"listVal\",12345,0.12345,true,\"2020-12-03T18:37:13.367Z\"],\n"
                + "      \"mapProperty\": {\"key1\":\"val1\",\"key2\":12345,\"key3\":[\"listVal\",12345,0.12345,true],\"key4\":\"2020-12-03T18:37:13.367Z\"}\n"
                + "    }\n"
                + "  ]\n"
                + "}");
    }

    @Test
    public void testGenerateInstantiateResponseMessageWithComplexProperties() throws Exception {
        final ExecutionRequest executionRequest = new ExecutionRequest();
        final MessageConversionService messageConversionService = new JavascriptMessageConversionServiceImpl(objectMapper);

        String nsInstance = loadFileIntoString("examples/NsInstance.json");
        Map<String, Object> nsInstanceAsMap = objectMapper.readValue(nsInstance, new TypeReference<Map<String, Object>>() {
        });

        Map extraPropertiesMap = new HashMap();
        extraPropertiesMap.put("stringProperty", "stringvalue");
        extraPropertiesMap.put("intProperty1", 12345);
        extraPropertiesMap.put("floatProperty", 0.12345);
        extraPropertiesMap.put("booleanProperty", true);
        nsInstanceAsMap.putAll(extraPropertiesMap);

        nsInstanceAsMap.put("timestampProperty", OffsetDateTime.parse("2020-12-03T18:37:13.367Z"));
        nsInstanceAsMap.put("listProperty", Arrays.asList("listVal", 12345, 0.12345, true));
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("key1", "val1");
        propertyMap.put("key2", 12345);
        propertyMap.put("key3", 0.12345);
        propertyMap.put("key4", true);
        nsInstanceAsMap.put("mapProperty", propertyMap);

        String nsInstanceWithExtraProps = objectMapper.writeValueAsString(nsInstanceAsMap);

        final Map<String, Object> extractedProperties = messageConversionService.extractPropertiesFromMessage("NsInstance", executionRequest, nsInstanceWithExtraProps);

        assertThat(extractedProperties).containsAllEntriesOf(extraPropertiesMap);
        assertThat(extractedProperties.get("timestampProperty")).isEqualTo("2020-12-03T18:37:13.367Z");
        assertThat(extractedProperties.get("listProperty.0")).isEqualTo("listVal");
        assertThat(extractedProperties.get("listProperty.1")).isEqualTo(12345);
        assertThat(extractedProperties.get("listProperty.2")).isEqualTo(0.12345);
        assertThat(extractedProperties.get("listProperty.3")).isEqualTo(true);
        assertThat(extractedProperties.get("mapProperty.key1")).isEqualTo("val1");
        assertThat(extractedProperties.get("mapProperty.key2")).isEqualTo(12345);
        assertThat(extractedProperties.get("mapProperty.key3")).isEqualTo(0.12345);
        assertThat(extractedProperties.get("mapProperty.key4")).isEqualTo(true);
    }

}