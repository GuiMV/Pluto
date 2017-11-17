package com.vieira.pluto.dao;

import com.vieira.pluto.entity.Empresa;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.persistence.GenericDao;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

public class EmpresaDao extends GenericDao<Empresa> {

    @Inject
    private PessoaDao pessoaDao;

    public void save(Empresa empresa) {
        Pessoa pessoa = empresa.getPessoa();
        pessoaDao.save(pessoa);
        if (Objects.isNull(empresa.getId())) {
            add(empresa);
        } else {
            edit(empresa);
        }
    }

    public List<Empresa> getAllAtivos() {
        return getEntities().where(obj -> obj.getDataExclusao() == null).toList();
    }

    public Empresa getByCpfCnpj(String cpfCnpj){
        return getEntities().where(obj -> obj.getPessoa().getCpfCnpj().equals(cpfCnpj)).findFirst().orElse(null);
    }
}
