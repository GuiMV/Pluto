/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FuncionarioDao;
import com.vieira.pluto.entity.Funcionario;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.entity.TipoPessoa;
import com.vieira.pluto.enums.PROPERTY;
import com.vieira.pluto.util.Strings;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbCadastroFuncionario extends BasicMb implements Serializable{

    @Inject
    private FuncionarioDao funcionarioDao;
    @Inject
    private MbPessoa mbPessoa;
    private Funcionario funcionario;

    @PostConstruct
    public void init() {
        mbPessoa.setTipoPessoa(new TipoPessoa(1L));
        Long idFuncionario = getOnSession(PROPERTY.FUNCIONARIO_EDITAR.name(), Long.class);
        removeFromSession(PROPERTY.FUNCIONARIO_EDITAR.name());
        if (isNull(idFuncionario)) {
           novoFuncionario(); 
        } else {
            funcionario = funcionarioDao.get(idFuncionario);
            mbPessoa.setPessoaCompleta(funcionario.getPessoa());
        }
    }

    private void novoFuncionario() {
        funcionario = new Funcionario();
    }

    public void buscarCpfCnpj() {
        Pessoa pessoa = mbPessoa.getPessoa();
        String cpfCnpj = Strings.apenasNumeros(pessoa.getCpfCnpj());
        if (cpfCnpj.length() != 11) {
            pessoa.setCpfCnpj("");
            return;
        }
        Funcionario funcionarioDb = funcionarioDao.getByCpf(cpfCnpj);
        if (isNull(funcionarioDb)) {
            mbPessoa.buscarCpfCnpj();
        } else {
            funcionario = Funcionario.class.cast(Hibernate.unproxy(funcionarioDb));
            mbPessoa.setPessoaCompleta(funcionario.getPessoa());
            if (nonNull(funcionario.getDataExclusao())){
                addWarnMessage("Funcionário inativado com este CPF, ao salvar ele será ativado");
            } else {
                addInfoMessage("Funcionário já cadastrado com este CPF, será realizada a edição");
            }
        }
    }
    
    public void salvar(){
        funcionario.setPessoa(mbPessoa.getPessoaCompleta());
        funcionario.setDataExclusao(null);
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
