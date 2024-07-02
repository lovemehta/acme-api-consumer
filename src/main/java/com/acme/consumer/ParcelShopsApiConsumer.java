package com.acme.consumer;

import com.acme.exceptions.AuthenticationException;
import com.acme.exceptions.DataFetchException;
import com.acme.service.ILoginService;
import com.acme.service.IParcelShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for consuming the Parcel Shops API.
 */
@Service
public class ParcelShopsApiConsumer implements IApiConsumer {

    private final ILoginService loginService;
    private final IParcelShopService parcelShopService;

    /**
     * Constructs a new ParcelShopsApiConsumer.
     *
     * @param loginService the service for login operations
     * @param parcelShopService the service for parcel shop operations
     */
    @Autowired
    public ParcelShopsApiConsumer(ILoginService loginService, IParcelShopService parcelShopService) {
        this.loginService = loginService;
        this.parcelShopService = parcelShopService;
    }

    /**
     * Consumes the Parcel Shops API to fetch and save parcel shops data.
     *
     * @throws AuthenticationException if the authentication process fails
     * @throws DataFetchException if the data fetching process fails
     */
    @Override
    public void consume() throws AuthenticationException, DataFetchException {
        parcelShopService.fetchAndSaveParcelShops(loginService.getAccessToken());
    }
}
