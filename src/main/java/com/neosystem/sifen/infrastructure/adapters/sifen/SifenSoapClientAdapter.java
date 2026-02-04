package com.neosystem.sifen.infrastructure.adapters.sifen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SifenSoapClientAdapter {

    private final javax.net.ssl.SSLContext sifenSSLContext;

    public String recibe(String signedXml, String id) {
        String url = "https://sifen-test.set.gov.py/de/ws/sync/recibe.wsdl";

        // Remove XML Declaration
        signedXml = signedXml.replaceAll("\\<\\?xml.+?\\?\\>", "").trim();

        String requestBody = "<rEnviDe xmlns=\"http://ekuatia.set.gov.py/sifen/xsd\">" +
                "  <dId>" + id + "</dId>" +
                "  <xDe>" + signedXml + "</xDe>" +
                "</rEnviDe>";

        return sendRawSoapRequest(url, requestBody);
    }

    public String consultaRuc(String ruc) {
        String url = "https://sifen-test.set.gov.py/de/ws/consultas/consulta-ruc.wsdl";

        String requestBody = "<rConsRuc xmlns=\"http://ekuatia.set.gov.py/sifen/xsd\">" +
                "  <dVerFor>150</dVerFor>" +
                "  <dId>1</dId>" +
                "  <dRucRec>" + ruc + "</dRucRec>" +
                "</rConsRuc>";

        return sendRawSoapRequest(url, requestBody);
    }

    public String consultaDe(String cdc) {
        String url = "https://sifen-test.set.gov.py/de/ws/consultas/consulta.wsdl";

        String requestBody = "<rEnviConsDeRequest xmlns=\"http://ekuatia.set.gov.py/sifen/xsd\">" +
                "  <dId>1</dId>" +
                "  <dCDC>" + cdc + "</dCDC>" +
                "</rEnviConsDeRequest>";

        return sendRawSoapRequest(url, requestBody);
    }

    public String recibeEvento(String signedEventXml, String id) {
        String url = "https://sifen-test.set.gov.py/de/ws/eventos/evento.wsdl";

        // Remove XML Declaration
        signedEventXml = signedEventXml.replaceAll("\\<\\?xml.+?\\?\\>", "").trim();

        String requestBody = "<rEnviEventoDe xmlns=\"http://ekuatia.set.gov.py/sifen/xsd\">" +
                "  <dId>" + id + "</dId>" +
                "  <dEvReg>" +
                signedEventXml +
                "  </dEvReg>" +
                "</rEnviEventoDe>";

        return sendRawSoapRequest(url, requestBody);
    }

    private String sendRawSoapRequest(String urlString, String bodyContent) {
        javax.net.ssl.HttpsURLConnection connection = null;
        try {
            java.net.URL url = java.net.URI.create(urlString).toURL();
            connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sifenSSLContext.getSocketFactory());
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(45000);

            // Headers from rshk-jsifenlib
            connection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
            connection.setRequestProperty("User-Agent", "rshk-jsifenlib/1.0 (LVEA)");

            // Build Full SOAP Envelope
            String soapEnvelope = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<env:Envelope xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">" +
                    "<env:Header/>" +
                    "<env:Body>" +
                    bodyContent +
                    "</env:Body>" +
                    "</env:Envelope>";

            try (java.io.OutputStream os = connection.getOutputStream()) {
                byte[] input = soapEnvelope.getBytes(java.nio.charset.StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int status = connection.getResponseCode();
            java.io.InputStream is = (status >= 400) ? connection.getErrorStream() : connection.getInputStream();

            if (is == null) {
                return "Error: No response stream (Status: " + status + ")";
            }

            try (java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.InputStreamReader(is, java.nio.charset.StandardCharsets.UTF_8))) {
                return br.lines().collect(java.util.stream.Collectors.joining("\n"));
            }

        } catch (java.io.IOException e) {
            throw new RuntimeException("Error communicating with SIFEN: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
