package com.vieira.pluto.dao;

import com.vieira.pluto.entity.ConfiguracaoClassificacao;
import com.vieira.pluto.persistence.GenericDao;

public class ConfiguracaoClassificacaoDao extends GenericDao<ConfiguracaoClassificacao> {

    public ConfiguracaoClassificacao getConfiguracao(){
        return get(1L);
    }
}
