package com.vieira.pluto.entity.query;

import javax.persistence.*;

@Entity
@SqlResultSetMapping(name = "ComissaoFuncionarioDto", entities = {@EntityResult(entityClass = ComissaoFuncionarioDto.class)})
public class ComissaoFuncionarioDto {

    @Id
    @Column(name = "id")
    private Long idVenda;
    @Column(name = "comissao")
    private Double comissao;
    @Column(name = "razao_social")
    private String nomeCliente;
    @Column(name = "nome")
    private String nomeVeiculo;
    @Column(name = "placa")
    private String placa;

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
