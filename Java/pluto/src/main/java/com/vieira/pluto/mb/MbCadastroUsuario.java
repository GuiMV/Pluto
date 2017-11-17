/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.mb;

import com.vieira.pluto.dao.UsuarioDao;
import com.vieira.pluto.entity.Funcionario;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.entity.TipoPessoa;
import com.vieira.pluto.entity.Usuario;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import com.vieira.pluto.util.Strings;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 *
 * @author Guilherme
 */
@Named
@ViewScoped
public class MbCadastroUsuario extends BasicMb{

    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private MbPessoa mbPessoa;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        mbPessoa.setTipoPessoa(new TipoPessoa(1L));
        Long idUsuario = getOnSession(PROPERTY.USUARIO_EDITAR.name(), Long.class);
        if (Objects.isNull(idUsuario)) {
           novoUsuario(); 
        } else {
            usuario = usuarioDao.get(idUsuario);
            mbPessoa.setPessoaCompleta(usuario.getPessoa());
        }
        removeFromSession("usuarioEditar");
    }

    private void novoUsuario() {
        usuario = new Usuario();
    }

    public void buscarCpfCnpj() {
        Pessoa pessoa = mbPessoa.getPessoa();
        String cpfCnpj = Strings.apenasNumeros(pessoa.getCpfCnpj());
        if (cpfCnpj.length() != 11) {
            pessoa.setCpfCnpj("");
            return;
        }
        Usuario usuarioDb = usuarioDao.getByCpf(cpfCnpj);
        if (isNull(usuarioDb)) {
            mbPessoa.buscarCpfCnpj();
        } else {
            usuario = Usuario.class.cast(Hibernate.unproxy(usuarioDb));
            mbPessoa.setPessoaCompleta(usuario.getPessoa());
            if (nonNull(usuario.getDataExclusao())){
                addWarnMessage("Usuario inativado com este CPF, ao salvar ele será ativado");
            } else {
                addInfoMessage("Usuario já cadastrado com este CPF, será realizada a edição");
            }
        }
    }
    
    public void salvar(){
        usuario.setPessoa(mbPessoa.getPessoaCompleta());
        usuario.setUserName(usuario.getPessoa().getCpfCnpj());
        usuario.setDataExclusao(null);
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
