/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.*;
import com.vieira.pluto.exception.HandledException;
import com.vieira.pluto.persistence.GenericDao;
import org.jinq.orm.stream.JinqStream;

import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static java.util.Objects.isNull;

/**
 *
 * @author Guilherme
 */
public class PessoaDao extends GenericDao<Pessoa> {

    @Inject
    private EnderecoDao enderecoDao;
    @Inject
    private EmailDao emailDao;
    @Inject
    private TelefoneDao telefoneDao;
    @Inject
    private PessoaVeiculoDao pessoaVeiculoDao;

    public void save(Pessoa pessoa) {
        List<Endereco> enderecos = pessoa.getEnderecoList();
        List<Email> emails = pessoa.getEmailList();
        List<Telefone> telefones = pessoa.getTelefoneList();
        if (enderecos.isEmpty()){
            throw new HandledException("Deve ser inserido ao menos um Endereço");
        }
        if (emails.isEmpty()){
            throw new HandledException("Deve ser inserido ao menos um E-mail");
        }
        if (telefones.isEmpty()){
            throw new HandledException("Deve ser inserido ao menos um Telefone");
        }
        Boolean isPrincipal = false;

        for (Endereco endereco : enderecos) {
            isPrincipal = Objects.equals(endereco, pessoa.getEndereco());
            enderecoDao.save(endereco);
            if (isPrincipal) {
                pessoa.setEndereco(endereco);
            }
        }

        for (Email email : emails) {
            isPrincipal = Objects.equals(email, pessoa.getEmail());
            emailDao.save(email);
            if (isPrincipal) {
                pessoa.setEmail(email);
            }
        }

        for (Telefone telefone : telefones) {
            isPrincipal = Objects.equals(telefone, pessoa.getTelefone());
            telefoneDao.save(telefone);
            if (isPrincipal) {
                pessoa.setTelefone(telefone);
            }
        }

        List<PessoaVeiculo> pessoaVeiculos = pessoa.getPessoaVeiculos();
        pessoa.setPessoaVeiculos(null);
        if (isNull(pessoa.getId()) || pessoa.getId() <= 0) {
            pessoa.setId(null);
            add(pessoa);
        } else {
            edit(pessoa);
        }

        pessoaVeiculoDao.removerVeiculosForaLista(pessoa.getId(), pessoaVeiculos);
        for (PessoaVeiculo pessoaVeiculo : pessoaVeiculos) {
            if (isNull(pessoaVeiculo.getIdPessoa())) {
                pessoaVeiculo.setIdPessoa(pessoa.getId());
            }
            pessoaVeiculoDao.save(pessoaVeiculo);
        }
        pessoa.setPessoaVeiculos(pessoaVeiculos);
    }

    @SuppressWarnings("unchecked")
    public Pessoa getByCpfCnpj(String cpfCnpj){
        JinqStream<Pessoa> select = getEntities().where(pessoa -> pessoa.getCpfCnpj().equals(cpfCnpj));
        return select.findFirst().orElse(null);
    }
    
    public Pessoa getByCpfCnpj(Pessoa pessoa){
        Pessoa newPessoa = getByCpfCnpj(pessoa.getCpfCnpj());
        if (Objects.nonNull(newPessoa)) {
            return newPessoa;
        }
        if (isNull(pessoa.getId())) {
            return pessoa;
        }
        return new Pessoa(pessoa.getCpfCnpj(), pessoa.getTipoPessoa());
    }
}
