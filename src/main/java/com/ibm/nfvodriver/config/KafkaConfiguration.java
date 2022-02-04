package com.ibm.nfvodriver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.nfvodriver.driver.NSLifecycleManagementDriver;
import com.ibm.nfvodriver.service.ExternalMessagingService;
import com.ibm.nfvodriver.service.impl.KafkaExternalMessagingServiceImpl;
import com.ibm.nfvodriver.service.impl.LcmOpOccPollingService;
import com.ibm.nfvodriver.service.impl.LoggingExternalMessagingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration("KafkaConfiguration")
public class KafkaConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfiguration.class);

    @Configuration("KafkaConfigurationEnabled")
    @ConditionalOnProperty(value = "vnfmdriver.kafka.enabled", matchIfMissing = true)
    @EnableKafka
    public static class KafkaConfigurationEnabled {

        @Bean
        @Primary
        public ExternalMessagingService getKafkaEMS(NFVODriverProperties properties, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
            logger.info("Creating Kafka EMS Bus Connector");
            return new KafkaExternalMessagingServiceImpl(properties, kafkaTemplate, objectMapper);
        }

        @Bean
        public LcmOpOccPollingService lcmOpOccPollingService(NSLifecycleManagementDriver driver, ExternalMessagingService externalMessagingService, ObjectMapper objectMapper) {
            return new LcmOpOccPollingService(driver, externalMessagingService, objectMapper);
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public ExternalMessagingService getLoggingEMS() {
        logger.info("Creating Logging EMS Bus Connector");
        return new LoggingExternalMessagingServiceImpl();
    }


}