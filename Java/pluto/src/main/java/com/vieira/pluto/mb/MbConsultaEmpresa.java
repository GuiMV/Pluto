package com.vieira.pluto.mb;

import com.vieira.pluto.dao.EmpresaDao;
import com.vieira.pluto.entity.Empresa;
import com.vieira.pluto.entity.Usuario;
import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaEmpresa extends BasicMb{

    @Inject
    private EmpresaDao empresaDao;
    private List<Empresa> empresas;

    @PostConstruct
    public void init(){
        empresas = empresaDao.getAllAtivos();
    }

    
    public void editar(Empresa empresa){
        putOnSession(PROPERTY.EMPRESA_EDITAR.name(), empresa.getId());
        redirectOnContextPath("/pages/empresa/cadastro/cadastroEmpresa.xhtml");
    }
    
    public void excluir(Empresa cliente){
        cliente.setDataExclusao(new Date());
        empresaDao.edit(cliente);
        empresas.remove(cliente);
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
