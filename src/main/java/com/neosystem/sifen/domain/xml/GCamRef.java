package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "iTipRef", "dIdRef", "dNumCodRef", "dNumDocRef", "dNomRef", "dFecRef", "iTipDocRef", "dDesTipDocRef"
})
public class GCamRef {

    @XmlElement(name = "iTipRef", required = true)
    private short iTipRef; // 1=CDC, 2=Preimpresa, 3=Constancia

    @XmlElement(name = "dIdRef")
    private String dIdRef; // CDC of referenced document

    @XmlElement(name = "dNumCodRef")
    private String dNumCodRef; // Authentication code if CDC not available

    @XmlElement(name = "dNumDocRef")
    private String dNumDocRef; // Document number if CDC not available

    @XmlElement(name = "dNomRef")
    private String dNomRef; // Name of issuer if document from other system

    @XmlElement(name = "dFecRef")
    private String dFecRef; // Emission date

    @XmlElement(name = "iTipDocRef")
    private String iTipDocRef; // Type (if pre-printed)

    @XmlElement(name = "dDesTipDocRef")
    private String dDesTipDocRef;
}
