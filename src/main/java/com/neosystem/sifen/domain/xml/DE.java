package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "dId", "dDvId", "dFecFirma", "dSisFact",
        "gOpeDE", "gTimb", "gDatGralOpe", "gDtipDE", "gTotSub", "gCamItem", "gCamOSP", "gTransp" // Add others as we go
})
public class DE {

    @XmlAttribute(name = "Id", required = true)
    private String id; // The CDC

    @XmlElement(name = "dId", required = true)
    private String dId; // Unique ID per document

    @XmlElement(name = "dDvId", required = true)
    private String dDvId; // Check digit of dId

    @XmlElement(name = "dFecFirma", required = true)
    private String dFecFirma; // Signature date ISO 8601

    @XmlElement(name = "dSisFact", required = true)
    private String dSisFact = "1"; // 1 = Sistema Propio

    // Sub-components to be implemented
    @XmlElement(name = "gOpeDE", required = true)
    private GOpeDE gOpeDE;

    @XmlElement(name = "gTimb", required = true)
    private GTimb gTimb;

    @XmlElement(name = "gDatGralOpe", required = true)
    private GDatGralOpe gDatGralOpe;

    @XmlElement(name = "gDtipDE", required = true)
    private GDtipDE gDtipDE;

    @XmlElement(name = "gTotSub", required = true)
    private GTotSub gTotSub;

    @XmlElement(name = "gCamItem", required = true)
    private java.util.List<GCamItem> gCamItem;

    @XmlElement(name = "gCamOSP")
    private Object gCamOSP;

    @XmlElement(name = "gTransp")
    private Object gTransp;
}
