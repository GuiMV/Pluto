package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.entity.ItemComercializavel;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaServico extends BasicMb {

    @Inject
    private ItemComercializavelDao itemComercializavelDao;
    private List<ItemComercializavel> servicos;

    @PostConstruct
    public void init(){
        servicos = itemComercializavelDao.getAllServicosAtivos();
    }

    public void editar(ItemComercializavel servico){
        putOnFlash(PROPERTY.SERVICO_EDITAR.name(), servico.getId());
        redirectOnContextPath("/pages/servico/cadastro/cadastroServico.xhtml");
    }

    public void excluir(ItemComercializavel servico){
        servico.setDataExclusao(new Date());
        itemComercializavelDao.edit(servico);
        servicos.remove(servico);
    }

    public List<ItemComercializavel> getServicos() {
        return servicos;
    }
}
