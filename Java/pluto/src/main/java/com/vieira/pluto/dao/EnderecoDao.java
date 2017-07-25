/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Endereco;
import com.vieira.pluto.persistence.GenericDao;
import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class EnderecoDao extends GenericDao<Endereco> {

    public void save(Endereco endereco) {
        if (Objects.isNull(endereco.getId()) || endereco.getId() <= 0) {
            endereco.setId(null);
            add(endereco);
        } else {
            edit(endereco);
        }
    }
}
