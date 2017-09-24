/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.TipoPessoa;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbCadastroCliente extends BasicMb implements Serializable{

    private Cliente cliente;
    private ClienteDao clienteDao;
    @Inject
    private MbPessoa mbPessoa;

    @PostConstruct
    public void init() {
        clienteDao = new ClienteDao();
        mbPessoa.setTipoPessoa(new TipoPessoa(1L));
        cliente = getOnSession("clienteEditar", Cliente.class);
        if (Objects.isNull(cliente)) {
           novoCliente(); 
        } else {
            mbPessoa.setPessoaCompleta(cliente.getPessoa());
        }
        removeFromSession("clienteEditar");
    }

    private void novoCliente() {
        cliente = new Cliente();
    }
    
    public void salvar(){
        cliente.setPessoa(mbPessoa.getPessoaCompleta());
        clienteDao.save(cliente);
        novoCliente();
        mbPessoa.novaPessoa();
        addInfoMessage("Cliente salvo com sucesso!");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
