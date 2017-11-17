package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.ModeloVeiculo;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaProduto extends BasicMb {

    @Inject
    private ItemComercializavelDao itemComercializavelDao;
    private List<ItemComercializavel> produtos;

    @PostConstruct
    public void init(){
        produtos = itemComercializavelDao.getAllProdutosAtivos();
    }

    public void editar(ItemComercializavel produto){
        putOnFlash(PROPERTY.PRODUTO_EDITAR.name(), produto.getId());
        redirectOnContextPath("/pages/produto/cadastro/cadastroProduto.xhtml");
    }

    public void excluir(ItemComercializavel produto){
        produto.setDataExclusao(new Date());
        itemComercializavelDao.edit(produto);
        produtos.remove(produto);
    }

    public List<ItemComercializavel> getProdutos() {
        return produtos;
    }
}
