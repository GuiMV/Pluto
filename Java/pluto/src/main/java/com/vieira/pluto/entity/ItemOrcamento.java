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
@Table(name = "item_orcamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemOrcamento.findAll", query = "SELECT i FROM ItemOrcamento i"),
    @NamedQuery(name = "ItemOrcamento.findById", query = "SELECT i FROM ItemOrcamento i WHERE i.id = :id"),
    @NamedQuery(name = "ItemOrcamento.findByQuantidade", query = "SELECT i FROM ItemOrcamento i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemOrcamento.findByValorVenda", query = "SELECT i FROM ItemOrcamento i WHERE i.valorVenda = :valorVenda")})
public class ItemOrcamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_ItemOrcamento", strategy = "increment")
    @GeneratedValue(generator = "increment_ItemOrcamento")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private double quantidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_venda")
    private double valorVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;
    @JoinColumn(name = "id_orcamento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orcamento orcamento;
    @JoinColumn(name = "id_item_comercializavel", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ItemComercializavel itemComercializavel;

    public ItemOrcamento() {
    }

    public ItemOrcamento(Long id) {
        this.id = id;
    }

    public ItemOrcamento(Long id, double quantidade, double valorVenda) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public ItemComercializavel getItemComercializavel() {
        return itemComercializavel;
    }

    public void setItemComercializavel(ItemComercializavel itemComercializavel) {
        this.itemComercializavel = itemComercializavel;
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
        if (!(object instanceof ItemOrcamento)) {
            return false;
        }
        ItemOrcamento other = (ItemOrcamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ItemOrcamento[ id=" + id + " ]";
    }
    
}
