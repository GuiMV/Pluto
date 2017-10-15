/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.UsuarioDao;
import com.vieira.pluto.entity.TipoPessoa;
import com.vieira.pluto.entity.Usuario;
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
public class MbCadastroUsuario extends BasicMb implements Serializable{

    private Usuario usuario;
    private UsuarioDao usuarioDao;
    @Inject
    private MbPessoa mbPessoa;

    @PostConstruct
    public void init() {
        usuarioDao = new UsuarioDao();
        mbPessoa.setTipoPessoa(new TipoPessoa(1L));
        usuario = getOnSession("usuarioEditar", Usuario.class);
        if (Objects.isNull(usuario)) {
           novoUsuario(); 
        } else {
            mbPessoa.setPessoaCompleta(usuario.getPessoa());
        }
        removeFromSession("usuarioEditar");
    }

    private void novoUsuario() {
        usuario = new Usuario();
    }
    
    public void salvar(){
        usuario.setPessoa(mbPessoa.getPessoaCompleta());
        usuario.setUserName(usuario.getPessoa().getCpfCnpj());
        usuarioDao.save(usuario);
        novoUsuario();
        mbPessoa.novaPessoa();
        addInfoMessage("Usuário salvo com sucesso!");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
