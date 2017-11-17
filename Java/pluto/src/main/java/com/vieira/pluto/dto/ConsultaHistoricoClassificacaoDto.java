package com.vieira.pluto.dto;

import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.util.Dates;

import java.util.Date;

public class ConsultaHistoricoClassificacaoDto {

    private Integer mesInicio;
    private Integer mesFim;
    private Integer anoInicio;
    private Integer anoFim;
    private Long idCliente;

    public ConsultaHistoricoClassificacaoDto() {
        this.mesFim = Dates.getMes(new Date());
        this.mesInicio = Dates.getMes(new Date());
        this.anoFim = Dates.getAno(new Date());
        this.anoInicio = Dates.getAno(new Date());
    }

    public Integer getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(Integer mesInicio) {
        this.mesInicio = mesInicio;
    }

    public Integer getMesFim() {
        return mesFim;
    }

    public void setMesFim(Integer mesFim) {
        this.mesFim = mesFim;
    }

    public Integer getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(Integer anoInicio) {
        this.anoInicio = anoInicio;
    }

    public Integer getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(Integer anoFim) {
        this.anoFim = anoFim;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
