package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "gCamFE", "gCamNC", "gCamNRE", "gCamCond" })
public class GDtipDE {

    @XmlElement(name = "gCamFE")
    private GCamFE gCamFE; // For Invoices

    @XmlElement(name = "gCamNC")
    private Object gCamNC; // Credit Note (Placeholder)

    @XmlElement(name = "gCamNRE")
    private Object gCamNRE; // Remission Note (Placeholder)

    @XmlElement(name = "gCamCond")
    private Object gCamCond; // Condition (Placeholder)
}
