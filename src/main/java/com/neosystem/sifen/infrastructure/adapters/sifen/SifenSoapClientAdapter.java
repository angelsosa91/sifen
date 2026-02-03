package com.neosystem.sifen.infrastructure.adapters.sifen;

import com.neosystem.sifen.infrastructure.config.SifenProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import jakarta.xml.soap.MimeHeaders;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.WebServiceMessage;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Service
@RequiredArgsConstructor
public class SifenSoapClientAdapter {

    private final WebServiceTemplate webServiceTemplate;
    private final SifenProperties sifenProperties;

    public String recibe(String signedXml, String id) {
        String url = "https://sifen-test.set.gov.py/de/ws/transmision/recepcion-de"; // Standard recibe URL

        // Remove XML Declaration if present to avoid nesting issues
        signedXml = signedXml.replaceAll("\\<\\?xml.+?\\?\\>", "").trim();

        String requestBody = "<rEnviDe xmlns=\"http://ekuatia.set.gov.py/sifen/xsd\">" +
                "  <dId>" + id + "</dId>" +
                "  <xDe>" + signedXml + "</xDe>" +
                "</rEnviDe>";

        StringWriter writer = new StringWriter();
        StreamSource source = new StreamSource(new StringReader(requestBody));
        StreamResult result = new StreamResult(writer);

        webServiceTemplate.sendSourceAndReceiveToResult(url, source, new WebServiceMessageCallback() {
            @Override
            public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                if (message instanceof SaajSoapMessage) {
                    SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
                    MimeHeaders headers = saajSoapMessage.getSaajMessage().getMimeHeaders();
                    headers.setHeader("User-Agent", "J-Sifen-Client/1.0 (Java)");
                    headers.setHeader("Content-Type",
                            "application/soap+xml; charset=utf-8; action=\"http://ekuatia.set.gov.py/sifen/xsd/rEnviDe\"");
                }
            }
        }, result);

        return writer.toString();
    }

    public String consultaRuc(String ruc) {
        String url = "https://sifen-test.set.gov.py/de/ws/consultas/consulta-ruc";

        // SIFEN V150: Consulta RUC structure
        String requestBody = "<rConsRuc xmlns=\"http://ekuatia.set.gov.py/sifen/xsd\">" +
                "  <dVerFor>150</dVerFor>" +
                "  <dId>1</dId>" +
                "  <dRucRec>" + ruc + "</dRucRec>" +
                "</rConsRuc>";

        StringWriter writer = new StringWriter();
        StreamSource source = new StreamSource(new StringReader(requestBody));
        StreamResult result = new StreamResult(writer);

        webServiceTemplate.sendSourceAndReceiveToResult(url, source, new WebServiceMessageCallback() {
            @Override
            public void doWithMessage(org.springframework.ws.WebServiceMessage message)
                    throws java.io.IOException, javax.xml.transform.TransformerException {
                if (message instanceof SaajSoapMessage) {
                    SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
                    try {
                        MimeHeaders headers = saajSoapMessage.getSaajMessage().getMimeHeaders();
                        headers.setHeader("User-Agent", "J-Sifen-Client/1.0 (Java)");
                        // Ensure SOAP 1.2 Content-Type
                        headers.setHeader("Content-Type",
                                "application/soap+xml; charset=utf-8; action=\"http://ekuatia.set.gov.py/sifen/xsd/rConsRuc\"");
                    } catch (Exception e) {
                        e.printStackTrace(); // Minimal handling for test
                    }
                }
            }
        }, result);

        return writer.toString();
    }
}
