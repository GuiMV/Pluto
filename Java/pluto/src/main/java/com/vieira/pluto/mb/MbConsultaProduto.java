package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.ModeloVeiculo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbConsultaProduto extends BasicMb {

    private List<ItemComercializavel> produtos;

    @PostConstruct
    public void init(){
        ItemComercializavelDao itemComercializavelDao = new ItemComercializavelDao();
        produtos = itemComercializavelDao.getAllProdutosAtivos();
    }

    public List<ItemComercializavel> getProdutos() {
        return produtos;
    }
}
