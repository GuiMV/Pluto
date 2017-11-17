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
import javax.validation.constraints.Size;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "modelo_veiculo")
@NamedQueries({
    @NamedQuery(name = "ModeloVeiculo.findAll", query = "SELECT m FROM ModeloVeiculo m")})
public class ModeloVeiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_ModeloVeiculo", strategy = "increment")
    @GeneratedValue(generator = "increment_ModeloVeiculo")
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nome")
    private String nome;
    @JoinColumn(name = "id_fabricante", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Fabricante fabricante;

    public ModeloVeiculo() {
    }

    public ModeloVeiculo(Long id) {
        this.id = id;
    }

    public ModeloVeiculo(Long id, String nome) {
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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModeloVeiculo that = (ModeloVeiculo) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ModeloVeiculo{" +
                "id=" + id +
                '}';
    }
}
