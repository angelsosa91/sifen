package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "gCamFE", "gCamAE", "gCamNCDE", "gCamRem", "gCamCond" })
public class GDtipDE {

    @XmlElement(name = "gCamFE")
    private GCamFE gCamFE; // For Invoices

    @XmlElement(name = "gCamAE")
    private GCamAE gCamAE; // Autofactura

    @XmlElement(name = "gCamNCDE")
    private GCamNCDE gCamNCDE; // Credit/Debit Note

    @XmlElement(name = "gCamRem")
    private GCamRem gCamRem; // Remission Note

    @XmlElement(name = "gCamCond")
    private GCamCond gCamCond; // Condition (Contado/Credito)
}
