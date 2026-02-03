package com.neosystem.sifen.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CdcServiceTest {

    @Autowired
    private CdcService cdcService;

    @Test
    void testGenerateCdc() {
        // Example data (to be verified with real SIFEN examples)
        String dType = "01";
        String ruc = "80001234";
        String dv = "1";
        String establishment = "001";
        String point = "001";
        String number = "0000001";
        String date = "20240203";
        String securityCode = "12345678";
        String transmission = "1";

        String cdc = cdcService.generateCdc(dType, ruc, dv, establishment, point, number, date, securityCode, transmission);
        
        assertEquals(44, cdc.length(), "CDC must be 44 digits long");
        System.out.println("Generated CDC: " + cdc);
    }
}
