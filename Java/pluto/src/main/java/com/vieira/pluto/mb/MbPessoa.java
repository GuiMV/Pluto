/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.business.NgEndereco;
import com.vieira.pluto.dao.EstadoCivilDao;
import com.vieira.pluto.dao.MunicipioDao;
import com.vieira.pluto.dao.SexoDao;
import com.vieira.pluto.dao.TipoEnderecoDao;
import com.vieira.pluto.dao.TipoTelefoneDao;
import com.vieira.pluto.dao.UfDao;
import com.vieira.pluto.entity.Email;
import com.vieira.pluto.entity.Endereco;
import com.vieira.pluto.entity.EstadoCivil;
import com.vieira.pluto.entity.Municipio;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.entity.Sexo;
import com.vieira.pluto.entity.Telefone;
import com.vieira.pluto.entity.TipoEndereco;
import com.vieira.pluto.entity.TipoPessoa;
import com.vieira.pluto.entity.TipoTelefone;
import com.vieira.pluto.entity.Uf;
import com.vieira.pluto.enums.SimNao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbPessoa extends BasicMb implements Serializable {

    private Pessoa pessoa;
    private Endereco endereco;
    private Email email;
    private Telefone telefone;
    private Long idUf;
    private Long decremental;
    private Boolean enderecoPrincipal;
    private Boolean emailPrincipal;
    private Boolean telefonePrincipal;
    private List<Uf> ufs;
    private List<Municipio> municipios;
    private List<Endereco> enderecos;
    private List<Email> emails;
    private List<Telefone> telefones;
    private List<Sexo> sexos;
    private List<EstadoCivil> estadosCivis;
    private List<TipoEndereco> tiposEndereco;
    private List<TipoTelefone> tiposTelefone;
    private SimNao[] simNao;
    private NgEndereco ngEndereco;
    private UfDao ufDao;
    private MunicipioDao municipioDao;
    private SexoDao sexoDao;
    private EstadoCivilDao estadoCivilDao;
    private TipoEnderecoDao tipoEnderecoDao;
    private TipoTelefoneDao tipoTelefoneDao;

    @PostConstruct
    public void init() {
        ngEndereco = new NgEndereco();
        ufDao = new UfDao();
        municipioDao = new MunicipioDao();
        sexoDao = new SexoDao();
        estadoCivilDao = new EstadoCivilDao();
        tipoEnderecoDao = new TipoEnderecoDao();
        tipoTelefoneDao = new TipoTelefoneDao();
        idUf = 42L;
        decremental = 0L;
        ufs = ufDao.getAll();
        sexos = sexoDao.getAll();
        estadosCivis = estadoCivilDao.getAll();
        tiposEndereco = tipoEnderecoDao.getAll();
        tiposTelefone = tipoTelefoneDao.getAll();
        simNao = SimNao.values();
        novaPessoa();
    }

    public void novaPessoa() {
        pessoa = new Pessoa();
        pessoa.setTipoPessoa(new TipoPessoa(1L));
        enderecos = new ArrayList<>();
        emails = new ArrayList<>();
        telefones = new ArrayList<>();
        novoEndereco();
        alterarMunicipios();
        novoEmail();
        novoTelefone();
    }

    private void novoTelefone() {
        telefone = new Telefone(--decremental);
        telefonePrincipal = false;
    }

    private void novoEmail() {
        email = new Email(--decremental);
        emailPrincipal = false;
    }

    private void novoEndereco() {
        Municipio municipio = new Municipio();
        municipio.setUf(new Uf());
        endereco = new Endereco(--decremental);
        endereco.setMunicipio(municipio);
        enderecoPrincipal = false;
    }

    public void alterarMunicipios() {
        municipios = municipioDao.getByIdUf(idUf);
        if (Objects.nonNull(endereco.getMunicipio().getId()) && !municipios.contains(endereco.getMunicipio())) {
            municipios.add(endereco.getMunicipio());
        }
    }

    public void buscarCep() {
        endereco = ngEndereco.buscarCEP(endereco);
        idUf = endereco.getMunicipio().getUf().getId();
        alterarMunicipios();
        addInfoMessage("CEP consultado com sucesso!");
    }

    public void editarEndereco(Endereco endereco) {
        idUf = endereco.getMunicipio().getUf().getId();
        enderecoPrincipal = isEnderecoPrincipal(endereco);
        setEndereco(endereco);
        alterarMunicipios();
    }

    public void excluirEndereco(Endereco endereco) {
        enderecos.remove(endereco);
    }

    public void salvarEndereco() {
        if (enderecos.isEmpty() || enderecoPrincipal) {
            pessoa.setEndereco(endereco);
        }
        enderecos.remove(endereco);
        enderecos.add(endereco);
        novoEndereco();
    }

    public void editarEmail(Email email) {
        setEmail(email);
        emailPrincipal = isEmailPrincipal(email);
    }

    public void excluirEmail(Email email) {
        emails.remove(email);
    }

    public void salvarEmail() {
        if (emails.isEmpty() || emailPrincipal) {
            pessoa.setEmail(email);
        }
        emails.remove(email);
        emails.add(email);
        novoEmail();
    }

    public void editarTelefone(Telefone telefone) {
        setTelefone(telefone);
        telefonePrincipal = isTelefonePrincipal(telefone);
    }

    public void excluirTelefone(Telefone telefone) {
        telefones.remove(telefone);
    }

    public void salvarTelefone() {
        if (telefones.isEmpty() || telefonePrincipal) {
            pessoa.setTelefone(telefone);
        }
        telefones.remove(telefone);
        telefones.add(telefone);
        novoTelefone();
    }

    public Pessoa getPessoaCompleta() {
        pessoa.setEnderecoList(enderecos);
        pessoa.setEmailList(emails);
        pessoa.setTelefoneList(telefones);
        return pessoa;
    }

    public void setPessoaCompleta(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.enderecos = pessoa.getEnderecoList();
        this.telefones = pessoa.getTelefoneList();
        this.emails = pessoa.getEmailList();
    }

    public Boolean isEnderecoPrincipal(Endereco endereco) {
        return Objects.equals(endereco, pessoa.getEndereco());
    }

    public Boolean isEmailPrincipal(Email email) {
        return Objects.equals(email, pessoa.getEmail());
    }

    public Boolean isTelefonePrincipal(Telefone telefone) {
        return Objects.equals(telefone, pessoa.getTelefone());
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Long getIdUf() {
        return idUf;
    }

    public void setIdUf(Long idUf) {
        this.idUf = idUf;
    }

    public Boolean getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(Boolean enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }

    public Boolean getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(Boolean emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public Boolean getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(Boolean telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public List<Uf> getUfs() {
        return ufs;
    }

    public void setUfs(List<Uf> ufs) {
        this.ufs = ufs;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Sexo> getSexos() {
        return sexos;
    }

    public void setSexos(List<Sexo> sexos) {
        this.sexos = sexos;
    }

    public List<EstadoCivil> getEstadosCivis() {
        return estadosCivis;
    }

    public void setEstadosCivis(List<EstadoCivil> estadosCivis) {
        this.estadosCivis = estadosCivis;
    }

    public List<TipoEndereco> getTiposEndereco() {
        return tiposEndereco;
    }

    public void setTiposEndereco(List<TipoEndereco> tiposEndereco) {
        this.tiposEndereco = tiposEndereco;
    }

    public List<TipoTelefone> getTiposTelefone() {
        return tiposTelefone;
    }

    public void setTiposTelefone(List<TipoTelefone> tiposTelefone) {
        this.tiposTelefone = tiposTelefone;
    }

    public SimNao[] getSimNao() {
        return simNao;
    }

    public void setSimNao(SimNao[] simNao) {
        this.simNao = simNao;
    }

}
