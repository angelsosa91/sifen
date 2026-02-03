package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "iIndPres", "dDesIndPres", "dFecEm", "gCompPub" })
public class GCamFE {

    @XmlElement(name = "iIndPres", required = true)
    private short iIndPres; // 1=Presencial, 2=Electronica

    @XmlElement(name = "dDesIndPres", required = true)
    private String dDesIndPres;

    @XmlElement(name = "dFecEm")
    private String dFecEm; // Date of emission if different

    @XmlElement(name = "gCompPub")
    private Object gCompPub; // Public Purchases
}
