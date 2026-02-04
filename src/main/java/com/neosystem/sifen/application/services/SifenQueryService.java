package com.neosystem.sifen.application.services;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.response.RespuestaConsultaRUC;
import com.roshka.sifen.core.exceptions.SifenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SifenQueryService {

    public RespuestaConsultaRUC consultaRuc(String ruc) throws SifenException {
        log.info("Querying RUC: {}", ruc);
        RespuestaConsultaRUC response = Sifen.consultaRUC(ruc);
        log.info("RUC Query Status: {}", response.getCodigoEstado());
        if (response.getxContRUC() != null) {
            log.info("Taxpayer Name: {}", response.getxContRUC().getdRazCons());
        } else {
            log.warn("No taxpayer data returned for RUC: {}", ruc);
        }
        return response;
    }
}
