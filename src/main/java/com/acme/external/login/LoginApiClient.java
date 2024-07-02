package com.acme.external.login;

import com.acme.config.BaseApiConfig;
import com.acme.config.LoginApiConfig;
import com.acme.utils.RestClient;
import com.acme.external.login.request.LoginRequest;
import com.acme.external.login.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Client for performing login operations.
 */
@Component
public class LoginApiClient implements ILoginApiClient {

    private static final Logger logger = LoggerFactory.getLogger(LoginApiClient.class);

    private final RestClient restClient;
    private final LoginApiConfig loginAPIConfig;
    private final BaseApiConfig baseApiConfig;

    /**
     * Constructs a new LoginApiClient.
     *
     * @param restClient     the RestClient to use for HTTP requests
     * @param loginAPIConfig the configuration for login API
     * @param baseApiConfig  the base API configuration
     */
    @Autowired
    public LoginApiClient(RestClient restClient, LoginApiConfig loginAPIConfig, BaseApiConfig baseApiConfig) {
        this.restClient = restClient;
        this.loginAPIConfig = loginAPIConfig;
        this.baseApiConfig = baseApiConfig;
    }

    /**
     * Logs into the API and returns a LoginResponse.
     *
     * @return an Optional containing the LoginResponse if login is successful, or an empty Optional if not
     */
    @Override
    public Optional<LoginResponse> login() {
        try {
            LoginRequest loginRequest = createLoginRequest();
            HttpHeaders headers = createHeaders();
            String url = getUrl();
            return Optional.ofNullable(restClient.post(url, headers, loginRequest, LoginResponse.class));
        } catch (Exception e) {
            logger.error("Login failed", e);
            return Optional.empty();
        }
    }

    /**
     * Creates a LoginRequest using the configuration values.
     *
     * @return a new LoginRequest
     */
    private LoginRequest createLoginRequest() {
        return new LoginRequest(loginAPIConfig.getUsername(), loginAPIConfig.getPassword(), null);
    }

    /**
     * Creates HTTP headers for the request.
     *
     * @return HttpHeaders with the ClientId set
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("ClientId", baseApiConfig.getClientId());
        return headers;
    }

    /**
     * Constructs the URL for the login request.
     *
     * @return the complete URL for the login request
     */
    private String getUrl() {
        return baseApiConfig.getBaseUrl() + loginAPIConfig.getUrl();
    }
}
