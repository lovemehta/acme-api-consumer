package com.acme.exceptions;

/**
 * Exception thrown when authentication fails.
 */
public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}
