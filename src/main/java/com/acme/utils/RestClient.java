package com.acme.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;

/**
 * Interface for making HTTP requests.
 */
public interface RestClient {

    /**
     * Makes a POST request to the specified URL.
     *
     * @param url the URL to send the POST request to
     * @param headers the HTTP headers to include in the request
     * @param data the data to include in the body of the request
     * @param responseType the type of the response
     * @param <T> the type of the response
     * @param <U> the type of the request body
     * @return the response from the POST request
     */
    <T, U> T post(String url, HttpHeaders headers, U data, Class<T> responseType);

    /**
     * Makes a GET request to the specified URL.
     *
     * @param url the URL to send the GET request to
     * @param headers the HTTP headers to include in the request
     * @param responseType the type of the response
     * @param <T> the type of the response
     * @return the response from the GET request
     */
    <T> T get(String url, HttpHeaders headers, ParameterizedTypeReference<T> responseType);
}
