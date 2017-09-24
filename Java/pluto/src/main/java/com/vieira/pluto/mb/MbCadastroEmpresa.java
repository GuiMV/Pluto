/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.EmpresaDao;
import com.vieira.pluto.entity.Empresa;
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
public class MbCadastroEmpresa extends BasicMb implements Serializable{

    private EmpresaDao empresaDao;
    @Inject
    private MbPessoa mbPessoa;
    private Empresa empresa;

    @PostConstruct
    public void init() {
        empresaDao = new EmpresaDao();
        mbPessoa.setTipoPessoa(new TipoPessoa(2L));
        empresa = getOnSession("empresaEditar", Empresa.class);
        if (Objects.isNull(empresa)) {
           novaEmpresa();
        } else {
            mbPessoa.setPessoaCompleta(empresa.getPessoa());
        }
        removeFromSession("empresaEditar");
    }

    private void novaEmpresa() {
        empresa = new Empresa();
    }
    
    public void salvar(){
        empresa.setPessoa(mbPessoa.getPessoaCompleta());
        empresaDao.save(empresa);
        novaEmpresa();
        mbPessoa.novaPessoa();
        addInfoMessage("Empresa salva com sucesso!");
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
