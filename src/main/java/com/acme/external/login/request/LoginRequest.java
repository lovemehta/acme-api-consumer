package com.acme.external.login.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data // Lombok's annotation to generate getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Username is required") // Ensures the username is not blank
    @Size(min = 1, message = "Username must be at least 1 character long") // Ensures the username meets the minimum length requirement
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long") // Ensures the password meets the minimum length requirement
    private String password;

    private Boolean omitClaims;

}
