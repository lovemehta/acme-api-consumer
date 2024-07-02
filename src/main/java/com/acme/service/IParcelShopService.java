package com.acme.service;

import com.acme.exceptions.DataFetchException;

/**
 * Service interface for handling parcel shop operations.
 */
public interface IParcelShopService {

    /**
     * Fetches and saves parcel shops using the provided access token.
     *
     * @param accessToken the access token for authentication
     * @throws DataFetchException if the data fetching process fails
     */
    void fetchAndSaveParcelShops(String accessToken) throws DataFetchException;
}
