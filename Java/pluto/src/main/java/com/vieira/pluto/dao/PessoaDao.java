/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Email;
import com.vieira.pluto.entity.Endereco;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.entity.Telefone;
import com.vieira.pluto.persistence.GenericDao;
import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(pessoa.getId()) || pessoa.getId() <= 0) {
            pessoa.setId(null);
            add(pessoa);
        } else {
            edit(pessoa);
        }
    }
}
