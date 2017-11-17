package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ModeloVeiculoDao;
import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.ModeloVeiculo;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaModeloVeiculo extends BasicMb {

    @Inject
    private ModeloVeiculoDao corDao;
    private List<ModeloVeiculo> modelosVeiculo;

    @PostConstruct
    public void init(){
        modelosVeiculo = corDao.getAll();
    }

    public void editar(ModeloVeiculo modeloVeiculo){
        putOnFlash(PROPERTY.MODELO_VEICULO_EDITAR.name(), modeloVeiculo.getId());
        redirectOnContextPath("/pages/modeloVeiculo/cadastro/cadastroModeloVeiculo.xhtml");
    }

    public List<ModeloVeiculo> getModelosVeiculo() {
        return modelosVeiculo;
    }
}
