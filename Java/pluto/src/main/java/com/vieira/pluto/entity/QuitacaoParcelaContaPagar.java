/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "quitacao_parcela_conta_pagar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuitacaoParcelaContaPagar.findAll", query = "SELECT q FROM QuitacaoParcelaContaPagar q"),
    @NamedQuery(name = "QuitacaoParcelaContaPagar.findById", query = "SELECT q FROM QuitacaoParcelaContaPagar q WHERE q.id = :id"),
    @NamedQuery(name = "QuitacaoParcelaContaPagar.findByValor", query = "SELECT q FROM QuitacaoParcelaContaPagar q WHERE q.valor = :valor"),
    @NamedQuery(name = "QuitacaoParcelaContaPagar.findByIdParcelaContaPagar", query = "SELECT q FROM QuitacaoParcelaContaPagar q WHERE q.idParcelaContaPagar = :idParcelaContaPagar"),
    @NamedQuery(name = "QuitacaoParcelaContaPagar.findByIdFormaPagamento", query = "SELECT q FROM QuitacaoParcelaContaPagar q WHERE q.idFormaPagamento = :idFormaPagamento")})
public class QuitacaoParcelaContaPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_QuitacaoParcelaContaPagar", strategy = "increment")
    @GeneratedValue(generator = "increment_QuitacaoParcelaContaPagar")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_parcela_conta_pagar")
    private long idParcelaContaPagar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_forma_pagamento")
    private long idFormaPagamento;

    public QuitacaoParcelaContaPagar() {
    }

    public QuitacaoParcelaContaPagar(Long id) {
        this.id = id;
    }

    public QuitacaoParcelaContaPagar(Long id, double valor, long idParcelaContaPagar, long idFormaPagamento) {
        this.id = id;
        this.valor = valor;
        this.idParcelaContaPagar = idParcelaContaPagar;
        this.idFormaPagamento = idFormaPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getIdParcelaContaPagar() {
        return idParcelaContaPagar;
    }

    public void setIdParcelaContaPagar(long idParcelaContaPagar) {
        this.idParcelaContaPagar = idParcelaContaPagar;
    }

    public long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
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
        if (!(object instanceof QuitacaoParcelaContaPagar)) {
            return false;
        }
        QuitacaoParcelaContaPagar other = (QuitacaoParcelaContaPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.QuitacaoParcelaContaPagar[ id=" + id + " ]";
    }
    
}
