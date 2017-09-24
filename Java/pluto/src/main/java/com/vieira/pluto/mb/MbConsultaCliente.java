/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme
 */
@ManagedBean
@ViewScoped
public class MbConsultaCliente extends BasicMb{
    
    private List<Cliente> clientes;
    private ClienteDao clienteDao;
    
    @PostConstruct
    public void init(){
        clienteDao = new ClienteDao();
        clientes = clienteDao.getAllAtivos();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public void editar(Cliente cliente){
        putOnSession("clienteEditar", cliente);
        redirectOnContextPath("/pages/cliente/cadastro/cadastroCliente.xhtml");
    }
    
    public void excluir(Cliente cliente){
        cliente.setDataExclusao(new Date());
        clienteDao.edit(cliente);
        clientes.remove(cliente);
    }
}
