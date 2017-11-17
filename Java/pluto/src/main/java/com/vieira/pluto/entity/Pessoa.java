/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Guilherme
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "increment_Pessoa", strategy = "increment")
    @GeneratedValue(generator = "increment_Pessoa")
    @NotNull
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "razao_social")
    private String razaoSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nome_fantasia")
    private String nomeFantasia;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "cpf_cnpj")
    protected String cpfCnpj;
    @Basic(optional = false)
    @Size(min = 0, max = 16)
    @Column(name = "rg_ie")
    private String rgIe;
    @JoinTable(name = "pessoa_telefone", joinColumns = {
        @JoinColumn(name = "id_pessoa", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_telefone", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Telefone> telefoneList;
    @JoinTable(name = "pessoa_endereco", joinColumns = {
        @JoinColumn(name = "id_pessoa", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_endereco", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Endereco> enderecoList;
    @JoinTable(name = "pessoa_email", joinColumns = {
        @JoinColumn(name = "id_pessoa", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_email", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Email> emailList;
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<PessoaVeiculo> pessoaVeiculos;
    @JoinColumn(name = "id_tipo_pessoa", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoPessoa tipoPessoa;
    @JoinColumn(name = "id_telefone", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Telefone telefone;
    @JoinColumn(name = "id_sexo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Sexo sexo;
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private EstadoCivil estadoCivil;
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Endereco endereco;
    @JoinColumn(name = "id_email", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Email email;

    public Pessoa() {
        enderecoList = new ArrayList<>();
        emailList = new ArrayList<>();
        telefoneList = new ArrayList<>();
        pessoaVeiculos = new ArrayList<>();
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        enderecoList = new ArrayList<>();
        emailList = new ArrayList<>();
        telefoneList = new ArrayList<>();
    }

    public Pessoa(String cpfCnpj, TipoPessoa tipoPessoa) {
        this.cpfCnpj = cpfCnpj;
        this.tipoPessoa = tipoPessoa;
        enderecoList = new ArrayList<>();
        emailList = new ArrayList<>();
        telefoneList = new ArrayList<>();
        pessoaVeiculos = new ArrayList<>();
    }

    public Pessoa(Long id, String razaoSocial, String nomeFantasia, String cpfCnpj, String rgIe) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cpfCnpj = cpfCnpj;
        this.rgIe = rgIe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRgIe() {
        return rgIe;
    }

    public void setRgIe(String rgIe) {
        this.rgIe = rgIe;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    public List<Email> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Email> emailList) {
        this.emailList = emailList;
    }

    public List<PessoaVeiculo> getPessoaVeiculos() {
        return pessoaVeiculos;
    }

    public void setPessoaVeiculos(List<PessoaVeiculo> pessoaVeiculos) {
        this.pessoaVeiculos = pessoaVeiculos;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.Pessoa[ id=" + id + " ]";
    }
    
}
