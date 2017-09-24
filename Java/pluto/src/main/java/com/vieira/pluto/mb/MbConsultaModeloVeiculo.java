package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ModeloVeiculoDao;
import com.vieira.pluto.entity.ModeloVeiculo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbConsultaModeloVeiculo extends BasicMb {

    private List<ModeloVeiculo> modelosVeiculo;

    @PostConstruct
    public void init(){
        ModeloVeiculoDao corDao = new ModeloVeiculoDao();
        modelosVeiculo = corDao.getAll();
    }

    public List<ModeloVeiculo> getModelosVeiculo() {
        return modelosVeiculo;
    }
}
