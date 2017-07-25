/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Email;
import com.vieira.pluto.persistence.GenericDao;
import java.util.Objects;

/**
 *
 * @author Guilherme
 */
public class EmailDao extends GenericDao<Email> {

    public void save(Email email) {
        if (Objects.isNull(email.getId()) || email.getId() <= 0) {
            email.setId(null);
            add(email);
        } else {
            edit(email);
        }
    }
}
