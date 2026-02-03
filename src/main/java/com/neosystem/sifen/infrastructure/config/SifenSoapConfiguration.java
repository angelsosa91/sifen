package com.neosystem.sifen.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

import javax.net.ssl.SSLContext;

@Configuration
public class SifenSoapConfiguration {

    @Bean
    public SaajSoapMessageFactory messageFactory() {
        SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion(SoapVersion.SOAP_12);
        return messageFactory;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(SaajSoapMessageFactory messageFactory, SSLContext sifenSSLContext) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory);

        // Configure mTLS
        HttpsUrlConnectionMessageSender messageSender = new HttpsUrlConnectionMessageSender();
        messageSender.setSslSocketFactory(sifenSSLContext.getSocketFactory());

        webServiceTemplate.setMessageSender(messageSender);

        return webServiceTemplate;
    }
}
