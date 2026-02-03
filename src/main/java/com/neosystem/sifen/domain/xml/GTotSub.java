package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "dSubExe", "dSubExo", "dSub5", "dSub10", "dTotOpe", "dTotDesc", "dTotDescGlotem",
        "dTotAntItem", "dTotAnt", "dPorcDescTotal", "dDescTotal", "dAnticipo", "dRedon",
        "dComi", "dTotGralOpe", "dTotOpeGS", "dTotGralOpeGS", "dTotIVA", "dBaseGrav5",
        "dBaseGrav10", "dTLiqGrav5", "dTLiqGrav10", "dTotGuaranies"
})
public class GTotSub {

    @XmlElement(name = "dSubExe")
    private BigDecimal dSubExe;

    @XmlElement(name = "dSubExo")
    private BigDecimal dSubExo;

    @XmlElement(name = "dSub5")
    private BigDecimal dSub5;

    @XmlElement(name = "dSub10")
    private BigDecimal dSub10;

    @XmlElement(name = "dTotOpe", required = true)
    private BigDecimal dTotOpe;

    @XmlElement(name = "dTotDesc")
    private BigDecimal dTotDesc;

    @XmlElement(name = "dTotDescGlotem")
    private BigDecimal dTotDescGlotem;

    @XmlElement(name = "dTotAntItem")
    private BigDecimal dTotAntItem;

    @XmlElement(name = "dTotAnt")
    private BigDecimal dTotAnt;

    @XmlElement(name = "dPorcDescTotal")
    private BigDecimal dPorcDescTotal;

    @XmlElement(name = "dDescTotal")
    private BigDecimal dDescTotal;

    @XmlElement(name = "dAnticipo")
    private BigDecimal dAnticipo;

    @XmlElement(name = "dRedon")
    private BigDecimal dRedon;

    @XmlElement(name = "dComi")
    private BigDecimal dComi;

    @XmlElement(name = "dTotGralOpe", required = true)
    private BigDecimal dTotGralOpe;

    @XmlElement(name = "dTotOpeGS")
    private BigDecimal dTotOpeGS;

    @XmlElement(name = "dTotGralOpeGS")
    private BigDecimal dTotGralOpeGS;

    @XmlElement(name = "dTotIVA", required = true)
    private BigDecimal dTotIVA;

    @XmlElement(name = "dBaseGrav5")
    private BigDecimal dBaseGrav5;

    @XmlElement(name = "dBaseGrav10")
    private BigDecimal dBaseGrav10;

    @XmlElement(name = "dTLiqGrav5")
    private BigDecimal dTLiqGrav5;

    @XmlElement(name = "dTLiqGrav10")
    private BigDecimal dTLiqGrav10;

    @XmlElement(name = "dTotGuaranies") // Used if foreign currency
    private BigDecimal dTotGuaranies;
}
