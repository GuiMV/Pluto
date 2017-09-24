/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;
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
public class ClienteDao extends GenericDao<Cliente> {

    public void save(Cliente cliente) {
        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = cliente.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(cliente.getId())) {
            add(cliente);
        } else {
            edit(cliente);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> getAllAtivos() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Cliente> cliente = cq.from(Cliente.class);
        cq.select(cq.from(entityClass));
        cq.where(cliente.get("dataExclusao").isNull());
        Query query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
}
