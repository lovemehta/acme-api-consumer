package com.acme.enums;

/**
 * Enum representing different carrier types.
 */
public enum CarrierType {
    COLLECT_PLUS,
    CORREOS,
    DHL,
    DHL_SE,
    FASTWAY,
    FERMOPOINT,
    GLS,
    GLS_EU,
    GLS_DK,
    GLS_FR,
    HERMES_UK,
    INPOST_PL,
    MONDIAL_RELAY,
    OMNIVA,
    POSTE_ITALIANE,
    POSTI,
    POSTNORD_NO;

    /**
     * Converts a string to the corresponding {@link CarrierType}.
     *
     * @param carrier the carrier string
     * @return the corresponding {@link CarrierType} or null if not found
     */
    public static CarrierType fromString(String carrier) {
        try {
            return CarrierType.valueOf(carrier);
        } catch (Exception e) {
            return null; // return null for unknown carrier types or special cases
        }
    }
}
