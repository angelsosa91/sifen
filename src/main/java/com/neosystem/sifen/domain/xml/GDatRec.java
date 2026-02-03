package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "iNatRec", "iTiOpe", "cPaisRec", "dDesPaisRe", "iTiContRec", "dRucRec", "dDVRec", "iTipIDRec",
        "dNumIDRec", "dNomRec", "dNomFanRec", "dDirRec", "dNumCasRec", "cDepRec", "dDesDepRec",
        "cDisRec", "dDesDisRec", "cCiuRec", "dDesCiuRec", "dTelRec", "dCelRec", "dEmailRec", "dCodCliente"
})
public class GDatRec {

    @XmlElement(name = "iNatRec", required = true)
    private short iNatRec; // 1=Contribuyente, 2=No Contribuyente

    @XmlElement(name = "iTiOpe", required = true)
    private short iTiOpe; // 1=B2B, 2=B2C, etc.

    @XmlElement(name = "cPaisRec", required = true)
    private String cPaisRec; // PRY

    @XmlElement(name = "dDesPaisRe", required = true)
    private String dDesPaisRe; // Paraguay

    @XmlElement(name = "iTiContRec")
    private Short iTiContRec; // 1=PF, 2=PJ (Required if iNatRec=1)

    @XmlElement(name = "dRucRec")
    private String dRucRec; // RUC (Required if iNatRec=1)

    @XmlElement(name = "dDVRec")
    private Integer dDVRec; // DV (Required if iNatRec=1)

    @XmlElement(name = "iTipIDRec")
    private Short iTipIDRec; // Cedula, Pasaporte, etc (If iNatRec=2)

    @XmlElement(name = "dNumIDRec")
    private String dNumIDRec; // ID Number (If iNatRec=2)

    @XmlElement(name = "dNomRec", required = true)
    private String dNomRec; // Name

    @XmlElement(name = "dNomFanRec")
    private String dNomFanRec;

    @XmlElement(name = "dDirRec", required = true)
    private String dDirRec;

    @XmlElement(name = "dNumCasRec", required = true)
    private Integer dNumCasRec;

    @XmlElement(name = "cDepRec", required = true)
    private short cDepRec;

    @XmlElement(name = "dDesDepRec", required = true)
    private String dDesDepRec;

    @XmlElement(name = "cDisRec")
    private Short cDisRec;

    @XmlElement(name = "dDesDisRec")
    private String dDesDisRec;

    @XmlElement(name = "cCiuRec", required = true)
    private int cCiuRec;

    @XmlElement(name = "dDesCiuRec", required = true)
    private String dDesCiuRec;

    @XmlElement(name = "dTelRec")
    private String dTelRec;

    @XmlElement(name = "dCelRec")
    private String dCelRec;

    @XmlElement(name = "dEmailRec")
    private String dEmailRec;

    @XmlElement(name = "dCodCliente")
    private String dCodCliente;
}
