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
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbConsultaUsuario extends BasicMb{

    @Inject
    private UsuarioDao usuarioDao;
    private List<Usuario> usuarios;
    
    @PostConstruct
    public void init(){
        usuarios = usuarioDao.getAllAtivos();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void editar(Usuario usuario){
        putOnSession(PROPERTY.USUARIO_EDITAR.name(), usuario.getId());
        redirectOnContextPath("/pages/usuario/cadastro/cadastroUsuario.xhtml");
    }
    
    public void excluir(Usuario usuario){
        usuario.setDataExclusao(new Date());
        usuarioDao.edit(usuario);
        usuarios.remove(usuario);
    }
}
