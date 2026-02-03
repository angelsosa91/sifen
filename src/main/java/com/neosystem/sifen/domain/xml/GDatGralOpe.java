package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "dFeEmiDE", "gEmis", "gDatRec" })
public class GDatGralOpe {

    @XmlElement(name = "dFeEmiDE", required = true)
    private String dFeEmiDE; // ISO 8601 Date Time

    @XmlElement(name = "gEmis", required = true)
    private GEmis gEmis;

    @XmlElement(name = "gDatRec", required = true)
    private GDatRec gDatRec;
}
