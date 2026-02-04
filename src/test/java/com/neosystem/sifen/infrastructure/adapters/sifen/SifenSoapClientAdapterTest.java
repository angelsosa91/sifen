package com.neosystem.sifen.infrastructure.adapters.sifen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SifenSoapClientAdapterTest {

    @Autowired
    private SifenSoapClientAdapter sifenSoapClientAdapter;

    @Autowired
    private com.neosystem.sifen.infrastructure.adapters.sifen.XmlSigningService xmlSigningService;

    @Test
    void testRecibe() throws Exception {
        // 1. Create DE
        com.neosystem.sifen.domain.xml.DocumentoElectronico rDE = new com.neosystem.sifen.domain.xml.DocumentoElectronico();
        com.neosystem.sifen.domain.xml.DE de = new com.neosystem.sifen.domain.xml.DE();
        String cdc = "01800015681001001000000122026020311234567891";
        de.setId(cdc);
        de.setDId(cdc);
        de.setDDvId("1");
        de.setDFecFirma("2026-02-03T10:00:00");
        de.setDSisFact("1");

        // Simple minimal fields to pass schema validation (expand as needed based on
        // manual)
        com.neosystem.sifen.domain.xml.GOpeDE gOpeDE = new com.neosystem.sifen.domain.xml.GOpeDE();
        gOpeDE.setITipEmi((short) 1);
        gOpeDE.setDDesTipEmi("Normal");
        gOpeDE.setDCodSeg("123456");
        de.setGOpeDE(gOpeDE);

        com.neosystem.sifen.domain.xml.GTimb gTimb = new com.neosystem.sifen.domain.xml.GTimb();
        gTimb.setITiDE((short) 1);
        gTimb.setDDesTiDE("Factura");
        gTimb.setDNumTim(12345678);
        gTimb.setDEst("001");
        gTimb.setDPunExp("001");
        gTimb.setDNumDoc("0000001");
        gTimb.setDFeIniT("2026-01-01");
        de.setGTimb(gTimb);

        com.neosystem.sifen.domain.xml.GDatGralOpe gDat = new com.neosystem.sifen.domain.xml.GDatGralOpe();
        gDat.setDFeEmiDE("2026-02-03T10:00:00");
        com.neosystem.sifen.domain.xml.GEmis gEmis = new com.neosystem.sifen.domain.xml.GEmis();
        gEmis.setDRucEm("80001568");
        gEmis.setDDVEmi("1");
        gEmis.setITipCont((short) 2);
        gEmis.setDNomEmi("Test S.A.");
        gEmis.setDDirEmi("Calle 1");
        gEmis.setDNumCas("1");
        gEmis.setCDepEmi((short) 1);
        gEmis.setDDesDepEmi("Capital");
        gEmis.setCCiuEmi(1);
        gEmis.setDDesCiuEmi("Asu");
        gEmis.setDTelEmi("123");
        gEmis.setDEmailE("a@a.com");
        gDat.setGEmis(gEmis);
        com.neosystem.sifen.domain.xml.GDatRec gRec = new com.neosystem.sifen.domain.xml.GDatRec();
        gRec.setINatRec((short) 1);
        gRec.setITiOpe((short) 1);
        gRec.setCPaisRec("PRY");
        gRec.setDDesPaisRe("Paraguay");
        gRec.setDNomRec("Cliente");
        gRec.setDDirRec("Calle 2");
        gRec.setDNumCasRec(1);
        gRec.setCDepRec((short) 1);
        gRec.setDDesDepRec("Capital");
        gRec.setCCiuRec(1);
        gRec.setDDesCiuRec("Asu");
        gDat.setGDatRec(gRec);
        de.setGDatGralOpe(gDat);

        com.neosystem.sifen.domain.xml.GDtipDE gDtip = new com.neosystem.sifen.domain.xml.GDtipDE();
        com.neosystem.sifen.domain.xml.GCamFE gCamFe = new com.neosystem.sifen.domain.xml.GCamFE();
        gCamFe.setIIndPres((short) 1);
        gCamFe.setDDesIndPres("Pres");
        gDtip.setGCamFE(gCamFe);
        de.setGDtipDE(gDtip);

        com.neosystem.sifen.domain.xml.GTotSub gTot = new com.neosystem.sifen.domain.xml.GTotSub();
        gTot.setDTotOpe(new java.math.BigDecimal(100));
        gTot.setDTotGralOpe(new java.math.BigDecimal(100));
        gTot.setDTotIVA(java.math.BigDecimal.ZERO);
        de.setGTotSub(gTot);

        com.neosystem.sifen.domain.xml.GCamItem item = new com.neosystem.sifen.domain.xml.GCamItem();
        item.setDCodInt("1");
        item.setDDesProSer("Prod");
        item.setCUniMed((short) 77);
        item.setDDesUniMed("Uni");
        item.setDCantProSer(java.math.BigDecimal.ONE);
        com.neosystem.sifen.domain.xml.GValorItem val = new com.neosystem.sifen.domain.xml.GValorItem();
        val.setDPUnitPro(new java.math.BigDecimal(100));
        val.setDTotBruOpeItem(new java.math.BigDecimal(100));
        com.neosystem.sifen.domain.xml.GValorRestaItem valRes = new com.neosystem.sifen.domain.xml.GValorRestaItem();
        valRes.setDTotOpeItem(new java.math.BigDecimal(100));
        val.setGValorRestaItem(valRes);
        item.setGValorItem(val);
        com.neosystem.sifen.domain.xml.GCamIVA iva = new com.neosystem.sifen.domain.xml.GCamIVA();
        iva.setIAfecIVA((short) 3);
        iva.setDDesAfecIVA("Exento");
        iva.setDPropIVA(java.math.BigDecimal.ZERO);
        iva.setDTasaIVA(0);
        iva.setDLiqIVAItem(java.math.BigDecimal.ZERO);
        item.setGCamIVA(iva);
        de.setGCamItem(java.util.Collections.singletonList(item));

        rDE.setDE(de);

        // 2. Marshal to Document
        jakarta.xml.bind.JAXBContext context = jakarta.xml.bind.JAXBContext
                .newInstance(com.neosystem.sifen.domain.xml.DocumentoElectronico.class);
        jakarta.xml.bind.Marshaller marshaller = context.createMarshaller();
        javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        org.w3c.dom.Document doc = dbf.newDocumentBuilder().newDocument();
        marshaller.marshal(rDE, doc);

        // 3. Sign
        xmlSigningService.signDocument(doc);

        // 4. Convert back to String
        javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = tf.newTransformer();
        java.io.StringWriter writer = new java.io.StringWriter();
        transformer.transform(new javax.xml.transform.dom.DOMSource(doc),
                new javax.xml.transform.stream.StreamResult(writer));
        String signedXml = writer.toString();

        System.out.println("Signed XML: " + signedXml);

        // 5. Send
        String response = sifenSoapClientAdapter.recibe(signedXml, "1");
        System.out.println("Response: " + response);
    }

    @Test
    void testConsultaRuc() {
        // RUC Proveedores del Exterior provided by user
        String ruc = "99999901";

        try {
            String response = sifenSoapClientAdapter.consultaRuc(ruc);
            System.out.println("SIFEN RUC Query Response:");
            System.out.println(response);

            assertNotNull(response);
            assertTrue(response.contains("rResEnviConsRuc") || response.contains("gResProc"),
                    "Response should contain SIFEN result tags");
        } catch (Exception e) {
            // If it fails due to network/certificate, print why
            System.err.println("SOAP Call failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    void testConsultaDe() {
        // Test with a dummy CDC (44 chars) to verify transport (will likely return
        // failure from SIFEN but 200 OK HTTP)
        String dummyCdc = "01800015681001001000000122026020311234567891";
        String response = sifenSoapClientAdapter.consultaDe(dummyCdc);

        System.out.println("Consulta DE Response: " + response);
        assertThat(response).isNotNull();
        assertThat(response).doesNotContain("Error: No response stream");
        // We expect a valid SOAP response
        assertThat(response).contains("ns2:rEnviConsDeResponse");
    }

    @Test
    void testRecibeEvento() {
        // Mock a signed event XML (gGroupGesEve)
        String signedEventXml = "<gGroupGesEve xmlns=\"http://ekuatia.set.gov.py/sifen/xsd\">" +
                "<rGesEve><dVerFor>150</dVerFor><dId>1</dId><dFecFirma>2026-02-03T12:00:00</dFecFirma></rGesEve>" +
                "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"\"/><SignatureMethod Algorithm=\"\"/><Reference><DigestMethod Algorithm=\"\"/><DigestValue>abc</DigestValue></Reference></SignedInfo><SignatureValue>xyz</SignatureValue></Signature>"
                +
                "</gGroupGesEve>";

        String response = sifenSoapClientAdapter.recibeEvento(signedEventXml, "1");

        System.out.println("Recibe Evento Response: " + response);
        assertThat(response).isNotNull();
        assertThat(response).doesNotContain("Error: No response stream");
        // We expect a valid SOAP response
        assertThat(response).contains("ns2:rRetEnviEventoDe");
    }
}
