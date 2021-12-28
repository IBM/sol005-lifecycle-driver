package com.ibm.nfvodriver.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springfox Swagger configuration.
 * <p>
 * Warning! When having a lot of REST endpoints, Springfox can become a performance issue. In that case, you can use a
 * specific Spring profile for this class, so that only front-end developers have access to the Swagger view.
 */
@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Value("${info.app.name:NFVO Driver}")
    private String appName;

    @Value("${info.app.description:ETSI SOL005 NFVO Driver}")
    private String appDescription;

    @Value("${info.app.version:0.0.1}")
    private String appVersion;

    @Value("${info.contact.name:Accanto Systems Ltd}")
    private String contactName;

    private final Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

    /**
     * Swagger Springfox configuration.
     *
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringfoxDocket() {
        logger.debug("Starting Swagger");
        Contact contact = new Contact(contactName, null, null);

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(appName)
                .description(appDescription)
                .version(appVersion)
                .contact(contact)
                .build();

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                                                      .forCodeGeneration(true)
                                                      .genericModelSubstitutes(ResponseEntity.class)
                                                      .select()
                                                      .paths(regex("/api/.*"))
                                                      .paths(regex("/nslcm/.*"))
                                                      .build();
    }
}
