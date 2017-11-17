package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.TipoItemComercializavel;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Named
@ViewScoped
public class MbCadastroProduto extends BasicMb {

    @Inject
    private FabricanteDao fabricanteDao;
    @Inject
    private ItemComercializavelDao itemComercializavelDao;
    private ItemComercializavel produto;
    private List<Fabricante> fabricantes;

    @PostConstruct
    public void init(){
        Long idProduto = getOnFlash(PROPERTY.PRODUTO_EDITAR.name(), Long.class);
        if(isNull(idProduto)){
            novoProduto();
        } else {
            produto = itemComercializavelDao.get(idProduto);
        }
        fabricantes = fabricanteDao.getAllAtivos();
    }

    private void novoProduto() {
        produto = new ItemComercializavel();
        produto.setTipoItemComercializavel(new TipoItemComercializavel(1L));
    }

    public void buscarCean(){
        ItemComercializavel produtoDb = itemComercializavelDao.getByCean(produto.getCean());
        if (nonNull(produtoDb)) {
            produto = ItemComercializavel.class.cast(Hibernate.unproxy(produtoDb));
            if (nonNull(produto.getDataExclusao())){
                addWarnMessage("Produto inativado com este CEAN, ao salvar ele será ativado");
            } else {
                addInfoMessage("Produto já cadastrado com este CEAN, será realizada a edição");
            }
        }
    }

    public void salvar(){
        produto.setDataExclusao(null);
        itemComercializavelDao.save(produto);
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
