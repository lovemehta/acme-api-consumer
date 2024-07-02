package com.acme.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for login API.
 * Binds properties prefixed with "login-api-config".
 */
@Data
@ConfigurationProperties("login-api-config")
@Configuration
public class LoginApiConfig {

	/**
	 * Username for login API.
	 */
	private String username;

	/**
	 * Password for login API.
	 */
	private String password;

	/**
	 * URL for login API.
	 */
	private String url;
}
