/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Municipio;
import com.vieira.pluto.persistence.GenericDao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class MunicipioDao extends GenericDao<Municipio>{
    
    @SuppressWarnings("unchecked")
    public List<Municipio> getByIdUf(Long idUf){
        String sql = String.format("FROM Municipio WHERE uf.id = %d", idUf);
        Query query = getEntityManager().createQuery(sql);
        return query.getResultList();
    }
}
