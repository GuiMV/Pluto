package com.vieira.pluto.mb;

import com.vieira.pluto.dao.CorDao;
import com.vieira.pluto.entity.Cor;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.entity.FormaPagamento;
import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaCor extends BasicMb {

    @Inject
    private CorDao corDao;
    private List<Cor> cores;

    @PostConstruct
    public void init(){
        cores = corDao.getAll();
    }

    public void editar(Cor cor){
        putOnFlash(PROPERTY.COR_EDITAR.name(), cor.getId());
        redirectOnContextPath("/pages/cor/cadastro/cadastroCor.xhtml");
    }

    public List<Cor> getCores() {
        return cores;
    }
}
