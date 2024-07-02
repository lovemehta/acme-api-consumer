package com.acme.exceptions;

/**
 * Exception thrown when data fetching fails.
 */
public class DataFetchException extends Exception {
    public DataFetchException(String message) {
        super(message);
    }
}
