package com.acme.service;

import com.acme.exceptions.DataFetchException;
import com.acme.external.parcelShops.IParcelShopsApiClient;
import com.acme.external.parcelShops.response.ParcelShopsResponse;
import com.acme.models.ParcelShop;
import com.acme.repository.ParcelShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for handling parcel shop operations.
 */
@Service
public class ParcelShopService implements IParcelShopService {

    private static final Logger logger = LoggerFactory.getLogger(ParcelShopService.class);

    private final IParcelShopsApiClient parcelShopsApiClient;
    private final ParcelShopRepository parcelShopRepository;

    /**
     * Constructs a new ParcelShopService.
     *
     * @param parcelShopsApiClient the API client for fetching parcel shops
     * @param parcelShopRepository the repository for saving parcel shops
     */
    @Autowired
    public ParcelShopService(IParcelShopsApiClient parcelShopsApiClient, ParcelShopRepository parcelShopRepository) {
        this.parcelShopsApiClient = parcelShopsApiClient;
        this.parcelShopRepository = parcelShopRepository;
    }

    /**
     * Fetches and saves parcel shops using the provided access token.
     *
     * @param accessToken the access token for authentication
     * @throws DataFetchException if the data fetching process fails
     */
    @Override
    public void fetchAndSaveParcelShops(String accessToken) throws DataFetchException {
        try {
            Optional<ParcelShopsResponse> parcelShopsResponseOpt = parcelShopsApiClient.fetchParcelShops(accessToken);
            ParcelShopsResponse parcelShopsResponse = parcelShopsResponseOpt.orElseThrow(() -> new DataFetchException("Parcel shops API response is empty!"));

            List<ParcelShop> parcelShops = parcelShopsResponse.getParcelShops();
            if (!parcelShops.isEmpty()) {
                parcelShopRepository.saveAll(parcelShops);
            }
        } catch (Exception e) {
            handleRetry(e);
        }
    }

    /**
     * Handles retry logic when fetching parcel shops fails.
     *
     * @param e the exception that occurred
     * @throws DataFetchException always throws the exception after logging
     */
    private void handleRetry(Exception e) throws DataFetchException {
        logger.error("Error fetching parcel shops, attempting to retry", e);
        throw new DataFetchException("Failed to fetch parcel shops, need to retry: " + e.getMessage());
    }
}
