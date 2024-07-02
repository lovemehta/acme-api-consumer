package com.acme.external.parcelShops;

import com.acme.config.BaseApiConfig;
import com.acme.config.ParcelShopsApiConfig;
import com.acme.utils.RestClient;
import com.acme.external.parcelShops.response.ParcelShopsResponse;
import com.acme.models.ParcelShop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Client for interacting with the Parcel Shops API.
 */
@Component
public class ParcelShopsApiClient implements IParcelShopsApiClient {

    private static final Logger logger = LoggerFactory.getLogger(ParcelShopsApiClient.class);

    private final RestClient restClient;
    private final ParcelShopsApiConfig parcelShopsApiConfig;
    private final BaseApiConfig baseApiConfig;

    /**
     * Constructs a new ParcelShopsApiClient.
     *
     * @param restClient the RestClient to use for HTTP requests
     * @param parcelShopsApiConfig the configuration for parcel shops API
     * @param baseApiConfig the base API configuration
     */
    @Autowired
    public ParcelShopsApiClient(RestClient restClient, ParcelShopsApiConfig parcelShopsApiConfig, BaseApiConfig baseApiConfig) {
        this.restClient = restClient;
        this.parcelShopsApiConfig = parcelShopsApiConfig;
        this.baseApiConfig = baseApiConfig;
    }

    /**
     * Fetches parcel shops using the provided access token.
     *
     * @param accessToken the access token for authentication
     * @return an Optional containing the ParcelShopsResponse if the fetch is successful, or an empty Optional if not
     */
    @Override
    public Optional<ParcelShopsResponse> fetchParcelShops(String accessToken) {
        String url = buildUrl();
        HttpHeaders headers = createHeaders(accessToken);

        try {
            List<ParcelShop> response = restClient.get(url, headers, new ParameterizedTypeReference<List<ParcelShop>>() {});
            if (response != null) {
                return Optional.of(new ParcelShopsResponse(response));
            } else {
                logger.warn("Received null response while fetching parcel shops");
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Failed to fetch parcel shops", e);
            return Optional.empty();
        }
    }

    /**
     * Builds the URL for the parcel shops request.
     *
     * @return the complete URL as a string
     */
    private String buildUrl() {
        return UriComponentsBuilder.fromHttpUrl(baseApiConfig.getBaseUrl() + parcelShopsApiConfig.getUrl())
                .queryParam("carrier", parcelShopsApiConfig.getCarrier())
                .queryParam("country", parcelShopsApiConfig.getCountry())
                .queryParam("limit", parcelShopsApiConfig.getLimit())
                .toUriString();
    }

    /**
     * Creates HTTP headers for the request.
     *
     * @param accessToken the access token for authentication
     * @return HttpHeaders with the Authorization and ClientId set
     */
    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("ClientId", baseApiConfig.getClientId());
        return headers;
    }
}
