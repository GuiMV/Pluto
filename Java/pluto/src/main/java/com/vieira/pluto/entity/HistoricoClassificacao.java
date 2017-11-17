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
@Table(name = "historico_classificacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoricoClassificacao.findAll", query = "SELECT h FROM HistoricoClassificacao h"),
    @NamedQuery(name = "HistoricoClassificacao.findById", query = "SELECT h FROM HistoricoClassificacao h WHERE h.id = :id"),
    @NamedQuery(name = "HistoricoClassificacao.findByMes", query = "SELECT h FROM HistoricoClassificacao h WHERE h.mes = :mes"),
    @NamedQuery(name = "HistoricoClassificacao.findByAno", query = "SELECT h FROM HistoricoClassificacao h WHERE h.ano = :ano"),
    @NamedQuery(name = "HistoricoClassificacao.findByIdCliente", query = "SELECT h FROM HistoricoClassificacao h WHERE h.cliente.id = :idCliente")})
public class HistoricoClassificacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_HistoricoClassificacao", strategy = "increment")
    @GeneratedValue(generator = "increment_HistoricoClassificacao")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private int ano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_classificacao")
    private Long idClassificacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private long idCliente;
    @JoinColumn(name = "id_classificacao", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Classificacao classificacao;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente cliente;

    public HistoricoClassificacao() {
    }

    public HistoricoClassificacao(Long id) {
        this.id = id;
    }

    public HistoricoClassificacao(Long id, int mes, int ano, long idCliente) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Long getIdClassificacao() {
        return idClassificacao;
    }

    public void setIdClassificacao(Long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof HistoricoClassificacao)) {
            return false;
        }
        HistoricoClassificacao other = (HistoricoClassificacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.HistoricoClassificacao[ id=" + id + " ]";
    }
    
}
