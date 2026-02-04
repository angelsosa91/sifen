package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "iTipNota", "dDesTipNota" })
public class GCamNCDE {

    @XmlElement(name = "iTipNota", required = true)
    private short iTipNota; // 1=Preimpresa, 2=Electronica

    @XmlElement(name = "dDesTipNota", required = true)
    private String dDesTipNota;
}
