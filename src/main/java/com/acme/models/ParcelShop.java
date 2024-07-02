package com.acme.models;

import com.acme.enums.CarrierType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
@Document(collection = "parcelShops")
public class ParcelShop {

    @Id
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    private String type; // Nullable

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;

    @NotNull(message = "Carrier is required")
    private CarrierType carrier;

    @NotBlank(message = "Address line 1 is required")
    private String addressLine1;

    @NotBlank(message = "Post code is required")
    private String postCode;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Country is required")
    private String country;

    private List<OpeningTime> openingTimes; // List of opening times

    private String carrierData; // JSON string, potentially unstructured and different per carrier
}
