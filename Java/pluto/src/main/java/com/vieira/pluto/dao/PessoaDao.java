/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.*;
import com.vieira.pluto.persistence.GenericDao;
import org.jinq.orm.stream.JinqStream;

import java.util.List;
import java.util.Objects;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Guilherme
 */
public class PessoaDao extends GenericDao<Pessoa> {

    public void save(Pessoa pessoa) {
        Boolean isPrincipal = false;

        EnderecoDao enderecoDao = new EnderecoDao();
        List<Endereco> enderecos = pessoa.getEnderecoList();
        for (Endereco endereco : enderecos) {
            isPrincipal = Objects.equals(endereco, pessoa.getEndereco());
            enderecoDao.save(endereco);
            if (isPrincipal) {
                pessoa.setEndereco(endereco);
            }
        }

        EmailDao emailDao = new EmailDao();
        List<Email> emails = pessoa.getEmailList();
        for (Email email : emails) {
            isPrincipal = Objects.equals(email, pessoa.getEmail());
            emailDao.save(email);
            if (isPrincipal) {
                pessoa.setEmail(email);
            }
        }
        
        TelefoneDao telefoneDao = new TelefoneDao();
        List<Telefone> telefones = pessoa.getTelefoneList();
        for (Telefone telefone : telefones) {
            isPrincipal = Objects.equals(telefone, pessoa.getTelefone());
            telefoneDao.save(telefone);
            if (isPrincipal) {
                pessoa.setTelefone(telefone);
            }
        }

        List<PessoaVeiculo> pessoaVeiculos = pessoa.getPessoaVeiculos();
        pessoa.setPessoaVeiculos(null);
        if (Objects.isNull(pessoa.getId()) || pessoa.getId() <= 0) {
            pessoa.setId(null);
            add(pessoa);
        } else {
            edit(pessoa);
        }

        PessoaVeiculoDao pessoaVeiculoDao = new PessoaVeiculoDao();
        pessoaVeiculoDao.removerVeiculosForaLista(pessoa.getId(), pessoaVeiculos);
        for (PessoaVeiculo pessoaVeiculo : pessoaVeiculos) {
            pessoaVeiculo.setPessoa(pessoa);
            pessoaVeiculoDao.save(pessoaVeiculo);
        }
        pessoa.setPessoaVeiculos(pessoaVeiculos);
    }

    @SuppressWarnings("unchecked")
    public Pessoa getByCpfCnpj(String cpfCnpj){
        /*CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Pessoa> root = cq.from(Pessoa.class);
        cq.select(cq.from(entityClass));
        cq.where(cb.equal(root.get("cpfCnpj"), cpfCnpj));
        Query query = getEntityManager().createQuery(cq);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return Pessoa.class.cast(query.getSingleResult());*/
        JinqStream<Pessoa> select = getEntities().where(pessoa -> pessoa.getCpfCnpj().equals(cpfCnpj));
        return select.findFirst().orElse(null);
    }
    
    public Pessoa getByCpfCnpj(Pessoa pessoa){
        Pessoa newPessoa = getByCpfCnpj(pessoa.getCpfCnpj());
        if (Objects.nonNull(newPessoa)) {
            return newPessoa;
        }
        if (Objects.isNull(pessoa.getId())) {
            return pessoa;
        }
        return new Pessoa(pessoa.getCpfCnpj(), pessoa.getTipoPessoa());
    }
}
