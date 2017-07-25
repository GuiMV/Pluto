/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Uf;
import com.vieira.pluto.persistence.GenericDao;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class UfDao extends GenericDao<Uf>{
    
    
    public Uf getByAbreviacao(String abreviacao){
        String sql = String.format("FROM Uf WHERE abreviacao = '%s'", abreviacao);
        Query query = getEntityManager().createQuery(sql);
        return Uf.class.cast(query.getSingleResult());
    }
}
