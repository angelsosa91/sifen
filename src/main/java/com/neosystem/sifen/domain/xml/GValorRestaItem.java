package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "dDescItem", "dPorcDesIt", "dDescGloItem", "dAntPreUniIt", "dAntGloPreUniIt",
        "dTotOpeItem", "dTotOpeGs"
})
public class GValorRestaItem {

    @XmlElement(name = "dDescItem")
    private BigDecimal dDescItem; // Discount amount

    @XmlElement(name = "dPorcDesIt")
    private BigDecimal dPorcDesIt; // Discount percent

    @XmlElement(name = "dDescGloItem")
    private BigDecimal dDescGloItem;

    @XmlElement(name = "dAntPreUniIt")
    private BigDecimal dAntPreUniIt; // Advance payment

    @XmlElement(name = "dAntGloPreUniIt")
    private BigDecimal dAntGloPreUniIt;

    @XmlElement(name = "dTotOpeItem", required = true)
    private BigDecimal dTotOpeItem; // Total Operation Item (Net)

    @XmlElement(name = "dTotOpeGs")
    private BigDecimal dTotOpeGs; // Total in Guarani
}
