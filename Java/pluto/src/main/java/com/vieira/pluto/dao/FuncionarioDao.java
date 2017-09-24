/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Funcionario ;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;

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
public class FuncionarioDao extends GenericDao<Funcionario > {

    public void save(Funcionario  funcionario ) {
        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = funcionario .getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(funcionario .getId())) {
            add(funcionario );
        } else {
            edit(funcionario );
        }
    }

    @SuppressWarnings("unchecked")
    public List<Funcionario > getAllAtivos() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Funcionario > funcionario  = cq.from(Funcionario .class);
        cq.select(cq.from(entityClass));
        cq.where(funcionario .get("dataExclusao").isNull());
        Query query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
}
