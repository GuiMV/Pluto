/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "conta_receber")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaReceber.findAll", query = "SELECT c FROM ContaReceber c"),
    @NamedQuery(name = "ContaReceber.findById", query = "SELECT c FROM ContaReceber c WHERE c.id = :id"),
    @NamedQuery(name = "ContaReceber.findByData", query = "SELECT c FROM ContaReceber c WHERE c.data = :data"),
    @NamedQuery(name = "ContaReceber.findByValorTotal", query = "SELECT c FROM ContaReceber c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "ContaReceber.findByDataQuitacao", query = "SELECT c FROM ContaReceber c WHERE c.dataQuitacao = :dataQuitacao"),
    @NamedQuery(name = "ContaReceber.findByIdVenda", query = "SELECT c FROM ContaReceber c WHERE c.venda.id = :idVenda"),
    @NamedQuery(name = "ContaReceber.findByIdUsuario", query = "SELECT c FROM ContaReceber c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "ContaReceber.findByIdCliente", query = "SELECT c FROM ContaReceber c WHERE c.cliente.id = :idCliente")})
public class ContaReceber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_ContaReceber", strategy = "increment")
    @GeneratedValue(generator = "increment_ContaReceber")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "data_exclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExclusao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private double valorTotal;
    @Column(name = "data_quitacao")
    private LocalDate dataQuitacao;
    @JoinColumn(name = "id_venda", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Venda venda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private long idUsuario;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaReceber", fetch = FetchType.LAZY)
    private List<ParcelaContaReceber> parcelaContaReceberList;

    public ContaReceber() {
    }

    public ContaReceber(Long id) {
        this.id = id;
    }

    public ContaReceber(Long id, Date data, double valorTotal) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
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

    public Date getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataQuitacao() {
        return dataQuitacao;
    }

    public void setDataQuitacao(LocalDate dataQuitacao) {
        this.dataQuitacao = dataQuitacao;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public List<ParcelaContaReceber> getParcelaContaReceberList() {
        return parcelaContaReceberList;
    }

    public void setParcelaContaReceberList(List<ParcelaContaReceber> parcelaContaReceberList) {
        this.parcelaContaReceberList = parcelaContaReceberList;
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
        if (!(object instanceof ContaReceber)) {
            return false;
        }
        ContaReceber other = (ContaReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ContaReceber[ id=" + id + " ]";
    }
    
}
