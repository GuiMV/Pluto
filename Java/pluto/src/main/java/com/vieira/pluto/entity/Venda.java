/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findById", query = "SELECT v FROM Venda v WHERE v.id = :id"),
    @NamedQuery(name = "Venda.findByData", query = "SELECT v FROM Venda v WHERE v.data = :data"),
    @NamedQuery(name = "Venda.findByDescricao", query = "SELECT v FROM Venda v WHERE v.descricao = :descricao"),
    @NamedQuery(name = "Venda.findByValorTotal", query = "SELECT v FROM Venda v WHERE v.valorTotal = :valorTotal"),
    @NamedQuery(name = "Venda.findByIdUsuario", query = "SELECT v FROM Venda v WHERE v.idUsuario = :idUsuario"),
    @NamedQuery(name = "Venda.findByIdOrcamento", query = "SELECT v FROM Venda v WHERE v.idOrcamento = :idOrcamento"),
    @NamedQuery(name = "Venda.findByIdCliente", query = "SELECT v FROM Venda v WHERE v.cliente.id = :idCliente")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_Venda", strategy = "increment")
    @GeneratedValue(generator = "increment_Venda")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private long idUsuario;
    @Column(name = "id_orcamento")
    private Long idOrcamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda", fetch = FetchType.LAZY)
    private List<ItemVenda> itemVendaList;
    @JoinColumn(name = "id_veiculo_venda", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private VeiculoVenda veiculoVenda;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente cliente;

    public Venda() {
    }

    public Venda(Long id) {
        this.id = id;
    }

    public Venda(Long id, Date data, String descricao, double valorTotal, long idUsuario) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.idUsuario = idUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public List<ItemVenda> getItemVendaList() {
        return itemVendaList;
    }

    public void setItemVendaList(List<ItemVenda> itemVendaList) {
        this.itemVendaList = itemVendaList;
    }

    public VeiculoVenda getVeiculoVenda() {
        return veiculoVenda;
    }

    public void setVeiculoVenda(VeiculoVenda veiculoVenda) {
        this.veiculoVenda = veiculoVenda;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.Venda[ id=" + id + " ]";
    }
    
}
