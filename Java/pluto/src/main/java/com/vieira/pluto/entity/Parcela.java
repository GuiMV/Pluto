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
@Table(name = "parcela")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parcela.findAll", query = "SELECT p FROM Parcela p"),
    @NamedQuery(name = "Parcela.findById", query = "SELECT p FROM Parcela p WHERE p.id = :id"),
    @NamedQuery(name = "Parcela.findByQuantidade", query = "SELECT p FROM Parcela p WHERE p.quantidade = :quantidade")})
public class Parcela implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_Parcela", strategy = "increment")
    @GeneratedValue(generator = "increment_Parcela")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private int quantidade;

    public Parcela() {
    }

    public Parcela(Long id) {
        this.id = id;
    }

    public Parcela(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
        if (!(object instanceof Parcela)) {
            return false;
        }
        Parcela other = (Parcela) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.Parcela[ id=" + id + " ]";
    }
    
}
