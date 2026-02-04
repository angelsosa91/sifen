package com.neosystem.sifen.application.services;

import com.roshka.sifen.Sifen;
import com.roshka.sifen.core.beans.DocumentoElectronico;
import com.roshka.sifen.core.beans.response.RespuestaRecepcionDE;
import com.roshka.sifen.core.exceptions.SifenException;
import com.roshka.sifen.core.fields.request.de.*;
import com.roshka.sifen.core.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SifenDeService {

    public RespuestaRecepcionDE submitSampleInvoice() throws SifenException {
        log.info("Generating sample Electronic Document (DE)...");
        DocumentoElectronico de = createSampleFactura();

        log.info("Submitting DE to SIFEN...");
        RespuestaRecepcionDE response = Sifen.recepcionDE(de);

        log.info("DE Submission Result: {} - {}", response.getdCodRes(), response.getdMsgRes());
        return response;
    }

    private DocumentoElectronico createSampleFactura() throws SifenException {
        DocumentoElectronico de = new DocumentoElectronico();
        de.setdFecFirma(LocalDateTime.now());
        de.setdSisFact((short) 1); // Sistema del cliente

        // 1. gOpeDE - Campos de operación
        TgOpeDE gOpeDE = new TgOpeDE();
        gOpeDE.setiTipEmi(TTipEmi.NORMAL);
        gOpeDE.setdCodSeg("0000001");
        de.setgOpeDE(gOpeDE);

        // 2. gTimb - Datos del timbrado
        TgTimb gTimb = new TgTimb();
        gTimb.setiTiDE(TTiDE.FACTURA_ELECTRONICA);
        gTimb.setdNumTim(12345678);
        gTimb.setdEst("001");
        gTimb.setdPunExp("001");
        gTimb.setdNumDoc("0000001");
        gTimb.setdFeIniT(LocalDate.now().minusDays(30));
        de.setgTimb(gTimb);

        // 3. gDatGralOpe - Datos generales de la operación
        TdDatGralOpe gDatGralOpe = new TdDatGralOpe();
        gDatGralOpe.setdFeEmiDE(LocalDateTime.now());

        // 3.1 gEmis - Emisor
        TgEmis gEmis = new TgEmis();
        gEmis.setdRucEm("80094165");
        gEmis.setdDVEmi("1");
        gEmis.setiTipCont(TiTipCont.PERSONA_JURIDICA);
        gEmis.setdNomEmi("NEO SYSTEM PARAGUAY SOCIEDAD ANONIMA");
        gEmis.setdDirEmi("AVDA. ESPAÑA 123");
        gEmis.setdNumCas("123");
        gEmis.setcDepEmi(TDepartamento.CAPITAL);
        gEmis.setcDisEmi((short) 1);
        gEmis.setcCiuEmi(1);
        gEmis.setdTelEmi("021123456");
        gEmis.setdEmailE("info@neosystem.com.py");

        List<TgActEco> gActEcoList = new ArrayList<>();
        TgActEco actEco = new TgActEco();
        actEco.setcActEco("62010");
        actEco.setdDesActEco("SERVICIOS DE TECNOLOGIA");
        gActEcoList.add(actEco);
        gEmis.setgActEcoList(gActEcoList);
        gDatGralOpe.setgEmis(gEmis);

        // 3.2 gDatRec - Receptor
        TgDatRec gDatRec = new TgDatRec();
        gDatRec.setiNatRec(TiNatRec.CONTRIBUYENTE);
        gDatRec.setiTiOpe(TiTiOpe.B2B);
        gDatRec.setcPaisRec(PaisType.PRY);
        gDatRec.setiTiContRec(TiTipCont.PERSONA_JURIDICA);
        gDatRec.setdRucRec("80001234");
        gDatRec.setdDVRec((short) 1);
        gDatRec.setdNomRec("CLIENTE DE PRUEBA SA");
        gDatGralOpe.setgDatRec(gDatRec);

        // 3.3 gOpeCom - Operación Comercial
        TgOpeCom gOpeCom = new TgOpeCom();
        gOpeCom.setiTipTra(TTipTra.PRESTACION_SERVICIOS);
        gOpeCom.setiTImp(TTImp.IVA);
        gOpeCom.setcMoneOpe(CMondT.PYG);
        gDatGralOpe.setgOpeCom(gOpeCom);

        de.setgDatGralOpe(gDatGralOpe);

        // 4. gDtipDE - Detalles específicos del DE
        TgDtipDE gDtipDE = new TgDtipDE();

        // 4.1 gCamItem - Ítems
        List<TgCamItem> gCamItemList = new ArrayList<>();
        TgCamItem item = new TgCamItem();
        item.setdCodInt("P001");
        item.setdDesProSer("SERVICIO DE CONSULTORIA");
        item.setcUniMed(TcUniMed.UNI);
        item.setdCantProSer(BigDecimal.ONE);

        TgValorItem gValorItem = new TgValorItem();
        gValorItem.setdPUniProSer(new BigDecimal("100000"));

        TgValorRestaItem gValorRestaItem = new TgValorRestaItem();
        gValorRestaItem.setdDescItem(BigDecimal.ZERO);
        gValorRestaItem.setdDescGloItem(BigDecimal.ZERO);
        gValorRestaItem.setdAntPreUniIt(BigDecimal.ZERO);
        gValorRestaItem.setdAntGloPreUniIt(BigDecimal.ZERO);
        gValorItem.setgValorRestaItem(gValorRestaItem);

        item.setgValorItem(gValorItem);

        TgCamIVA gCamIVA = new TgCamIVA();
        gCamIVA.setiAfecIVA(TiAfecIVA.GRAVADO);
        gCamIVA.setdPropIVA(new BigDecimal("100"));
        gCamIVA.setdTasaIVA(new BigDecimal("10"));
        item.setgCamIVA(gCamIVA);

        gCamItemList.add(item);
        gDtipDE.setgCamItemList(gCamItemList);

        // 4.2 gCamCond - Condición de pago
        TgCamCond gCamCond = new TgCamCond();
        gCamCond.setiCondOpe(TiCondOpe.CONTADO);

        TgPaConEIni gPaConEIni = new TgPaConEIni();
        gPaConEIni.setiTiPago(TiTiPago.EFECTIVO);
        gPaConEIni.setdMonTiPag(new BigDecimal("100000"));
        gCamCond.setgPaConEIniList(Collections.singletonList(gPaConEIni));
        gDtipDE.setgCamCond(gCamCond);

        de.setgDtipDE(gDtipDE);

        // 5. gTotSub - Totales
        TgTotSub gTotSub = new TgTotSub();
        de.setgTotSub(gTotSub);

        return de;
    }
}
