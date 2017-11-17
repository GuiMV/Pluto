/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;
import org.jinq.orm.stream.JinqStream;

import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Guilherme
 */
public class ClienteDao extends GenericDao<Cliente> {

    @Inject
    private PessoaDao pessoaDao;

    public void save(Cliente cliente) {
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
        JinqStream<Cliente> query = getEntities().where(cliente -> cliente.getDataExclusao() == null);
        query = query.sortedBy(cliente -> cliente.getPessoa().getNomeFantasia());
        return query.toList();
    }

    public Cliente getByCpfCnpj(String cpfCnpj){
        return getEntities().where(obj -> obj.getPessoa().getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
    }

    public void removerClassificacao(){
        String sql = "UPDATE Cliente SET idClassificacao = 1";
        Query query = getEntityManager().createQuery(sql);
        query.executeUpdate();
    }
}
