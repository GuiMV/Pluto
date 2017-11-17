package com.vieira.pluto.dao;

import com.vieira.pluto.entity.FormaPagamento;
import com.vieira.pluto.persistence.GenericDao;

import static java.util.Objects.isNull;

public class FormaPagamentoDao extends GenericDao<FormaPagamento> {

    public void save(FormaPagamento formaPagamento){
        if (isNull(formaPagamento.getId())){
            add(formaPagamento);
        } else {
            edit(formaPagamento);
        }
    }
}
