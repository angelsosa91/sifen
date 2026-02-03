package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "iTiDE", "dDesTiDE", "dNumTim", "dEst", "dPunExp", "dNumDoc",
        "dSerieNum", "dFeIniT"
})
public class GTimb {

    @XmlElement(name = "iTiDE", required = true)
    private short iTiDE; // 1=Factura Electronica, 4=Nota Credito, etc.

    @XmlElement(name = "dDesTiDE", required = true)
    private String dDesTiDE;

    @XmlElement(name = "dNumTim", required = true)
    private Integer dNumTim; // Timbrado number (8 digits)

    @XmlElement(name = "dEst", required = true)
    private String dEst; // Establecimiento (3 digits)

    @XmlElement(name = "dPunExp", required = true)
    private String dPunExp; // Punto de expedicion (3 digits)

    @XmlElement(name = "dNumDoc", required = true)
    private String dNumDoc; // Document number (7 digits)

    @XmlElement(name = "dSerieNum")
    private String dSerieNum; // Optional serie

    @XmlElement(name = "dFeIniT", required = true)
    private String dFeIniT; // Start date of Timbrado (ISO 8601 Date)
}
