package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.entity.ItemComercializavel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbConsultaServico extends BasicMb {

    private List<ItemComercializavel> servicos;

    @PostConstruct
    public void init(){
        ItemComercializavelDao itemComercializavelDao = new ItemComercializavelDao();
        servicos = itemComercializavelDao.getAllServicosAtivos();
    }

    public List<ItemComercializavel> getServicos() {
        return servicos;
    }
}
