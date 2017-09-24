package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.Funcionario;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class FabricanteDao extends GenericDao<Fabricante> {

    public void save(Fabricante fabricante) {
        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = fabricante.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(fabricante.getId())) {
            add(fabricante);
        } else {
            edit(fabricante);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Fabricante> getAllAtivos() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Fabricante> fabricante = cq.from(Fabricante.class);
        cq.select(cq.from(entityClass));
        cq.where(fabricante.get("dataExclusao").isNull());
        Query query = getEntityManager().createQuery(cq);
        return query.getResultList();
    }
}
