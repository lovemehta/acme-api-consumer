package com.acme.service;

import com.acme.consumer.ParcelShopsApiConsumer;
import com.acme.exceptions.AuthenticationException;
import com.acme.exceptions.DataFetchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParcelShopsFetchingServiceTest {

    @Mock
    private ILoginService loginService;

    @Mock
    private IParcelShopService parcelShopService;

    @InjectMocks
    private ParcelShopsApiConsumer parcelShopsFetchingService;

    @BeforeEach
    void setUp() {
        // Initialization
    }

    @Test
    void fetchParcelShops_Successful() throws AuthenticationException, DataFetchException {
        // Arrange
        String fakeToken = "fake-access-token";
        when(loginService.getAccessToken()).thenReturn(fakeToken);

        // Act
        parcelShopsFetchingService.consume();

        // Assert
        verify(loginService).getAccessToken();
        verify(parcelShopService).fetchAndSaveParcelShops(fakeToken);
    }

    @Test
    void fetchParcelShops_FailsOnLogin() throws AuthenticationException, DataFetchException {
        // Arrange
        when(loginService.getAccessToken()).thenThrow(new AuthenticationException("Login failed"));

        // Assert
        assertThrows(AuthenticationException.class, () -> {
            // Act
            parcelShopsFetchingService.consume();
        });

        verify(parcelShopService, never()).fetchAndSaveParcelShops(anyString());
    }
}
