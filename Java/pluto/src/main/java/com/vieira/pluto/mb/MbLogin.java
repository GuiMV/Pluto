package com.vieira.pluto.mb;

import com.vieira.pluto.dao.UsuarioDao;
import com.vieira.pluto.entity.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named
@SessionScoped
public class MbLogin extends BasicMb {

    private Usuario usuario;

    public MbLogin() {
        init();
    }

    private void init() {
        usuario = new Usuario();
    }

    public void logon() {
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            usuario = usuarioDao.logon(usuario);
        } catch (Exception e) {
            addErrorMessage("Login Inválido!");
            return;
        }
        if (usuario.getId() == null) {
            addErrorMessage("Login Inválido!");
        } else {
            putOnSession("usuarioLogado", usuario);
            redirectOnContextPath("/pages/templates/template.xhtml");
        }
    }

    public void logout() {
        removeFromSession("usuarioLogado");
        init();
        redirectOnContextPath("/login.xhtml");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
