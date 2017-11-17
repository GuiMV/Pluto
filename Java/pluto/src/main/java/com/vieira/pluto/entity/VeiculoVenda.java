/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "veiculo_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VeiculoVenda.findAll", query = "SELECT v FROM VeiculoVenda v"),
    @NamedQuery(name = "VeiculoVenda.findById", query = "SELECT v FROM VeiculoVenda v WHERE v.id = :id"),
    @NamedQuery(name = "VeiculoVenda.findByIdCor", query = "SELECT v FROM VeiculoVenda v WHERE v.cor.id = :idCor"),
    @NamedQuery(name = "VeiculoVenda.findByPlaca", query = "SELECT v FROM VeiculoVenda v WHERE v.placa = :placa"),
    @NamedQuery(name = "VeiculoVenda.findByIdModeloVeiculo", query = "SELECT v FROM VeiculoVenda v WHERE v.modeloVeiculo.id = :idModeloVeiculo")})
public class VeiculoVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_VeiculoVenda", strategy = "increment")
    @GeneratedValue(generator = "increment_VeiculoVenda")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "placa")
    private String placa;
    @JoinColumn(name = "id_modelo_veiculo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ModeloVeiculo modeloVeiculo;
    @JoinColumn(name = "id_cor", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cor cor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veiculoVenda", fetch = FetchType.LAZY)
    private List<Venda> vendaList;

    public VeiculoVenda() {
    }

    public VeiculoVenda(Long id) {
        this.id = id;
    }

    public VeiculoVenda(Long id, String placa) {
        this.id = id;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public ModeloVeiculo getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(ModeloVeiculo modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    @XmlTransient
    public List<Venda> getVendaList() {
        return vendaList;
    }

    public void setVendaList(List<Venda> vendaList) {
        this.vendaList = vendaList;
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
        if (!(object instanceof VeiculoVenda)) {
            return false;
        }
        VeiculoVenda other = (VeiculoVenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.VeiculoVenda[ id=" + id + " ]";
    }
    
}
