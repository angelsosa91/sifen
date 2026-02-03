package com.neosystem.sifen.infrastructure.adapters.sifen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class XmlSigningServiceTest {

    @Autowired
    private XmlSigningService xmlSigningService;

    @Test
    void testSignDocument() throws Exception {
        // Create a dummy XML document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        
        Element root = doc.createElementNS("http://www.sifen.gov.py/schema", "rDE");
        doc.appendChild(root);
        
        Element dVerFor = doc.createElement("dVerFor");
        dVerFor.setTextContent("150");
        root.appendChild(dVerFor);

        // Sign the document
        xmlSigningService.signDocument(doc);

        // Verify Signature element exists
        assertNotNull(doc.getElementsByTagName("Signature").item(0));

        // Convert to string for visual verification
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        String output = writer.toString();

        System.out.println("Signed XML Output:");
        System.out.println(output);
        
        assertTrue(output.contains("Signature"));
        assertTrue(output.contains("DigestValue"));
        assertTrue(output.contains("SignatureValue"));
    }
}
