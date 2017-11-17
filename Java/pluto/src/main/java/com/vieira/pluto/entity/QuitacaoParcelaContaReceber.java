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
@Table(name = "quitacao_parcela_conta_receber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuitacaoParcelaContaReceber.findAll", query = "SELECT q FROM QuitacaoParcelaContaReceber q"),
    @NamedQuery(name = "QuitacaoParcelaContaReceber.findById", query = "SELECT q FROM QuitacaoParcelaContaReceber q WHERE q.id = :id"),
    @NamedQuery(name = "QuitacaoParcelaContaReceber.findByValor", query = "SELECT q FROM QuitacaoParcelaContaReceber q WHERE q.valor = :valor"),
    @NamedQuery(name = "QuitacaoParcelaContaReceber.findByIdParcelaContaReceber", query = "SELECT q FROM QuitacaoParcelaContaReceber q WHERE q.idParcelaContaReceber = :idParcelaContaReceber"),
    @NamedQuery(name = "QuitacaoParcelaContaReceber.findByIdFormaPagamento", query = "SELECT q FROM QuitacaoParcelaContaReceber q WHERE q.idFormaPagamento = :idFormaPagamento")})
public class QuitacaoParcelaContaReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_QuitacaoParcelaContaReceber", strategy = "increment")
    @GeneratedValue(generator = "increment_QuitacaoParcelaContaReceber")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_parcela_conta_receber")
    private long idParcelaContaReceber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_forma_pagamento")
    private long idFormaPagamento;

    public QuitacaoParcelaContaReceber() {
    }

    public QuitacaoParcelaContaReceber(Long id) {
        this.id = id;
    }

    public QuitacaoParcelaContaReceber(Long id, double valor, long idParcelaContaReceber, long idFormaPagamento) {
        this.id = id;
        this.valor = valor;
        this.idParcelaContaReceber = idParcelaContaReceber;
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

    public long getIdParcelaContaReceber() {
        return idParcelaContaReceber;
    }

    public void setIdParcelaContaReceber(long idParcelaContaReceber) {
        this.idParcelaContaReceber = idParcelaContaReceber;
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
        if (!(object instanceof QuitacaoParcelaContaReceber)) {
            return false;
        }
        QuitacaoParcelaContaReceber other = (QuitacaoParcelaContaReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.QuitacaoParcelaContaReceber[ id=" + id + " ]";
    }
    
}
