package com.vieira.pluto.mb;

import com.vieira.pluto.dao.CorDao;
import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.dao.ModeloVeiculoDao;
import com.vieira.pluto.entity.Cor;
import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.ModeloVeiculo;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.Objects.isNull;

@Named
@ViewScoped
public class MbCadastroModeloVeiculo extends BasicMb {

    @Inject
    private FabricanteDao fabricanteDao;
    @Inject
    private ModeloVeiculoDao modeloVeiculoDao;
    private ModeloVeiculo modeloVeiculo;
    private List<Fabricante> fabricantes;

    @PostConstruct
    public void init() {
        Long idModeloVeiculo = getOnFlash(PROPERTY.MODELO_VEICULO_EDITAR.name(), Long.class);
        if(isNull(idModeloVeiculo)){
            novoModeloVeiculo();
        } else {
            modeloVeiculo = modeloVeiculoDao.get(idModeloVeiculo);
        }
        fabricantes = fabricanteDao.getAllAtivos();
    }

    private void novoModeloVeiculo() {
        modeloVeiculo = new ModeloVeiculo();
    }

    public void salvar() {
        modeloVeiculoDao.save(modeloVeiculo);
        novoModeloVeiculo();
        addInfoMessage("Modelo de Veiculo salvo com sucesso!");
    }

    public ModeloVeiculo getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(ModeloVeiculo modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
}
