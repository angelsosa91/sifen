package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "dCodInt", "dParAranc", "dNCM", "dDncpG", "dDncpE", "dGtin", "dGtinPrib",
        "dDesProSer", "cUniMed", "dDesUniMed", "dCantProSer", "cPaisOrig", "dDesPaisOrig",
        "dInfItem", "cRelMerc", "dDesRelMerc", "dCanQuiMer", "dPorQuiMer", "dCDCAnt",
        "gValorItem", "gCamIVA", "gRasMerc", "gVehNuevo"
})
public class GCamItem {

    @XmlElement(name = "dCodInt", required = true)
    private String dCodInt; // Internal Code

    @XmlElement(name = "dParAranc")
    private Short dParAranc;

    @XmlElement(name = "dNCM")
    private Integer dNCM;

    @XmlElement(name = "dDncpG")
    private String dDncpG;

    @XmlElement(name = "dDncpE")
    private String dDncpE;

    @XmlElement(name = "dGtin")
    private Long dGtin;

    @XmlElement(name = "dGtinPrib")
    private Long dGtinPrib;

    @XmlElement(name = "dDesProSer", required = true)
    private String dDesProSer; // Description

    @XmlElement(name = "cUniMed", required = true)
    private short cUniMed; // Code Unit of Measure (77=Unit, etc)

    @XmlElement(name = "dDesUniMed", required = true)
    private String dDesUniMed;

    @XmlElement(name = "dCantProSer", required = true)
    private BigDecimal dCantProSer; // Quantity

    @XmlElement(name = "cPaisOrig")
    private String cPaisOrig;

    @XmlElement(name = "dDesPaisOrig")
    private String dDesPaisOrig;

    @XmlElement(name = "dInfItem")
    private String dInfItem;

    @XmlElement(name = "cRelMerc")
    private Short cRelMerc;

    @XmlElement(name = "dDesRelMerc")
    private String dDesRelMerc;

    @XmlElement(name = "dCanQuiMer")
    private BigDecimal dCanQuiMer;

    @XmlElement(name = "dPorQuiMer")
    private BigDecimal dPorQuiMer;

    @XmlElement(name = "dCDCAnt")
    private String dCDCAnt;

    @XmlElement(name = "gValorItem", required = true)
    private GValorItem gValorItem;

    @XmlElement(name = "gCamIVA", required = true)
    private GCamIVA gCamIVA;

    @XmlElement(name = "gRasMerc")
    private Object gRasMerc; // Tracking info

    @XmlElement(name = "gVehNuevo")
    private Object gVehNuevo; // Vehicle info
}
