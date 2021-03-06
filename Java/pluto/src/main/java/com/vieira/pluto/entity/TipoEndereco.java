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
@Table(name = "tipo_endereco")
@NamedQueries({
    @NamedQuery(name = "TipoEndereco.findAll", query = "SELECT t FROM TipoEndereco t")})
public class TipoEndereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEndereco", fetch = FetchType.LAZY)
    private List<Endereco> enderecoList;

    public TipoEndereco() {
    }

    public TipoEndereco(Long id) {
        this.id = id;
    }

    public TipoEndereco(Long id, String nome) {
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

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
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
        if (!(object instanceof TipoEndereco)) {
            return false;
        }
        TipoEndereco other = (TipoEndereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.TipoEndereco[ id=" + id + " ]";
    }
    
}
