package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "dRucEm", "dDVEmi", "iTipCont", "cTipReg", "dNomEmi", "dNomFanEmi", "dDirEmi",
        "dNumCas", "dCompDir1", "dCompDir2", "cDepEmi", "dDesDepEmi", "cDisEmi", "dDesDisEmi",
        "cCiuEmi", "dDesCiuEmi", "dTelEmi", "dEmailE", "dDenSuc", "gActEco"
})
public class GEmis {

    @XmlElement(name = "dRucEm", required = true)
    private String dRucEm; // RUC without DV

    @XmlElement(name = "dDVEmi", required = true)
    private String dDVEmi; // DV

    @XmlElement(name = "iTipCont", required = true)
    private short iTipCont; // 1=Persona Fisica, 2=Persona Juridica

    @XmlElement(name = "cTipReg")
    private Short cTipReg; // Optional default 8 (General)

    @XmlElement(name = "dNomEmi", required = true)
    private String dNomEmi; // Social Reason

    @XmlElement(name = "dNomFanEmi")
    private String dNomFanEmi; // Fantasy Name

    @XmlElement(name = "dDirEmi", required = true)
    private String dDirEmi; // Address

    @XmlElement(name = "dNumCas", required = true)
    private String dNumCas; // House number

    @XmlElement(name = "dCompDir1")
    private String dCompDir1;

    @XmlElement(name = "dCompDir2")
    private String dCompDir2;

    @XmlElement(name = "cDepEmi", required = true)
    private short cDepEmi; // Departamento code

    @XmlElement(name = "dDesDepEmi", required = true)
    private String dDesDepEmi; // Departamento description

    @XmlElement(name = "cDisEmi")
    private Short cDisEmi; // Distrito code

    @XmlElement(name = "dDesDisEmi")
    private String dDesDisEmi; // Distrito description

    @XmlElement(name = "cCiuEmi", required = true)
    private int cCiuEmi; // Ciudad code

    @XmlElement(name = "dDesCiuEmi", required = true)
    private String dDesCiuEmi; // Ciudad description

    @XmlElement(name = "dTelEmi", required = true)
    private String dTelEmi;

    @XmlElement(name = "dEmailE", required = true)
    private String dEmailE;

    @XmlElement(name = "dDenSuc")
    private String dDenSuc; // Sucursal name

    @XmlElement(name = "gActEco")
    private Object gActEco; // Economic Activities (List)
}
