package com.neosystem.sifen.infrastructure.config;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.SifenConfig;
import com.roshka.sifen.core.exceptions.SifenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Slf4j
@Configuration
public class SifenLibraryConfig {

    @Value("${sifen.certificate.path}")
    private String certPath;

    @Value("${sifen.certificate.password}")
    private String certPassword;

    @Value("${sifen.test-data.id-csc}")
    private String idCsc;

    @Value("${sifen.test-data.csc}")
    private String csc;

    @Bean
    public SifenConfig sifenConfig() throws SifenException {
        log.info("Initializing SIFEN Configuration with cert: {}", certPath);

        // Ensure the path is absolute for the library
        File certFile = new File(certPath);
        String absolutePath = certFile.getAbsolutePath();

        SifenConfig config = new SifenConfig();
        config.setAmbiente(SifenConfig.TipoAmbiente.DEV); // Default to DEV for now
        config.setUsarCertificadoCliente(true);
        config.setCertificadoCliente(absolutePath);
        config.setContrasenaCertificadoCliente(certPassword);
        config.setTipoCertificadoCliente(SifenConfig.TipoCertificadoCliente.PFX);
        config.setIdCSC(idCsc);
        config.setCSC(csc);

        // Explicit paths according to manual
        config.setUrlBase("https://sifen-test.set.gov.py");
        config.setPathConsultaRUC("/sifen/ws/consulta/consulta-ruc");
        config.setPathRecibe("/de/ws/recepcion/recepcion");
        config.setPathRecibeLote("/de/ws/recepcion/lote");
        config.setPathEvento("/de/ws/evento/evento");
        config.setPathConsulta("/de/ws/consulta/consulta");
        config.setPathConsultaLote("/de/ws/consulta/consulta-lote");

        // Set as global config for the library
        Sifen.setSifenConfig(config);

        return config;
    }
}
