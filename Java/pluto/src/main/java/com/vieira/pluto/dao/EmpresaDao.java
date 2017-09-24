/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.Empresa;
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
public class EmpresaDao extends GenericDao<Empresa> {

    public void save(Empresa empresa) {
        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = empresa.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(empresa.getId())) {
            add(empresa);
        } else {
            edit(empresa);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Empresa> getAllAtivos() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Empresa> empresa = cq.from(Empresa.class);
        cq.select(cq.from(entityClass));
        cq.where(empresa.get("dataExclusao").isNull());
        Query query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
}
