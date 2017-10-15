/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.entity.Usuario;
import com.vieira.pluto.persistence.GenericDao;
import org.jinq.orm.stream.JinqStream;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class UsuarioDao extends GenericDao<Usuario> {

    public void save(Usuario usuario) {
        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = usuario.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(usuario.getId())) {
            add(usuario);
        } else {
            edit(usuario);
        }
    }

    public Usuario logon(Usuario usuario) {
        String password = usuario.getPassword().trim();
        String user = usuario.getUserName().trim();
        JinqStream<Usuario> select = getEntities().where(usuarioDb -> {
            return usuarioDb.getDataExclusao() == null && usuarioDb.getPassword().equals(password)&& usuarioDb.getUserName().equals(user);

        });
        return select.getOnlyValue();
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> getAllAtivos() {
        JinqStream<Usuario> select = getEntities().where(usuario -> usuario.getDataExclusao() == null);
        return select.toList();
    }
}
