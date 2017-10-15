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

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "veiculo_orcamento")
@NamedQueries({
    @NamedQuery(name = "VeiculoOrcamento.findAll", query = "SELECT v FROM VeiculoOrcamento v")})
public class VeiculoOrcamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_VeiculoOrcamento", strategy = "increment")
    @GeneratedValue(generator = "increment_VeiculoOrcamento")
    @Basic(optional = false)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veiculoOrcamento", fetch = FetchType.LAZY)
    private List<Orcamento> orcamentoList;

    public VeiculoOrcamento() {
    }

    public VeiculoOrcamento(Long id) {
        this.id = id;
    }

    public VeiculoOrcamento(Long id, String placa) {
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

    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
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
        if (!(object instanceof VeiculoOrcamento)) {
            return false;
        }
        VeiculoOrcamento other = (VeiculoOrcamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.VeiculoOrcamento[ id=" + id + " ]";
    }
    
}
