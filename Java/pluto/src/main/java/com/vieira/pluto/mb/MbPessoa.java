/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.business.NgEndereco;
import com.vieira.pluto.dao.*;
import com.vieira.pluto.entity.*;
import com.vieira.pluto.enums.SimNao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.vieira.pluto.exception.HandledException;
import com.vieira.pluto.util.Strings;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;

import static java.util.Objects.nonNull;

/**
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbPessoa extends BasicMb {

    @Inject
    private NgEndereco ngEndereco;
    @Inject
    private UfDao ufDao;
    @Inject
    private FabricanteDao fabricanteDao;
    @Inject
    private ModeloVeiculoDao modeloVeiculoDao;
    @Inject
    private CorDao corDao;
    @Inject
    private MunicipioDao municipioDao;
    @Inject
    private SexoDao sexoDao;
    @Inject
    private EstadoCivilDao estadoCivilDao;
    @Inject
    private TipoEnderecoDao tipoEnderecoDao;
    @Inject
    private TipoTelefoneDao tipoTelefoneDao;
    @Inject
    private TipoPessoaDao tipoPessoaDao;
    @Inject
    private PessoaDao pessoaDao;
    private Pessoa pessoa;
    private Endereco endereco;
    private Email email;
    private Telefone telefone;
    private PessoaVeiculo pessoaVeiculo;
    private Long idUf;
    private Long idFabricante;
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
    private List<TipoPessoa> tiposPessoa;
    private List<Fabricante> fabricantes;
    private List<ModeloVeiculo> modelosVeiculo;
    private List<Cor> cores;
    private List<PessoaVeiculo> pessoaVeiculos;
    private SimNao[] simNao;
    private TipoPessoa tipoPessoa;
    private Boolean renderTipoPessoa;

    @PostConstruct
    public void init() {
        idUf = 42L;
        decremental = 0L;
        renderTipoPessoa = true;
        tipoPessoa = new TipoPessoa(1L);
        ufs = ufDao.getAll();
        sexos = sexoDao.getAll();
        estadosCivis = estadoCivilDao.getAll();
        tiposEndereco = tipoEnderecoDao.getAll();
        tiposTelefone = tipoTelefoneDao.getAll();
        tiposPessoa = tipoPessoaDao.getAll();
        fabricantes = fabricanteDao.getAllAtivos();
        cores = corDao.getAll();
        simNao = SimNao.values();
        if (!fabricantes.isEmpty()){
            idFabricante = fabricantes.get(0).getId();
        } else {
            idFabricante = null;
        }
        novaPessoa();
    }

    public void novaPessoa() {
        pessoa = new Pessoa();
        pessoa.setTipoPessoa(tipoPessoa);
        enderecos = new ArrayList<>();
        emails = new ArrayList<>();
        telefones = new ArrayList<>();
        pessoaVeiculos = new ArrayList<>();
        novoEndereco();
        alterarMunicipios();
        novoEmail();
        novoTelefone();
        novaPessoaVeiculo();
        alterarModelosVeiculo();
    }

    private void novaPessoaVeiculo() {
        pessoaVeiculo = new PessoaVeiculo(--decremental);
        pessoaVeiculo.setModeloVeiculo(new ModeloVeiculo());
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
        if (nonNull(endereco.getMunicipio().getId()) && !municipios.contains(endereco.getMunicipio())) {
            municipios.add(endereco.getMunicipio());
        }
    }

    public void alterarModelosVeiculo() {
        modelosVeiculo = modeloVeiculoDao.getByIdFabricante(idFabricante);
        if (nonNull(pessoaVeiculo.getModeloVeiculo().getId()) && !modelosVeiculo.contains(pessoaVeiculo.getModeloVeiculo())) {
            modelosVeiculo.add(pessoaVeiculo.getModeloVeiculo());
        }
    }

    public void buscarCpfCnpj() {
        String cpfCnpj = Strings.apenasNumeros(pessoa.getCpfCnpj());
        if (pessoa.getTipoPessoa().equals(new TipoPessoa(1L)) && cpfCnpj.length() != 11) {
            pessoa.setCpfCnpj("");
            return;
        } else if (pessoa.getTipoPessoa().equals(new TipoPessoa(2L)) && cpfCnpj.length() != 14) {
            pessoa.setCpfCnpj("");
            return;
        }
        setPessoaCompleta(pessoaDao.getByCpfCnpj(pessoa));
        alterarMunicipios();
    }

    public void buscarCep() {
        endereco = ngEndereco.buscarCEP(endereco);
        idUf = endereco.getMunicipio().getUf().getId();
        alterarMunicipios();
        addInfoMessage("CEP consultado com sucesso!");
    }

    public void editarEndereco(Endereco endereco) {
        endereco = Endereco.class.cast(Hibernate.unproxy(endereco));
        idUf = endereco.getMunicipio().getUf().getId();
        enderecoPrincipal = isEnderecoPrincipal(endereco);
        setEndereco(endereco);
        alterarMunicipios();
    }

    public void excluirEndereco(Endereco endereco) {
        enderecos.remove(endereco);
    }

    public void salvarEndereco() {
        if (Strings.isNullOrEmpty(endereco.getCep())){
            throw new HandledException("Campo CEP é obriatório");
        }
        if (Objects.isNull(endereco.getMunicipio())){
            throw new HandledException("Campo Município é obriatório");
        }

        if (enderecos.isEmpty() || enderecoPrincipal) {
            pessoa.setEndereco(endereco);
        }
        enderecos.remove(endereco);
        enderecos.add(endereco);
        novoEndereco();
    }

    public void editarEmail(Email email) {
        email = Email.class.cast(Hibernate.unproxy(email));
        setEmail(email);
        emailPrincipal = isEmailPrincipal(email);
    }

    public void excluirEmail(Email email) {
        emails.remove(email);
    }

    public void salvarEmail() {
        if (Strings.isNullOrEmpty(email.getEndereco())){
            throw new HandledException("Campo Email é obriatório");
        }

        if (emails.isEmpty() || emailPrincipal) {
            pessoa.setEmail(email);
        }
        emails.remove(email);
        emails.add(email);
        novoEmail();
    }

    public void editarTelefone(Telefone telefone) {
        telefone = Telefone.class.cast(Hibernate.unproxy(telefone));
        setTelefone(telefone);
        telefonePrincipal = isTelefonePrincipal(telefone);
    }

    public void excluirTelefone(Telefone telefone) {
        telefones.remove(telefone);
    }

    public void salvarTelefone() {
        if (Strings.isNullOrEmpty(telefone.getNumero())){
            throw new HandledException("Campo Numero é obriatório");
        }

        if (telefones.isEmpty() || telefonePrincipal) {
            pessoa.setTelefone(telefone);
        }
        telefones.remove(telefone);
        telefones.add(telefone);
        novoTelefone();
    }

    public void editarPessoaVeiculo(PessoaVeiculo pessoaVeiculo) {
        pessoaVeiculo = PessoaVeiculo.class.cast(Hibernate.unproxy(pessoaVeiculo));
        setPessoaVeiculo(pessoaVeiculo);
    }

    public void excluirPessoaVeiculo(PessoaVeiculo pessoaVeiculo) {
        pessoaVeiculos.remove(pessoaVeiculo);
    }

    public void salvarPessoaVeiculo() {
        if (Strings.isNullOrEmpty(pessoaVeiculo.getPlaca())){
            throw new HandledException("Campo Placa é obriatório");
        }
        if (Objects.isNull(pessoaVeiculo.getModeloVeiculo())){
            throw new HandledException("Campo Modelo é obriatório");
        }
        if (Objects.isNull(pessoaVeiculo.getCor())){
            throw new HandledException("Campo Cor é obriatório");
        }

        pessoaVeiculos.remove(pessoaVeiculo);
        pessoaVeiculos.add(pessoaVeiculo);
        novaPessoaVeiculo();
    }

    public Pessoa getPessoaCompleta() {
        pessoa.setEnderecoList(enderecos);
        pessoa.setEmailList(emails);
        pessoa.setTelefoneList(telefones);
        pessoa.setPessoaVeiculos(pessoaVeiculos);
        return pessoa;
    }

    public void setPessoaCompleta(Pessoa pessoa) {
        this.pessoa = Pessoa.class.cast(Hibernate.unproxy(pessoa));

        Hibernate.initialize(this.pessoa.getEnderecoList());
        Hibernate.initialize(this.pessoa.getTelefoneList());
        Hibernate.initialize(this.pessoa.getEmailList());
        Hibernate.initialize(this.pessoa.getPessoaVeiculos());

        this.enderecos = this.pessoa.getEnderecoList();
        this.telefones = this.pessoa.getTelefoneList();
        this.emails = this.pessoa.getEmailList();
        this.pessoaVeiculos = this.pessoa.getPessoaVeiculos();
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
        if (nonNull(pessoa)) {
            pessoa.setTipoPessoa(tipoPessoa);
        }
        renderTipoPessoa = false;
    }

    public Boolean getRenderCpf() {
        return pessoa.getTipoPessoa().equals(new TipoPessoa(1L));
    }

    public Boolean getRenderCnpj() {
        return pessoa.getTipoPessoa().equals(new TipoPessoa(2L));
    }

    public Boolean getRenderTipoPessoa() {
        return renderTipoPessoa;
    }

    public void setRenderTipoPessoa(Boolean renderTipoPessoa) {
        this.renderTipoPessoa = renderTipoPessoa;
    }

    public List<TipoPessoa> getTiposPessoa() {
        return tiposPessoa;
    }

    public void setTiposPessoa(List<TipoPessoa> tiposPessoa) {
        this.tiposPessoa = tiposPessoa;
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

    public PessoaVeiculo getPessoaVeiculo() {
        return pessoaVeiculo;
    }

    public void setPessoaVeiculo(PessoaVeiculo pessoaVeiculo) {
        this.pessoaVeiculo = pessoaVeiculo;
    }

    public Long getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Long idFabricante) {
        this.idFabricante = idFabricante;
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

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    public List<ModeloVeiculo> getModelosVeiculo() {
        return modelosVeiculo;
    }

    public void setModelosVeiculo(List<ModeloVeiculo> modelosVeiculo) {
        this.modelosVeiculo = modelosVeiculo;
    }

    public List<Cor> getCores() {
        return cores;
    }

    public void setCores(List<Cor> cores) {
        this.cores = cores;
    }

    public List<PessoaVeiculo> getPessoaVeiculos() {
        return pessoaVeiculos;
    }

    public void setPessoaVeiculos(List<PessoaVeiculo> pessoaVeiculos) {
        this.pessoaVeiculos = pessoaVeiculos;
    }

    public SimNao[] getSimNao() {
        return simNao;
    }

    public void setSimNao(SimNao[] simNao) {
        this.simNao = simNao;
    }

}
