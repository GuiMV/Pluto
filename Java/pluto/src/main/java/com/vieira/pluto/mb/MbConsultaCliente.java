/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.entity.Cliente;

import javax.annotation.PostConstruct;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbConsultaCliente extends BasicMb{

    @Inject
    private ClienteDao clienteDao;
    private List<Cliente> clientes;
    
    @PostConstruct
    public void init(){
        clientes = clienteDao.getAllAtivos();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public void editar(Cliente cliente){
        putOnSession(PROPERTY.CLIENTE_EDITAR.name(), cliente.getId());
        redirectOnContextPath("/pages/cliente/cadastro/cadastroCliente.xhtml");
    }
    
    public void excluir(Cliente cliente){
        cliente.setDataExclusao(new Date());
        clienteDao.edit(cliente);
        clientes.remove(cliente);
    }
}
