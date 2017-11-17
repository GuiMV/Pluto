package com.vieira.pluto.dao;

import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.TipoItemComercializavel;
import com.vieira.pluto.persistence.GenericDao;
import org.jinq.orm.stream.JinqStream;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ItemComercializavelDao extends GenericDao<ItemComercializavel> {

    public List<ItemComercializavel> getAllProdutosAtivos() {
        return getsAtivos(1L);
    }

    public List<ItemComercializavel> getAllServicosAtivos() {
        return getsAtivos(2L);
    }

    public List<ItemComercializavel> getsAtivos(Long idTipo) {
        JinqStream<ItemComercializavel> select = getEntities().where(itemComercializavel -> itemComercializavel.getDataExclusao() == null && itemComercializavel.getTipoItemComercializavel().getId() == idTipo);
        return select.toList();
    }

    public void save(ItemComercializavel itemComercializavel){
        itemComercializavel.setNomeCompleto(itemComercializavel.getNome() + " " + itemComercializavel.getApresentacao());
        if (isNull(itemComercializavel.getId())){
            add(itemComercializavel);
        }  else  {
            edit(itemComercializavel);
        }
    }

    public ItemComercializavel getByCean(String cean){
        if(isNull(cean)){
            return null;
        }
        return getEntities().where(obj -> obj.getCean().equals(cean)).findFirst().orElse(null);
    }

    public ItemComercializavel getByCean(ItemComercializavel itemComercializavel){
        ItemComercializavel newItemComercializavel = getByCean(itemComercializavel.getCean());
        if (nonNull(newItemComercializavel)) {
            return newItemComercializavel;
        }
        if (isNull(itemComercializavel.getId())) {
            return itemComercializavel;
        }
        return new ItemComercializavel(itemComercializavel.getCean(), itemComercializavel.getTipoItemComercializavel());
    }
}
