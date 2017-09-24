/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.EmpresaDao;
import com.vieira.pluto.entity.Empresa;
import com.vieira.pluto.entity.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Guilherme
 */
@ManagedBean
@ViewScoped
public class MbConsultaEmpresa extends BasicMb{

    private EmpresaDao empresaDao;
    private List<Empresa> empresas;

    @PostConstruct
    public void init(){
        empresaDao = new EmpresaDao();
        empresas = empresaDao.getAllAtivos();
    }

    
    public void editar(Empresa empresa){
        putOnSession("empresaEditar", empresa);
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
