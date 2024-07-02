package com.acme.service;

import com.acme.exceptions.AuthenticationException;

/**
 * Service interface for handling login operations.
 */
public interface ILoginService {

    /**
     * Retrieves the access token.
     *
     * @return the access token as a String
     * @throws AuthenticationException if the authentication process fails
     */
    String getAccessToken() throws AuthenticationException;
}
