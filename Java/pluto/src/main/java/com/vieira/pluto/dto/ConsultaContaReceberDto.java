package com.vieira.pluto.dto;

import com.vieira.pluto.util.Dates;

import java.util.Date;

public class ConsultaContaReceberDto {

    private Date dataInicio;
    private Date dataFim;
    private Long idCliente;

    public ConsultaContaReceberDto() {
        this.dataInicio = Dates.getDateInMidnight(Dates.minusDays(new Date(),7));
        this.dataFim = Dates.getDateInLastTimeOfDay(new Date());
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
