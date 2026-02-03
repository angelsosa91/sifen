package com.neosystem.sifen.infrastructure.adapters.sifen;

import com.neosystem.sifen.infrastructure.config.SifenProperties;
import lombok.RequiredArgsConstructor;
import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

@Service
@RequiredArgsConstructor
public class XmlSigningService {

    private final SifenProperties sifenProperties;

    @PostConstruct
    public void init() {
        try {
            Init.init();
            ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing XML Security", e);
        }
    }

    public void signDocument(Document doc) throws Exception {
        char[] password = sifenProperties.getCertificate().getPassword().toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(sifenProperties.getCertificate().getPath())) {
            ks.load(fis, password);
        }

        String alias = null;
        Enumeration<String> aliases = ks.aliases();
        while (aliases.hasMoreElements()) {
            alias = aliases.nextElement();
            if (ks.isKeyEntry(alias)) break;
        }

        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password);
        X509Certificate cert = (X509Certificate) ks.getCertificate(alias);

        // XML Signature object
        XMLSignature signature = new XMLSignature(doc, null, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA256);

        // Add the signature element to the document
        Element root = doc.getDocumentElement();
        root.appendChild(signature.getElement());

        // Transforms
        Transforms transforms = new Transforms(doc);
        transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
        transforms.addTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS);

        // Add reference to the document (empty URI means the whole document)
        signature.addDocument("", transforms, "http://www.w3.org/2001/04/xmlenc#sha256");

        // Add KeyInfo
        signature.addKeyInfo(cert);
        signature.addKeyInfo(cert.getPublicKey());

        // Sign
        signature.sign(privateKey);
    }
}
