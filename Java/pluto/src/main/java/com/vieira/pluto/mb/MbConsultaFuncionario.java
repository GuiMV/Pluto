/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FuncionarioDao;
import com.vieira.pluto.entity.Funcionario;
import com.vieira.pluto.entity.Usuario;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbConsultaFuncionario extends BasicMb{

    @Inject
    private FuncionarioDao funcionarioDao;
    private List<Funcionario> funcionarios;
    
    @PostConstruct
    public void init(){
        funcionarios = funcionarioDao.getAllAtivos();
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    public void editar(Funcionario funcionario){
        putOnSession(PROPERTY.FUNCIONARIO_EDITAR.name(), funcionario.getId());
        redirectOnContextPath("/pages/funcionario/cadastro/cadastroFuncionario.xhtml");
    }
    
    public void excluir(Funcionario funcionario){
        funcionario.setDataExclusao(new Date());
        funcionarioDao.edit(funcionario);
        funcionarios.remove(funcionario);
    }
}
