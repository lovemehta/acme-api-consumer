package com.acme.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for base API settings.
 * Binds properties prefixed with "base-api-config".
 */
@Configuration
@ConfigurationProperties("base-api-config")
@Data
public class BaseApiConfig {

	/**
	 * Base URL for API connections.
	 */
	private String baseUrl;

	/**
	 * Client ID for API identification.
	 */
	private String clientId;

}
