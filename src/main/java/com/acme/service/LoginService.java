package com.acme.service;

import com.acme.external.login.ILoginApiClient;
import com.acme.external.login.response.LoginResponse;
import com.acme.exceptions.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for handling login operations.
 */
@Service
public class LoginService implements ILoginService {

    private final ILoginApiClient loginApiClient;
    private String currentToken = null;

    /**
     * Constructs a new LoginService.
     *
     * @param loginApiClient the API client for login operations
     */
    @Autowired
    public LoginService(ILoginApiClient loginApiClient) {
        this.loginApiClient = loginApiClient;
    }

    /**
     * Retrieves an access token, refreshing it if necessary.
     *
     * @return the access token as a String
     * @throws AuthenticationException if the authentication process fails
     */
    @Override
    public String getAccessToken() throws AuthenticationException {
        if (currentToken == null || !isTokenValid(currentToken)) {
            currentToken = refreshToken();
        }
        return currentToken;
    }

    /**
     * Validates the current token.
     *
     * @param token the token to validate
     * @return false, as the validation logic is not yet implemented
     */
    private boolean isTokenValid(String token) {
        // Dummy implementation - always returns false for now
        // We can implement actual token validation logic here in the future
        return false;
    }

    /**
     * Refreshes the access token by logging in again.
     *
     * @return the new access token
     * @throws AuthenticationException if the login process fails
     */
    private String refreshToken() throws AuthenticationException {
        Optional<LoginResponse> loginResponseOpt = loginApiClient.login();
        LoginResponse loginResponse = loginResponseOpt.orElseThrow(() -> new AuthenticationException("Login failed!"));
        return loginResponse.getAccessToken();
    }
}
