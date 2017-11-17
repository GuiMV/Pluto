package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.Funcionario;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class FabricanteDao extends GenericDao<Fabricante> {

    @Inject
    private PessoaDao pessoaDao;

    public void save(Fabricante fabricante) {
        Pessoa pessoa = fabricante.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(fabricante.getId())) {
            add(fabricante);
        } else {
            edit(fabricante);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Fabricante> getAllAtivos() {
        return getEntities().where(obj -> obj.getDataExclusao() == null).toList();
    }

    public Fabricante getByCpfCnpj(String cpfCnpj){
        return getEntities().where(obj -> obj.getPessoa().getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
    }
}
