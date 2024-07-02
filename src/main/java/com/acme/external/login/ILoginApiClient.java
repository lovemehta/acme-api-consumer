package com.acme.external.login;

import com.acme.external.login.response.LoginResponse;
import java.util.Optional;

/**
 * Interface for login API client.
 * Provides method for logging into the API and obtaining a login response.
 */
public interface ILoginApiClient {

    /**
     * Logs into the API and returns a {@link LoginResponse}.
     *
     * @return an {@link Optional} containing the {@link LoginResponse} if login is successful, or an empty {@link Optional} if not.
     */
    Optional<LoginResponse> login();
}
