package com.neosystem.sifen.domain;

import org.springframework.stereotype.Service;

/**
 * Service to calculate the CDC (Código de Control) for SIFEN.
 * The CDC is a 44-digit number that uniquely identifies an electronic document.
 */
@Service
public class CdcService {

    /**
     * Calculates the CDC for a Documento Electrónico (DE).
     * 
     * @param dType         Type of document (e.g., 1 for Invoice)
     * @param ruc           RUC of the issuer (without check digit)
     * @param dv            RUC check digit
     * @param establishment Establischment code (3 digits)
     * @param point         Point of sale (3 digits)
     * @param number        Document number (7 digits)
     * @param date          Date in format YYYYMMDD
     * @param securityCode  Security code (8 random digits provided by the system)
     * @param transmission  Transmission type (1 - Normal)
     * @return The 44-digit CDC including the Modulo 11 check digit.
     */
    public String generateCdc(String dType, String ruc, String dv, String establishment,
            String point, String number, String emissionType, String date, String securityCode,
            String transmission) {

        // Ensure zero-padding
        String pType = leftPad(dType, 2, '0');
        String pRuc = leftPad(ruc, 8, '0');
        String pEstab = leftPad(establishment, 3, '0');
        String pPoint = leftPad(point, 3, '0');
        String pNumber = leftPad(number, 7, '0');
        String pSecurity = leftPad(securityCode, 9, '0');

        // Concatenate without DV for initial CDC (Total 43 digits)
        String cdcInit = pType + pRuc + dv + pEstab + pPoint + pNumber + emissionType + date + pSecurity + transmission;

        // Calculate Modulo 11 check digit
        int verifier = calculateModulo11(cdcInit);

        return cdcInit + verifier;
    }

    private String leftPad(String value, int length, char pad) {
        if (value.length() >= length)
            return value;
        return String.valueOf(pad).repeat(length - value.length()) + value;
    }

    private int calculateModulo11(String value) {
        int[] weights = { 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8,
                9, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6 };
        int sum = 0;
        int weightIndex = 0;

        // Process from right to left
        for (int i = value.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(value.charAt(i));
            sum += digit * weights[weightIndex++];
        }

        int remainder = sum % 11;
        int digit = 11 - remainder;

        if (digit > 9)
            return 0;
        return digit;
    }
}
