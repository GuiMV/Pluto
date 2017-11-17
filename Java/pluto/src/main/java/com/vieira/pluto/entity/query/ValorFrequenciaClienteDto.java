package com.vieira.pluto.entity.query;

import javax.persistence.*;

@Entity
@SqlResultSetMapping(name = "ValorFrequenciaClienteDto", entities = {@EntityResult(entityClass = ValorFrequenciaClienteDto.class)})
public class ValorFrequenciaClienteDto {

    @Id
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "frequencia")
    private Integer frequencia;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }
}
