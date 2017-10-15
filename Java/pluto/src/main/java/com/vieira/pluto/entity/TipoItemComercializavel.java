/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "tipo_item_comercializavel")
@NamedQueries({
    @NamedQuery(name = "TipoItemComercializavel.findAll", query = "SELECT t FROM TipoItemComercializavel t")})
public class TipoItemComercializavel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoItemComercializavel", fetch = FetchType.LAZY)
    private List<ItemComercializavel> itemComercializavelList;

    public TipoItemComercializavel() {
    }

    public TipoItemComercializavel(Long id) {
        this.id = id;
    }

    public TipoItemComercializavel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public List<ItemComercializavel> getItemComercializavelList() {
        return itemComercializavelList;
    }

    public void setItemComercializavelList(List<ItemComercializavel> itemComercializavelList) {
        this.itemComercializavelList = itemComercializavelList;
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
        if (!(object instanceof TipoItemComercializavel)) {
            return false;
        }
        TipoItemComercializavel other = (TipoItemComercializavel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.TipoItemComercializavel[ id=" + id + " ]";
    }
    
}
