/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "orcamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orcamento.findAll", query = "SELECT o FROM Orcamento o"),
    @NamedQuery(name = "Orcamento.findById", query = "SELECT o FROM Orcamento o WHERE o.id = :id"),
    @NamedQuery(name = "Orcamento.findByData", query = "SELECT o FROM Orcamento o WHERE o.data = :data"),
    @NamedQuery(name = "Orcamento.findByDescricao", query = "SELECT o FROM Orcamento o WHERE o.descricao = :descricao")})
public class Orcamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_Orcamento", strategy = "increment")
    @GeneratedValue(generator = "increment_Orcamento")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;
    @JoinColumn(name = "id_status_orcamento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private StatusOrcamento statusOrcamento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliente;
    @JoinColumn(name = "id_veiculo_orcamento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VeiculoOrcamento veiculoOrcamento;
    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FormaPagamento formaPagamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orcamento", fetch = FetchType.LAZY)
    private List<ItemOrcamento> itemOrcamentoList;

    public Orcamento() {
    }

    public Orcamento(Long id) {
        this.id = id;
    }

    public Orcamento(Long id, Date data, String descricao) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusOrcamento getStatusOrcamento() {
        return statusOrcamento;
    }

    public void setStatusOrcamento(StatusOrcamento statusOrcamento) {
        this.statusOrcamento = statusOrcamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VeiculoOrcamento getVeiculoOrcamento() {
        return veiculoOrcamento;
    }

    public void setVeiculoOrcamento(VeiculoOrcamento veiculoOrcamento) {
        this.veiculoOrcamento = veiculoOrcamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @XmlTransient
    public List<ItemOrcamento> getItemOrcamentoList() {
        return itemOrcamentoList;
    }

    public void setItemOrcamentoList(List<ItemOrcamento> itemOrcamentoList) {
        this.itemOrcamentoList = itemOrcamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orcamento)) {
            return false;
        }
        Orcamento other = (Orcamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.Orcamento[ id=" + id + " ]";
    }
    
}
