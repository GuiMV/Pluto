/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
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
@Table(name = "conta_pagar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaPagar.findAll", query = "SELECT c FROM ContaPagar c"),
    @NamedQuery(name = "ContaPagar.findById", query = "SELECT c FROM ContaPagar c WHERE c.id = :id"),
    @NamedQuery(name = "ContaPagar.findByData", query = "SELECT c FROM ContaPagar c WHERE c.data = :data"),
    @NamedQuery(name = "ContaPagar.findByValorTotal", query = "SELECT c FROM ContaPagar c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "ContaPagar.findByDataQuitacao", query = "SELECT c FROM ContaPagar c WHERE c.dataQuitacao = :dataQuitacao"),
    @NamedQuery(name = "ContaPagar.findByDataExclusao", query = "SELECT c FROM ContaPagar c WHERE c.dataExclusao = :dataExclusao"),
    @NamedQuery(name = "ContaPagar.findByIdUsuario", query = "SELECT c FROM ContaPagar c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "ContaPagar.findByIdPessoa", query = "SELECT c FROM ContaPagar c WHERE c.pessoa.id = :idPessoa")})
public class ContaPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_ContaPagar", strategy = "increment")
    @GeneratedValue(generator = "increment_ContaPagar")
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
    @Column(name = "valor_total")
    private double valorTotal;
    @Column(name = "data_quitacao")
    private LocalDate dataQuitacao;
    @Column(name = "data_exclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExclusao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private long idUsuario;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pessoa pessoa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaPagar", fetch = FetchType.LAZY)
    private List<ParcelaContaPagar> parcelaContaPagarList;

    public ContaPagar() {
    }

    public ContaPagar(Long id) {
        this.id = id;
    }

    public ContaPagar(Long id, Date data, double valorTotal, long idUsuario, Pessoa pessoa) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.idUsuario = idUsuario;
        this.pessoa = pessoa;
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

    public Date getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @XmlTransient
    public List<ParcelaContaPagar> getParcelaContaPagarList() {
        return parcelaContaPagarList;
    }

    public void setParcelaContaPagarList(List<ParcelaContaPagar> parcelaContaPagarList) {
        this.parcelaContaPagarList = parcelaContaPagarList;
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
        if (!(object instanceof ContaPagar)) {
            return false;
        }
        ContaPagar other = (ContaPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ContaPagar[ id=" + id + " ]";
    }
    
}
