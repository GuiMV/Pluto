package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Cor;
import com.vieira.pluto.persistence.GenericDao;

import static java.util.Objects.isNull;

public class CorDao extends GenericDao<Cor> {

    public void save(Cor cor){
        if (isNull(cor.getId())){
            add(cor);
        } else {
            edit(cor);
        }
    }
}
