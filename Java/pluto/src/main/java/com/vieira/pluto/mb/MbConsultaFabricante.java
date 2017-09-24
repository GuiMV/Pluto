package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.entity.Fabricante;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbConsultaFabricante extends BasicMb{
    
    private List<Fabricante> fabricantes;
    private FabricanteDao fabricanteDao;
    
    @PostConstruct
    public void init(){
        fabricanteDao = new FabricanteDao();
        fabricantes = fabricanteDao.getAllAtivos();
    }

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
    
    public void editar(Fabricante fabricante){
        putOnSession("fabricanteEditar", fabricante);
        redirectOnContextPath("/pages/fabricante/cadastro/cadastroFabricante.xhtml");
    }
    
    public void excluir(Fabricante fabricante){
        fabricante.setDataExclusao(new Date());
        fabricanteDao.edit(fabricante);
        fabricantes.remove(fabricante);
    }
}
