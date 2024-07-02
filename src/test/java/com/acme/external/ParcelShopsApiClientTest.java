package com.acme.external;

import com.acme.config.BaseApiConfig;
import com.acme.config.ParcelShopsApiConfig;
import com.acme.external.parcelShops.ParcelShopsApiClient;
import com.acme.utils.RestClient;
import com.acme.external.parcelShops.response.ParcelShopsResponse;
import com.acme.models.ParcelShop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParcelShopsApiClientTest {

    @Mock
    private RestClient restClient;

    @Mock
    private ParcelShopsApiConfig parcelShopsApiConfig;

    @Mock
    private BaseApiConfig baseApiConfig;

    @InjectMocks
    private ParcelShopsApiClient parcelShopsApiClient;

    private final String ACCESS_TOKEN = "valid-access-token";

    @BeforeEach
    void setUp() {
        when(baseApiConfig.getBaseUrl()).thenReturn("http://testapi.com");
        when(parcelShopsApiConfig.getUrl()).thenReturn("/parcelShops");
        when(parcelShopsApiConfig.getCarrier()).thenReturn("DHL");
        when(parcelShopsApiConfig.getCountry()).thenReturn("USA");
        when(parcelShopsApiConfig.getLimit()).thenReturn(10);
    }

    @Test
    void fetchParcelShops_Success() {
        // Arrange
        List<ParcelShop> mockResponse = Arrays.asList(new ParcelShop(), new ParcelShop());
        when(restClient.get(anyString(), any(HttpHeaders.class), eq(new ParameterizedTypeReference<List<ParcelShop>>() {})))
            .thenReturn(mockResponse);

        // Act
        Optional<ParcelShopsResponse> result = parcelShopsApiClient.fetchParcelShops(ACCESS_TOKEN);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(2, result.get().getParcelShops().size());
    }

    @Test
    void fetchParcelShops_EmptyResponse() {
        // Arrange
        when(restClient.get(anyString(), any(HttpHeaders.class), eq(new ParameterizedTypeReference<List<ParcelShop>>() {})))
            .thenReturn(null);

        // Act
        Optional<ParcelShopsResponse> result = parcelShopsApiClient.fetchParcelShops(ACCESS_TOKEN);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void fetchParcelShops_ExceptionCaught() {
        // Arrange
        when(restClient.get(anyString(), any(HttpHeaders.class), eq(new ParameterizedTypeReference<List<ParcelShop>>() {})))
            .thenThrow(new RuntimeException("Connection failure"));

        // Act
        Optional<ParcelShopsResponse> result = parcelShopsApiClient.fetchParcelShops(ACCESS_TOKEN);

        // Assert
        assertFalse(result.isPresent());
    }
}
