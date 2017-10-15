/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "item_comercializavel")
@NamedQueries({
    @NamedQuery(name = "ItemComercializavel.findAll", query = "SELECT i FROM ItemComercializavel i")})
public class ItemComercializavel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_ItemComercializavel", strategy = "increment")
    @GeneratedValue(generator = "increment_ItemComercializavel")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "apresentacao")
    private String apresentacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nome_completo")
    private String nomeCompleto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "margem_lucro")
    private double margemLucro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_venda")
    private double valorVenda;
    @Size(max = 14)
    @Column(name = "cean")
    private String cean;
    @Column(name = "data_exclusao")
    @Temporal(TemporalType.DATE)
    private Date dataExclusao;
    @JoinColumn(name = "id_tipo_item_comercializavel", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoItemComercializavel tipoItemComercializavel;
    @JoinColumn(name = "id_fabricante", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Fabricante fabricante;

    public ItemComercializavel() {
    }

    public ItemComercializavel(Long id) {
        this.id = id;
    }

    public ItemComercializavel(Long id, String nome, String apresentacao, String nomeCompleto, double margemLucro, double valorVenda) {
        this.id = id;
        this.nome = nome;
        this.apresentacao = apresentacao;
        this.nomeCompleto = nomeCompleto;
        this.margemLucro = margemLucro;
        this.valorVenda = valorVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getCean() {
        return cean;
    }

    public void setCean(String cean) {
        this.cean = cean;
    }

    public Date getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public TipoItemComercializavel getTipoItemComercializavel() {
        return tipoItemComercializavel;
    }

    public void setTipoItemComercializavel(TipoItemComercializavel idTipoItemComercializavel) {
        this.tipoItemComercializavel = idTipoItemComercializavel;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
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
        if (!(object instanceof ItemComercializavel)) {
            return false;
        }
        ItemComercializavel other = (ItemComercializavel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ItemComercializavel[ id=" + id + " ]";
    }
    
}
