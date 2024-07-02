package com.acme.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class to define beans.
 */
@Configuration
public class ApplicationBeanConfig {

    /**
     * Creates a {@link RestTemplate} bean for making HTTP requests.
     *
     * @param builder the {@link RestTemplateBuilder} for customizing the RestTemplate
     * @return the configured {@link RestTemplate} instance
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
