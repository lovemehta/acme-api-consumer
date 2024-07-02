package com.acme.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link RestClient} for making HTTP requests using {@link RestTemplate}.
 */
@Component
public class RestAPIClient implements RestClient {

    private final RestTemplate restTemplate;

    /**
     * Constructs a new RestAPIClient.
     *
     * @param restTemplate the RestTemplate to use for HTTP requests
     */
    @Autowired
    public RestAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Makes a POST request to the specified URL.
     */
    @Override
    public <T, U> T post(String url, HttpHeaders headers, U data, Class<T> responseType) {
        HttpEntity<U> entity = new HttpEntity<>(data, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, entity, responseType);
        return handleResponse(responseEntity);
    }

    /**
     * Makes a GET request to the specified URL.
     */
    @Override
    public <T> T get(String url, HttpHeaders headers, ParameterizedTypeReference<T> responseType) {
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        return handleResponse(responseEntity);
    }

    /**
     * Handles the response from an HTTP request.
     *
     * @param responseEntity the response entity from the HTTP request
     * @param <T> the type of the response
     * @return the body of the response entity
     * @throws RuntimeException if the response status code is not OK
     */
    private <T> T handleResponse(ResponseEntity<T> responseEntity) {
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("External API call failed: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();
    }
}
