package com.vieira.pluto.dao;

import com.vieira.pluto.entity.ModeloVeiculo;
import com.vieira.pluto.persistence.GenericDao;

import javax.persistence.Query;
import java.util.List;

public class ModeloVeiculoDao extends GenericDao<ModeloVeiculo> {

    @SuppressWarnings("unchecked")
    public List<ModeloVeiculo> getByIdFabricante(Long idFabricante) {
        String sql = String.format("FROM ModeloVeiculo WHERE fabricante.id = %d", idFabricante);
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
