package com.acme.service;

import com.acme.external.login.ILoginApiClient;
import com.acme.external.login.response.LoginResponse;
import com.acme.exceptions.AuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private ILoginApiClient loginApiClient;

    @InjectMocks
    private LoginService loginService;

    private final String VALID_TOKEN = "valid-access-token";

    @BeforeEach
    void setUp()
    {
        LoginResponse response = new LoginResponse();
        response.setAccessToken(VALID_TOKEN);
        when(loginApiClient.login()).thenReturn(Optional.of(response));
    }

    @Test
    void getAccessToken_RefreshesToken_WhenTokenIsInvalid() throws AuthenticationException {
        // Arrange
        // Initial setup ensures token is considered invalid

        // Act
        String token = loginService.getAccessToken();

        // Assert
        assertEquals(VALID_TOKEN, token);
        verify(loginApiClient, times(1)).login(); // Ensures refreshToken is called
    }

    @Test
    void getAccessToken_ThrowsAuthenticationException_WhenLoginFails() {
        // Arrange
        when(loginApiClient.login()).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AuthenticationException.class, () -> loginService.getAccessToken());
    }
}
