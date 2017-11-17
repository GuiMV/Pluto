package com.vieira.pluto.dao;

import com.vieira.pluto.entity.ModeloVeiculo;
import com.vieira.pluto.persistence.GenericDao;

import javax.persistence.Query;
import java.util.List;

import static java.util.Objects.isNull;

public class ModeloVeiculoDao extends GenericDao<ModeloVeiculo> {

    @SuppressWarnings("unchecked")
    public List<ModeloVeiculo> getByIdFabricante(Long idFabricante) {
        return getEntities().where(obj -> obj.getFabricante().getId() == idFabricante).toList();
    }

    public void save(ModeloVeiculo modeloVeiculo){
        if (isNull(modeloVeiculo.getId())){
            add(modeloVeiculo);
        } else {
            edit(modeloVeiculo);
        }
    }

}
