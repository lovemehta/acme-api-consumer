package com.acme.external.login.response;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Data
public class LoginResponse {

    @NotBlank(message = "Email is required")
    @Size(min = 1, message = "Email must not be empty")
    private String email;

    @NotBlank(message = "First name is required")
    @Size(min = 1, message = "First name must not be empty")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, message = "Last name must not be empty")
    private String lastName;

    private Boolean newsletterOptIn;

    private String createdAt;

    private String username;

    @NotBlank(message = "Access token is required")
    private String accessToken;

    private Integer expiresIn;

    private String refreshToken;

}