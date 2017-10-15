package com.vieira.pluto.dto;

import com.vieira.pluto.entity.StatusOrcamento;
import com.vieira.pluto.util.Dates;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ConsultaOrcamentoDto {

    private Date dataInicio;
    private Date dataFim;
    private StatusOrcamento statusOrcamento;
    private Long idCliente;

    public ConsultaOrcamentoDto() {
        this.dataInicio = Dates.getLocalDateTimeInMidnight(Dates.minusDays(new Date(),7));
        this.dataFim = Dates.getLocalDateTimeInLastTimeOfDay(new Date());
        this.statusOrcamento = new StatusOrcamento(1L);
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

    public StatusOrcamento getStatusOrcamento() {
        return statusOrcamento;
    }

    public void setStatusOrcamento(StatusOrcamento statusOrcamento) {
        this.statusOrcamento = statusOrcamento;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
