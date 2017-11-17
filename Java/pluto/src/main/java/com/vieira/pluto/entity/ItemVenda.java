/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "item_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemVenda.findAll", query = "SELECT i FROM ItemVenda i"),
    @NamedQuery(name = "ItemVenda.findById", query = "SELECT i FROM ItemVenda i WHERE i.id = :id"),
    @NamedQuery(name = "ItemVenda.findByQuantidade", query = "SELECT i FROM ItemVenda i WHERE i.quantidade = :quantidade"),
    @NamedQuery(name = "ItemVenda.findByValorVenda", query = "SELECT i FROM ItemVenda i WHERE i.valorVenda = :valorVenda"),
    @NamedQuery(name = "ItemVenda.findByValorTotal", query = "SELECT i FROM ItemVenda i WHERE i.valorTotal = :valorTotal"),
    @NamedQuery(name = "ItemVenda.findByComissao", query = "SELECT i FROM ItemVenda i WHERE i.comissao = :comissao"),
    @NamedQuery(name = "ItemVenda.findByIdItemComercializavel", query = "SELECT i FROM ItemVenda i WHERE i.itemComercializavel.id = :idItemComercializavel"),
    @NamedQuery(name = "ItemVenda.findByIdFuncionario", query = "SELECT i FROM ItemVenda i WHERE i.funcionario.id = :idFuncionario")})
public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_ItemVenda", strategy = "increment")
    @GeneratedValue(generator = "increment_ItemVenda")
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "comissao")
    private double comissao;
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Funcionario funcionario;
    @JoinColumn(name = "id_item_comercializavel", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ItemComercializavel itemComercializavel;
    @JoinColumn(name = "id_venda", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Venda venda;

    public ItemVenda() {
    }

    public ItemVenda(Long id) {
        this.id = id;
    }

    public ItemVenda(Long id, double quantidade, double valorVenda, double valorTotal, long comissao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.valorTotal = valorTotal;
        this.comissao = comissao;
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

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public ItemComercializavel getItemComercializavel() {
        return itemComercializavel;
    }

    public void setItemComercializavel(ItemComercializavel itemComercializavel) {
        this.itemComercializavel = itemComercializavel;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
        if (!(object instanceof ItemVenda)) {
            return false;
        }
        ItemVenda other = (ItemVenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ItemVenda[ id=" + id + " ]";
    }
    
}
