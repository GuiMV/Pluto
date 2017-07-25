/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.entity.Usuario;
import com.vieira.pluto.persistence.GenericDao;
import java.util.Objects;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class UsuarioDao extends GenericDao<Usuario>{
    
    public void save(Usuario usuario){
        PessoaDao pessoaDao = new PessoaDao();
        Pessoa pessoa = usuario.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(usuario.getId())) {
            add(usuario);
        } else {
            edit(usuario);
        }
    }    
    
    public Usuario logon(Usuario usuario){
        StringBuilder sb = new StringBuilder("FROM Usuario WHERE username = '");
        sb.append(usuario.getUser().trim());
        sb.append("' AND password = '");
        sb.append(usuario.getPassword().trim());
        sb.append("'");
        Query query = getEntityManager().createQuery(sb.toString());
        return Usuario.class.cast(query.getSingleResult());
    }
}
