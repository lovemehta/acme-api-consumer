package com.acme.consumer;

import com.acme.exceptions.AuthenticationException;
import com.acme.exceptions.DataFetchException;

/**
 * Service interface for consuming APIs.
 */
public interface IApiConsumer {

    /**
     * Consumes the API.
     *
     * @throws AuthenticationException if the authentication process fails
     * @throws DataFetchException if the data fetching process fails
     */
    void consume() throws AuthenticationException, DataFetchException;
}
