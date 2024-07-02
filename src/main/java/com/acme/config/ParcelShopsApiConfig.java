package com.acme.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for parcel shops API.
 * Binds properties prefixed with "parcel-shops-api-config".
 */
@Data
@ConfigurationProperties("parcel-shops-api-config")
@Configuration
public class ParcelShopsApiConfig {

	/**
	 * Carrier for parcel shops API.
	 */
	private String carrier;

	/**
	 * Country for parcel shops API.
	 */
	private String country;

	/**
	 * Limit for the number of results from the API.
	 */
	private int limit;

	/**
	 * URL for parcel shops API.
	 */
	private String url;
}
