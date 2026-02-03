package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "iAfecIVA", "dDesAfecIVA", "dPropIVA", "dTasaIVA", "dLiqIVAItem"
})
public class GCamIVA {

    @XmlElement(name = "iAfecIVA", required = true)
    private short iAfecIVA; // 1=Gravado 10, 2=Gravado 5, 3=Exento, etc.

    @XmlElement(name = "dDesAfecIVA", required = true)
    private String dDesAfecIVA;

    @XmlElement(name = "dPropIVA", required = true)
    private BigDecimal dPropIVA; // Proportion (100, 0, etc)

    @XmlElement(name = "dTasaIVA", required = true)
    private int dTasaIVA; // 10, 5, 0

    @XmlElement(name = "dLiqIVAItem", required = true)
    private BigDecimal dLiqIVAItem; // VAT Liquidation amount
}
