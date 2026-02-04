package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "iNatVen", "dDesNatVen", "iTipIDVen", "dDTipIDVen", "dNumIDVen", "dNomVen",
        "dDirVen", "dNumCasVen", "dDirProvVen", "cDepVen", "dDesDepVen", "cCiuVen", "dDesCiuVen"
})
public class GCamAE {

    @XmlElement(name = "iNatVen", required = true)
    private short iNatVen; // 1=No Contribuyente, 2=Extranjero

    @XmlElement(name = "dDesNatVen", required = true)
    private String dDesNatVen;

    @XmlElement(name = "iTipIDVen", required = true)
    private short iTipIDVen;

    @XmlElement(name = "dDTipIDVen", required = true)
    private String dDTipIDVen;

    @XmlElement(name = "dNumIDVen", required = true)
    private String dNumIDVen;

    @XmlElement(name = "dNomVen", required = true)
    private String dNomVen;

    @XmlElement(name = "dDirVen", required = true)
    private String dDirVen;

    @XmlElement(name = "dNumCasVen", required = true)
    private Integer dNumCasVen;

    @XmlElement(name = "dDirProvVen")
    private String dDirProvVen;

    @XmlElement(name = "cDepVen", required = true)
    private short cDepVen;

    @XmlElement(name = "dDesDepVen", required = true)
    private String dDesDepVen;

    @XmlElement(name = "cCiuVen", required = true)
    private short cCiuVen;

    @XmlElement(name = "dDesCiuVen", required = true)
    private String dDesCiuVen;
}
