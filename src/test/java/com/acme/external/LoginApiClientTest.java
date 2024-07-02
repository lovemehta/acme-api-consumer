package com.acme.external;

import com.acme.config.BaseApiConfig;
import com.acme.config.LoginApiConfig;
import com.acme.external.login.LoginApiClient;
import com.acme.external.login.request.LoginRequest;
import com.acme.external.login.response.LoginResponse;
import com.acme.utils.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginApiClientTest {

    @Mock
    private RestClient restClient;
    @Mock
    private LoginApiConfig loginAPIConfig;
    @Mock
    private BaseApiConfig baseApiConfig;

    @InjectMocks
    private LoginApiClient loginApiClient;

    private final String TEST_URL = "http://test.com/login";
    private final String CLIENT_ID = "test-client-id";
    private final String USERNAME = "testuser";
    private final String PASSWORD = "password";
    private final String ACCESS_TOKEN = "valid-access-token";

    @BeforeEach
    void setUp() {
        when(baseApiConfig.getBaseUrl()).thenReturn("http://test.com");
        when(loginAPIConfig.getUrl()).thenReturn("/login");
        when(loginAPIConfig.getUsername()).thenReturn(USERNAME);
        when(loginAPIConfig.getPassword()).thenReturn(PASSWORD);
        when(baseApiConfig.getClientId()).thenReturn(CLIENT_ID);
    }

    @Test
    void login_Success() {
        // Arrange
        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setAccessToken(ACCESS_TOKEN);
        when(restClient.post(eq(TEST_URL), any(HttpHeaders.class), any(LoginRequest.class), eq(LoginResponse.class)))
                .thenReturn(expectedResponse);

        // Act
        Optional<LoginResponse> response = loginApiClient.login();

        // Assert
        assertTrue(response.isPresent());
        assertEquals(ACCESS_TOKEN, response.get().getAccessToken());
        verify(restClient).post(eq(TEST_URL), any(HttpHeaders.class), any(LoginRequest.class), eq(LoginResponse.class));
    }

    @Test
    void login_Failure_ReturnsEmpty() {
        // Arrange
        when(restClient.post(eq(TEST_URL), any(HttpHeaders.class), any(LoginRequest.class), eq(LoginResponse.class)))
                .thenReturn(null);

        // Act
        Optional<LoginResponse> response = loginApiClient.login();

        // Assert
        assertFalse(response.isPresent());
    }

    @Test
    void login_ThrowsException_ReturnsEmpty() {
        // Arrange
        when(restClient.post(eq(TEST_URL), any(HttpHeaders.class), any(LoginRequest.class), eq(LoginResponse.class)))
                .thenThrow(new RuntimeException("Connection error"));

        // Act
        Optional<LoginResponse> response = loginApiClient.login();

        // Assert
        assertFalse(response.isPresent());
    }
}
