package com.vieira.pluto.mb;

import com.vieira.pluto.dao.CorDao;
import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.dao.ModeloVeiculoDao;
import com.vieira.pluto.entity.Cor;
import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.ModeloVeiculo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbCadastroModeloVeiculo extends BasicMb {

    private ModeloVeiculo modeloVeiculo;
    private List<Fabricante> fabricantes;

    @PostConstruct
    public void init(){
        modeloVeiculo = new ModeloVeiculo();
        FabricanteDao fabricanteDao = new FabricanteDao();
        fabricantes = fabricanteDao.getAllAtivos();
    }

    public void salvar(){
        ModeloVeiculoDao modeloVeiculoDao = new ModeloVeiculoDao();
        modeloVeiculoDao.add(modeloVeiculo);
        modeloVeiculo = new ModeloVeiculo();
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
