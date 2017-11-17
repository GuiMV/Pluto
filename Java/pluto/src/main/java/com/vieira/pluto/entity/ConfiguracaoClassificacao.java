/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "configuracao_classificacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfiguracaoClassificacao.findAll", query = "SELECT c FROM ConfiguracaoClassificacao c"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findById", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.id = :id"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findByPeriodo", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.periodo = :periodo"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findByValorOuro", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.valorOuro = :valorOuro"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findByFrequenciaOuro", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.frequenciaOuro = :frequenciaOuro"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findByRecenciaOuro", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.recenciaOuro = :recenciaOuro"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findByValorPrata", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.valorPrata = :valorPrata"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findByFrequenciaPrata", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.frequenciaPrata = :frequenciaPrata"),
    @NamedQuery(name = "ConfiguracaoClassificacao.findByRecenciaPrata", query = "SELECT c FROM ConfiguracaoClassificacao c WHERE c.recenciaPrata = :recenciaPrata")})
public class ConfiguracaoClassificacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo")
    private int periodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_ouro")
    private double valorOuro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "frequencia_ouro")
    private int frequenciaOuro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recencia_ouro")
    private int recenciaOuro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_prata")
    private double valorPrata;
    @Basic(optional = false)
    @NotNull
    @Column(name = "frequencia_prata")
    private int frequenciaPrata;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recencia_prata")
    private int recenciaPrata;
    @Column(name = "ultimo_mes_historico")
    private Integer ultimoMesHistorico;
    @Column(name = "ultimo_ano_historico")
    private Integer ultimoAnoHistorico;

    public ConfiguracaoClassificacao() {
    }

    public ConfiguracaoClassificacao(Long id) {
        this.id = id;
    }

    public ConfiguracaoClassificacao(Long id, int periodo, double valorOuro, int frequenciaOuro, int recenciaOuro, double valorPrata, int frequenciaPrata, int recenciaPrata) {
        this.id = id;
        this.periodo = periodo;
        this.valorOuro = valorOuro;
        this.frequenciaOuro = frequenciaOuro;
        this.recenciaOuro = recenciaOuro;
        this.valorPrata = valorPrata;
        this.frequenciaPrata = frequenciaPrata;
        this.recenciaPrata = recenciaPrata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public double getValorOuro() {
        return valorOuro;
    }

    public void setValorOuro(double valorOuro) {
        this.valorOuro = valorOuro;
    }

    public int getFrequenciaOuro() {
        return frequenciaOuro;
    }

    public void setFrequenciaOuro(int frequenciaOuro) {
        this.frequenciaOuro = frequenciaOuro;
    }

    public int getRecenciaOuro() {
        return recenciaOuro;
    }

    public void setRecenciaOuro(int recenciaOuro) {
        this.recenciaOuro = recenciaOuro;
    }

    public double getValorPrata() {
        return valorPrata;
    }

    public void setValorPrata(double valorPrata) {
        this.valorPrata = valorPrata;
    }

    public int getFrequenciaPrata() {
        return frequenciaPrata;
    }

    public void setFrequenciaPrata(int frequenciaPrata) {
        this.frequenciaPrata = frequenciaPrata;
    }

    public int getRecenciaPrata() {
        return recenciaPrata;
    }

    public void setRecenciaPrata(int recenciaPrata) {
        this.recenciaPrata = recenciaPrata;
    }

    public Integer getUltimoMesHistorico() {
        return ultimoMesHistorico;
    }

    public void setUltimoMesHistorico(Integer ultimoMesHistorico) {
        this.ultimoMesHistorico = ultimoMesHistorico;
    }

    public Integer getUltimoAnoHistorico() {
        return ultimoAnoHistorico;
    }

    public void setUltimoAnoHistorico(Integer ultimoAnoHistorico) {
        this.ultimoAnoHistorico = ultimoAnoHistorico;
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
        if (!(object instanceof ConfiguracaoClassificacao)) {
            return false;
        }
        ConfiguracaoClassificacao other = (ConfiguracaoClassificacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ConfiguracaoClassificacao[ id=" + id + " ]";
    }
    
}
