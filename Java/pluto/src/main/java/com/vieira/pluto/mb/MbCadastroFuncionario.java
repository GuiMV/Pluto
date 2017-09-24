/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FuncionarioDao;
import com.vieira.pluto.entity.Funcionario;
import com.vieira.pluto.entity.TipoPessoa;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbCadastroFuncionario extends BasicMb implements Serializable{

    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao;
    @Inject
    private MbPessoa mbPessoa;

    @PostConstruct
    public void init() {
        funcionarioDao = new FuncionarioDao();
        mbPessoa.setTipoPessoa(new TipoPessoa(1L));
        funcionario = getOnSession("funcionarioEditar", Funcionario.class);
        if (Objects.isNull(funcionario)) {
           novoFuncionario(); 
        } else {
            mbPessoa.setPessoaCompleta(funcionario.getPessoa());
        }
        removeFromSession("funcionarioEditar");
    }

    private void novoFuncionario() {
        funcionario = new Funcionario();
    }
    
    public void salvar(){
        funcionario.setPessoa(mbPessoa.getPessoaCompleta());
        funcionarioDao.save(funcionario);
        novoFuncionario();
        mbPessoa.novaPessoa();
        addInfoMessage("Funcionario salvo com sucesso!");
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
