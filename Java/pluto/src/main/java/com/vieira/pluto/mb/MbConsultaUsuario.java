/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.UsuarioDao;
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
public class MbConsultaUsuario extends BasicMb{
    
    private List<Usuario> usuarios;
    private UsuarioDao usuarioDao;
    
    @PostConstruct
    public void init(){
        usuarioDao = new UsuarioDao();
        usuarios = usuarioDao.getAll();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void editar(Usuario usuario){
        putOnSession("usuarioEditar", usuario);
        redirectOnContextPath("/pages/usuario/cadastro/cadastroUsuario.xhtml");
    }
    
    public void excluir(Usuario usuario){
        usuario.setDataExclusao(new Date());
        usuarioDao.edit(usuario);
        usuarios.remove(usuario);
    }
}
