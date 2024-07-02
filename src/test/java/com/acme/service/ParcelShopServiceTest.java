package com.acme.service;

import com.acme.exceptions.DataFetchException;
import com.acme.external.parcelShops.IParcelShopsApiClient;
import com.acme.external.parcelShops.response.ParcelShopsResponse;
import com.acme.models.ParcelShop;
import com.acme.repository.ParcelShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParcelShopServiceTest {

    @Mock
    private IParcelShopsApiClient parcelShopsApiClient;

    @Mock
    private ParcelShopRepository parcelShopRepository;

    @InjectMocks
    private ParcelShopService parcelShopService;

    private String fakeAccessToken;  // Declare the variable at the class level

    @BeforeEach
    void setUp() {
        fakeAccessToken = "fake-access-token";  // Initialize the token here for reuse in all tests
    }

    @Test
    void fetchAndSaveParcelShops_ThrowsDataFetchException_WhenResponseIsEmpty() {
        // Arrange
        when(parcelShopsApiClient.fetchParcelShops(fakeAccessToken)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataFetchException.class, () -> parcelShopService.fetchAndSaveParcelShops(fakeAccessToken));
    }

    @Test
    void fetchAndSaveParcelShops_SavesShops_WhenResponseIsNotEmpty() throws DataFetchException {
        // Arrange
        List<ParcelShop> shops = Arrays.asList(new ParcelShop(), new ParcelShop());
        ParcelShopsResponse response = new ParcelShopsResponse(shops);

        when(parcelShopsApiClient.fetchParcelShops(fakeAccessToken)).thenReturn(Optional.of(response));

        // Act
        parcelShopService.fetchAndSaveParcelShops(fakeAccessToken);

        // Assert
        verify(parcelShopRepository).saveAll(shops);
        verify(parcelShopsApiClient).fetchParcelShops(fakeAccessToken);
    }

    @Test
    void fetchAndSaveParcelShops_DoesNothing_WhenNoShopsToSave() throws DataFetchException {
        // Arrange
        List<ParcelShop> shops = Collections.emptyList();
        ParcelShopsResponse response = new ParcelShopsResponse(shops);

        when(parcelShopsApiClient.fetchParcelShops(fakeAccessToken)).thenReturn(Optional.of(response));

        // Act
        parcelShopService.fetchAndSaveParcelShops(fakeAccessToken);

        // Assert
        verify(parcelShopRepository, never()).saveAll(anyList());
    }
}
