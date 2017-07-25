/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Telefone;
import com.vieira.pluto.persistence.GenericDao;
import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class TelefoneDao extends GenericDao<Telefone>{
    

    public void save(Telefone telefone) {
        if (Objects.isNull(telefone.getId()) || telefone.getId() <= 0) {
            telefone.setId(null);
            add(telefone);
        } else {
            edit(telefone);
        }
    }
}
