package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "dPUnitPro", "dTiCamIt", "dTotBruOpeItem", "gValorRestaItem"
})
public class GValorItem {

    @XmlElement(name = "dPUnitPro", required = true)
    private BigDecimal dPUnitPro; // Unit Price

    @XmlElement(name = "dTiCamIt")
    private BigDecimal dTiCamIt; // Exchange Rate

    @XmlElement(name = "dTotBruOpeItem", required = true)
    private BigDecimal dTotBruOpeItem; // Total Gross (Unit * Qty)

    @XmlElement(name = "gValorRestaItem", required = true)
    private GValorRestaItem gValorRestaItem;
}
