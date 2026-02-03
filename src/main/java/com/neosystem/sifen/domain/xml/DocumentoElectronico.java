package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rDE", namespace = "http://ekuatia.set.gov.py/sifen/xsd")
@XmlType(propOrder = { "dVerFor", "DE", "signature" })
public class DocumentoElectronico {

    @XmlElement(name = "dVerFor", required = true)
    private String dVerFor = "150";

    @XmlElement(name = "DE", required = true)
    private DE DE;

    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    private Object signature; // Placeholder for now, will be populated by signing service
}
