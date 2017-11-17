/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Funcionario ;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class FuncionarioDao extends GenericDao<Funcionario> {

    @Inject
    private PessoaDao pessoaDao;

    public void save(Funcionario  funcionario ) {
        Pessoa pessoa = funcionario .getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(funcionario .getId())) {
            add(funcionario );
        } else {
            edit(funcionario );
        }
    }

    public List<Funcionario> getAllAtivos() {
        return getEntities().where(obj -> obj.getDataExclusao() == null).toList();
    }

    public Funcionario getByCpf(String cpf){
        return getEntities().where(obj -> obj.getPessoa().getCpfCnpj().equals(cpf)).findFirst().orElse(null);
    }
}
