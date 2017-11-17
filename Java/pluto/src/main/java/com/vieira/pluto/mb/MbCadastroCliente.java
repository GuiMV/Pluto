/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.entity.*;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import com.vieira.pluto.util.Strings;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbCadastroCliente extends BasicMb {

    @Inject
    private ClienteDao clienteDao;
    @Inject
    private MbPessoa mbPessoa;
    private Cliente cliente;
    private boolean isCadastro;

    @PostConstruct
    public void init() {
        Long idCliente = getOnSession(PROPERTY.CLIENTE_EDITAR.name(), Long.class);
        removeFromSession(PROPERTY.CLIENTE_EDITAR.name());
        isCadastro = Objects.isNull(idCliente);
        if (isCadastro) {
           novoCliente(); 
        } else {
            cliente = clienteDao.get(idCliente);
            mbPessoa.setPessoaCompleta(cliente.getPessoa());
        }
    }

    private void novoCliente() {
        cliente = new Cliente();
        cliente.setIdClassificacao(1L);
    }

    public void buscarCpfCnpj() {
        Pessoa pessoa = mbPessoa.getPessoa();
        String cpfCnpj = Strings.apenasNumeros(pessoa.getCpfCnpj());
        if (pessoa.getTipoPessoa().equals(new TipoPessoa(1L)) && cpfCnpj.length() != 11) {
            pessoa.setCpfCnpj("");
            return;
        } else if (pessoa.getTipoPessoa().equals(new TipoPessoa(2L)) && cpfCnpj.length() != 14) {
            pessoa.setCpfCnpj("");
            return;
        }
        Cliente clienteDb = clienteDao.getByCpfCnpj(cpfCnpj);
        if (isNull(clienteDb)) {
            mbPessoa.buscarCpfCnpj();
        } else {
            cliente = Cliente.class.cast(Hibernate.unproxy(clienteDb));
            mbPessoa.setPessoaCompleta(cliente.getPessoa());
            if (nonNull(cliente.getDataExclusao())){
                addWarnMessage("Cliente inativado com este CPF/CNPJ, ao salvar ele será ativado");
            } else {
                addInfoMessage("Cliente já cadastrado com este CPF/CNPJ, será realizada a edição");
            }
        }
    }
    
    public void salvar(){
        cliente.setPessoa(mbPessoa.getPessoaCompleta());
        cliente.setDataExclusao(null);
        clienteDao.save(cliente);
        novoCliente();
        mbPessoa.novaPessoa();
        addInfoMessage("Cliente salvo com sucesso!");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isCadastro() {
        return isCadastro;
    }

    public void setCadastro(boolean cadastro) {
        isCadastro = cadastro;
    }
}
