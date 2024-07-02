package com.acme.models;

import lombok.Data;

@Data
public class OpeningTime {

    private Integer day; // Integer representation of the day, e.g., 1 for Monday, 2 for Tuesday, etc.

    private String from; // Opening time

    private String to; // Closing time

    private String closedDuring; // Time during the day when the shop is closed, e.g., "13:00-14:00" for lunch break

    // Constructors, getters, and setters are managed by Lombok
}
