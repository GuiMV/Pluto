package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.entity.Fabricante;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaFabricante extends BasicMb{

    @Inject
    private FabricanteDao fabricanteDao;
    private List<Fabricante> fabricantes;
    
    @PostConstruct
    public void init(){
        fabricantes = fabricanteDao.getAllAtivos();
    }

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
    
    public void editar(Fabricante fabricante){
        putOnSession(PROPERTY.FABRICANTE_EDITAR.name(), fabricante.getId());
        redirectOnContextPath("/pages/fabricante/cadastro/cadastroFabricante.xhtml");
    }
    
    public void excluir(Fabricante fabricante){
        fabricante.setDataExclusao(new Date());
        fabricanteDao.edit(fabricante);
        fabricantes.remove(fabricante);
    }
}
