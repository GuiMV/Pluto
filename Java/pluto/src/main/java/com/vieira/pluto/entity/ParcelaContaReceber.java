/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "parcela_conta_receber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParcelaContaReceber.findAll", query = "SELECT p FROM ParcelaContaReceber p"),
    @NamedQuery(name = "ParcelaContaReceber.findById", query = "SELECT p FROM ParcelaContaReceber p WHERE p.id = :id"),
    @NamedQuery(name = "ParcelaContaReceber.findByValor", query = "SELECT p FROM ParcelaContaReceber p WHERE p.valor = :valor"),
    @NamedQuery(name = "ParcelaContaReceber.findByDataVencimento", query = "SELECT p FROM ParcelaContaReceber p WHERE p.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "ParcelaContaReceber.findByDataQuitacao", query = "SELECT p FROM ParcelaContaReceber p WHERE p.dataQuitacao = :dataQuitacao")})
public class ParcelaContaReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_ParcelaContaReceber", strategy = "increment")
    @GeneratedValue(generator = "increment_ParcelaContaReceber")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    @Column(name = "data_quitacao")
    private LocalDate dataQuitacao;
    @JoinColumn(name = "id_conta_receber", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ContaReceber contaReceber;

    public ParcelaContaReceber() {
    }

    public ParcelaContaReceber(Long id) {
        this.id = id;
    }

    public ParcelaContaReceber(Long id, double valor, LocalDate dataVencimento) {
        this.id = id;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataQuitacao() {
        return dataQuitacao;
    }

    public void setDataQuitacao(LocalDate dataQuitacao) {
        this.dataQuitacao = dataQuitacao;
    }

    public ContaReceber getContaReceber() {
        return contaReceber;
    }

    public void setContaReceber(ContaReceber contaReceber) {
        this.contaReceber = contaReceber;
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
        if (!(object instanceof ParcelaContaReceber)) {
            return false;
        }
        ParcelaContaReceber other = (ParcelaContaReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ParcelaContaReceber[ id=" + id + " ]";
    }
    
}
