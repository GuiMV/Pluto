package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.dao.ModeloVeiculoDao;
import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.ModeloVeiculo;
import com.vieira.pluto.entity.TipoItemComercializavel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbCadastroProduto extends BasicMb {

    private ItemComercializavel produto;
    private List<Fabricante> fabricantes;

    @PostConstruct
    public void init(){
        novoProduto();
        FabricanteDao fabricanteDao = new FabricanteDao();
        fabricantes = fabricanteDao.getAllAtivos();
    }

    private void novoProduto() {
        produto = new ItemComercializavel();
        produto.setTipoItemComercializavel(new TipoItemComercializavel(1L));
        produto.setMargemLucro(0.0);
    }

    public void salvar(){
        ItemComercializavelDao modeloVeiculoDao = new ItemComercializavelDao();
        modeloVeiculoDao.save(produto);
        novoProduto();
        addInfoMessage("Produto salvo com sucesso!");
    }

    public ItemComercializavel getProduto() {
        return produto;
    }

    public void setProduto(ItemComercializavel produto) {
        this.produto = produto;
    }

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }
}
