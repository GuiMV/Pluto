package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Estabelecimento;
import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;

import javax.inject.Inject;
import java.util.Objects;

public class EstabelecimentoDao extends GenericDao<Estabelecimento>{

    @Inject
    private PessoaDao pessoaDao;

    public void save(Estabelecimento estabelecimento) {
        Pessoa pessoa = estabelecimento.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(estabelecimento.getId())) {
            add(estabelecimento);
        } else {
            edit(estabelecimento);
        }
    }

    public Estabelecimento getEstabelecimento(){
        return get(1L);
    }

}
