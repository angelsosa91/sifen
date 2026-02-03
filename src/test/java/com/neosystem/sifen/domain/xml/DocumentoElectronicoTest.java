package com.neosystem.sifen.domain.xml;

import org.junit.jupiter.api.Test;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentoElectronicoTest {

    @Test
    void testMarshalling() throws Exception {
        // Construct the object graph
        DocumentoElectronico rDE = new DocumentoElectronico();
        DE de = new DE();
        de.setId("01800015681001001000000122026020311234567891"); // Dummy CDC
        de.setDId("01800015681001001000000122026020311234567891");
        de.setDDvId("1");
        de.setDFecFirma("2026-02-03T10:00:00");

        // Operation
        GOpeDE gOpeDE = new GOpeDE();
        gOpeDE.setITipEmi((short) 1);
        gOpeDE.setDDesTipEmi("Normal");
        gOpeDE.setDCodSeg("123456");
        de.setGOpeDE(gOpeDE);

        // Timbrado
        GTimb gTimb = new GTimb();
        gTimb.setITiDE((short) 1);
        gTimb.setDDesTiDE("Factura Electronica");
        gTimb.setDNumTim(12345678);
        gTimb.setDEst("001");
        gTimb.setDPunExp("001");
        gTimb.setDNumDoc("0000001");
        gTimb.setDFeIniT("2026-01-01");
        de.setGTimb(gTimb);

        // General Data
        GDatGralOpe gDatGralOpe = new GDatGralOpe();
        gDatGralOpe.setDFeEmiDE("2026-02-03T10:00:00");

        GEmis gEmis = new GEmis();
        gEmis.setDRucEm("80001568");
        gEmis.setDDVEmi("1");
        gEmis.setITipCont((short) 2);
        gEmis.setDNomEmi("Empresa Test S.A.");
        gEmis.setDDirEmi("Calle Falsa 123");
        gEmis.setDNumCas("123");
        gEmis.setCDepEmi((short) 1);
        gEmis.setDDesDepEmi("Capital");
        gEmis.setCCiuEmi(1);
        gEmis.setDDesCiuEmi("Asuncion");
        gEmis.setDTelEmi("021123456");
        gEmis.setDEmailE("test@test.com");
        gDatGralOpe.setGEmis(gEmis);

        GDatRec gDatRec = new GDatRec();
        gDatRec.setINatRec((short) 1);
        gDatRec.setITiOpe((short) 1);
        gDatRec.setCPaisRec("PRY");
        gDatRec.setDDesPaisRe("Paraguay");
        gDatRec.setDNomRec("Juan Perez");
        gDatRec.setDDirRec("Calle 1");
        gDatRec.setDNumCasRec(1);
        gDatRec.setCDepRec((short) 1);
        gDatRec.setDDesDepRec("Capital");
        gDatRec.setCCiuRec(1);
        gDatRec.setDDesCiuRec("Asuncion");
        gDatGralOpe.setGDatRec(gDatRec);

        de.setGDatGralOpe(gDatGralOpe);

        // Specific Data (Factura)
        GDtipDE gDtipDE = new GDtipDE();
        GCamFE gCamFE = new GCamFE();
        gCamFE.setIIndPres((short) 1);
        gCamFE.setDDesIndPres("Presencial");
        gDtipDE.setGCamFE(gCamFE);
        de.setGDtipDE(gDtipDE);

        // Items
        GCamItem item = new GCamItem();
        item.setDCodInt("P001");
        item.setDDesProSer("Producto 1");
        item.setCUniMed((short) 77);
        item.setDDesUniMed("Unidad");
        item.setDCantProSer(new BigDecimal("1"));

        GValorItem val = new GValorItem();
        val.setDPUnitPro(new BigDecimal("10000"));
        val.setDTotBruOpeItem(new BigDecimal("10000"));
        GValorRestaItem valRes = new GValorRestaItem();
        valRes.setDTotOpeItem(new BigDecimal("10000"));
        val.setGValorRestaItem(valRes);
        item.setGValorItem(val);

        GCamIVA iva = new GCamIVA();
        iva.setIAfecIVA((short) 1);
        iva.setDDesAfecIVA("Gravado 10");
        iva.setDPropIVA(new BigDecimal("100"));
        iva.setDTasaIVA(10);
        iva.setDLiqIVAItem(new BigDecimal("909"));
        item.setGCamIVA(iva);

        de.setGCamItem(Collections.singletonList(item));

        // Totals
        GTotSub gTotSub = new GTotSub();
        gTotSub.setDTotOpe(new BigDecimal("10000"));
        gTotSub.setDTotGralOpe(new BigDecimal("10000"));
        gTotSub.setDTotIVA(new BigDecimal("909"));
        de.setGTotSub(gTotSub);

        rDE.setDE(de);

        // Marshal
        JAXBContext context = JAXBContext.newInstance(DocumentoElectronico.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(rDE, writer);
        String xml = writer.toString();

        System.out.println(xml);

        assertThat(xml).contains("http://ekuatia.set.gov.py/sifen/xsd");
        assertThat(xml).contains("rDE");
        assertThat(xml).contains("<dVerFor>150</dVerFor>");
        assertThat(xml).contains("Id=\"01800015681001001000000122026020311234567891\"");
        assertThat(xml).contains("<dDesTiDE>Factura Electronica</dDesTiDE>");
    }
}
