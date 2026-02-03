package com.neosystem.sifen;

import com.neosystem.sifen.infrastructure.config.SifenProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.net.ssl.SSLContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CertificateLoadingTest {

    @Autowired
    private SSLContext sifenSSLContext;

    @Autowired
    private SifenProperties sifenProperties;

    @Test
    void testCertificateLoading() {
        assertNotNull(sifenSSLContext, "SSLContext should not be null");
        System.out.println("Certificate loaded successfully from: " + sifenProperties.getCertificate().getPath());
    }
}
