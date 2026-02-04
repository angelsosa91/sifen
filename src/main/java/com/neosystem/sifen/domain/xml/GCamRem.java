package com.neosystem.sifen.domain.xml;

import lombok.Data;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "iMotEmiNR", "dDesMotEmiNR", "iRespEmiNR", "dDesRespEmiNR", "dKmRet", "dFecEm"
})
public class GCamRem {

    @XmlElement(name = "iMotEmiNR", required = true)
    private short iMotEmiNR; // 1=Traslado, 2=Venta, 3=Compra...

    @XmlElement(name = "dDesMotEmiNR", required = true)
    private String dDesMotEmiNR;

    @XmlElement(name = "iRespEmiNR", required = true)
    private short iRespEmiNR; // 1=Emisor, 2=Poseedor, 3=Tercero

    @XmlElement(name = "dDesRespEmiNR", required = true)
    private String dDesRespEmiNR;

    @XmlElement(name = "dKmRet")
    private Integer dKmRet;

    @XmlElement(name = "dFecEm")
    private String dFecEm; // YYYY-MM-DD
}
