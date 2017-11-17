package com.vieira.pluto.dto;

import com.vieira.pluto.util.Dates;

import java.util.Date;

public class ConsultaComissaoDto {
    private Long idFuncionario;
    private Date dataInicial;
    private Date dataFinal;

    public ConsultaComissaoDto() {
        this.dataInicial = Dates.minusMonths(new Date(),1);
        this.dataInicial = Dates.getBeginningOfTheMonth(this.dataInicial);
        this.dataFinal = Dates.minusMonths(new Date(),1);
        this.dataFinal = Dates.getEndOfTheMonth(this.dataFinal);
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}
