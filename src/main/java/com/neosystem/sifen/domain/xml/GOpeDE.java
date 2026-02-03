package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "iTipEmi", "dDesTipEmi", "dCodSeg", "dInfoEmi", "dInfoFisc" })
public class GOpeDE {

    @XmlElement(name = "iTipEmi", required = true)
    private short iTipEmi; // 1=Normal, 2=Contingencia

    @XmlElement(name = "dDesTipEmi", required = true)
    private String dDesTipEmi;

    @XmlElement(name = "dCodSeg", required = true)
    private String dCodSeg; // Random generated code

    @XmlElement(name = "dInfoEmi")
    private String dInfoEmi; // Optional

    @XmlElement(name = "dInfoFisc")
    private String dInfoFisc; // Optional
}
