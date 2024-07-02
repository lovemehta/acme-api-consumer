package com.acme.external.parcelShops;

import com.acme.external.parcelShops.response.ParcelShopsResponse;
import java.util.Optional;

/**
 * Interface for the Parcel Shops API client.
 * Provides methods to interact with the Parcel Shops API.
 */
public interface IParcelShopsApiClient {

    /**
     * Fetches parcel shops using the provided access token.
     *
     * @param accessToken the access token to authenticate the request
     * @return an {@link Optional} containing the {@link ParcelShopsResponse} if the fetch is successful, or an empty {@link Optional} if not
     */
    Optional<ParcelShopsResponse> fetchParcelShops(String accessToken);
}
