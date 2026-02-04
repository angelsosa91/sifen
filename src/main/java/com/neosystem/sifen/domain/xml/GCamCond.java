package com.neosystem.sifen.domain.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "iCondOpe", "dDesCondOpe", "gPaCond", "gCuotas" })
public class GCamCond {

    @XmlElement(name = "iCondOpe", required = true)
    private short iCondOpe; // 1=Contado, 2=Credito

    @XmlElement(name = "dDesCondOpe", required = true)
    private String dDesCondOpe;

    @XmlElement(name = "gPaCond")
    private List<GPAcond> gPaCond;

    @XmlElement(name = "gCuotas")
    private GCuotas gCuotas;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "iTiPa", "dDesTiPa", "dMonTiPa", "cMoneTiPa", "dDesMoneTiPa" })
    public static class GPAcond {
        @XmlElement(name = "iTiPa", required = true)
        private short iTiPa;
        @XmlElement(name = "dDesTiPa", required = true)
        private String dDesTiPa;
        @XmlElement(name = "dMonTiPa", required = true)
        private java.math.BigDecimal dMonTiPa;
        @XmlElement(name = "cMoneTiPa", required = true)
        private String cMoneTiPa;
        @XmlElement(name = "dDesMoneTiPa", required = true)
        private String dDesMoneTiPa;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "iCondTiCuo", "dDesCondTiCuo", "dMonCuota", "gCuoti" })
    public static class GCuotas {
        @XmlElement(name = "iCondTiCuo", required = true)
        private short iCondTiCuo;
        @XmlElement(name = "dDesCondTiCuo", required = true)
        private String dDesCondTiCuo;
        @XmlElement(name = "dMonCuota")
        private java.math.BigDecimal dMonCuota; // Optional if gCuoti is present
        @XmlElement(name = "gCuoti")
        private List<GCuoti> gCuoti;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = { "dMonCuota", "dVencCuo" })
    public static class GCuoti {
        @XmlElement(name = "dMonCuota", required = true)
        private java.math.BigDecimal dMonCuota;
        @XmlElement(name = "dVencCuo")
        private String dVencCuo;
    }
}
