package com.neosystem.sifen.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sifen")
public class SifenProperties {
    private Certificate certificate;
    private Endpoints endpoints;

    @Data
    public static class Certificate {
        private String path;
        private String password;
    }

    @Data
    public static class Endpoints {
        private Environment test;
        private Environment production;
    }

    @Data
    public static class Environment {
        private String recibe;
        private String consulta;
        private String evento;
    }
}
