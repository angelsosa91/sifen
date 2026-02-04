package com.neosystem.sifen.infrastructure.controllers;

import com.neosystem.sifen.application.services.SifenDeService;
import com.neosystem.sifen.application.services.SifenQueryService;
import com.roshka.sifen.core.beans.response.RespuestaConsultaRUC;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionDE;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sifen/test")
@RequiredArgsConstructor
public class SifenTestController {

    private final SifenQueryService queryService;
    private final SifenDeService deService;

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<RespuestaConsultaRUC> testRuc(@PathVariable String ruc) {
        try {
            RespuestaConsultaRUC response = queryService.consultaRuc(ruc);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/de/sample")
    public ResponseEntity<RespuestaRecepcionDE> testDeSample() {
        try {
            RespuestaRecepcionDE response = deService.submitSampleInvoice();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
