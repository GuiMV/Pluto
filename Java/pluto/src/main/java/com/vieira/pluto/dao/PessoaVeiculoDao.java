package com.vieira.pluto.dao;

import com.vieira.pluto.entity.PessoaVeiculo;
import com.vieira.pluto.persistence.GenericDao;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

public class PessoaVeiculoDao extends GenericDao<PessoaVeiculo> {


    public void save(PessoaVeiculo pessoaVeiculo) {
        if (Objects.isNull(pessoaVeiculo.getId()) || pessoaVeiculo.getId() <= 0) {
            pessoaVeiculo.setId(null);
            add(pessoaVeiculo);
        } else {
            edit(pessoaVeiculo);
        }
    }


    public void removerVeiculosForaLista(Long idPessoa, List<PessoaVeiculo> pessoaVeiculos) {
        StringBuilder sb = new StringBuilder("DELETE FROM PessoaVeiculo\n");
        sb.append("WHERE pessoa.id = ").append(idPessoa).append("\n");
        if(!pessoaVeiculos.isEmpty()){
            sb.append("AND id not in (").append(pessoaVeiculos.get(0).getId());
            for (int i = 1; i < pessoaVeiculos.size(); i++) {
                PessoaVeiculo pessoaVeiculo = pessoaVeiculos.get(i);
                sb.append(",").append(pessoaVeiculo.getId());
            }
            sb.append(")\n");
        }
        Query query = getEntityManager().createQuery(sb.toString());
        query.executeUpdate();
    }

    public List<PessoaVeiculo> getPessoaVeiculos(Long idCPessoa){
        return getEntities().where(obj -> obj.getPessoa().getId() == idCPessoa).toList();
    }
}
