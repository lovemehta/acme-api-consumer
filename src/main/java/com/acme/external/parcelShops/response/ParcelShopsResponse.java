package com.acme.external.parcelShops.response;

import com.acme.models.ParcelShop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelShopsResponse {

    private List<ParcelShop> parcelShops; // List to hold multiple ParcelShop objects

    // Constructors, getters, and setters are managed by Lombok
}
