package com.vieira.pluto.mb;

import com.vieira.pluto.dao.EmpresaDao;
import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.Empresa;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.entity.TipoPessoa;
import com.vieira.pluto.enums.PROPERTY;
import com.vieira.pluto.util.Strings;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Named
@ViewScoped
public class MbCadastroEmpresa extends BasicMb implements Serializable{

    @Inject
    private EmpresaDao empresaDao;
    @Inject
    private MbPessoa mbPessoa;
    private Empresa empresa;

    @PostConstruct
    public void init() {
        mbPessoa.setTipoPessoa(new TipoPessoa(2L));
        Long idEmpresa = getOnSession(PROPERTY.EMPRESA_EDITAR.name(), Long.class);
        removeFromSession(PROPERTY.EMPRESA_EDITAR.name());
        if (Objects.isNull(idEmpresa)) {
           novaEmpresa();
        } else {
            empresa = empresaDao.get(idEmpresa);
            mbPessoa.setPessoaCompleta(empresa.getPessoa());
        }
    }

    private void novaEmpresa() {
        empresa = new Empresa();
    }

    public void buscarCpfCnpj() {
        Pessoa pessoa = mbPessoa.getPessoa();
        String cpfCnpj = Strings.apenasNumeros(pessoa.getCpfCnpj());
        if (pessoa.getTipoPessoa().equals(new TipoPessoa(1L)) && cpfCnpj.length() != 11) {
            pessoa.setCpfCnpj("");
            return;
        } else if (pessoa.getTipoPessoa().equals(new TipoPessoa(2L)) && cpfCnpj.length() != 14) {
            pessoa.setCpfCnpj("");
            return;
        }
        Empresa empresaDb = empresaDao.getByCpfCnpj(cpfCnpj);
        if (isNull(empresaDb)) {
            mbPessoa.buscarCpfCnpj();
        } else {
            empresa = Empresa.class.cast(Hibernate.unproxy(empresaDb));
            mbPessoa.setPessoaCompleta(empresa.getPessoa());
            if (nonNull(empresa.getDataExclusao())){
                addWarnMessage("Empresa inativada com este CPF/CNPJ, ao salvar ele será ativada");
            } else {
                addInfoMessage("Empresa já cadastrada com este CPF/CNPJ, será realizada a edição");
            }
        }
    }
    
    public void salvar(){
        empresa.setPessoa(mbPessoa.getPessoaCompleta());
        empresa.setDataExclusao(null);
        empresaDao.save(empresa);
        novaEmpresa();
        mbPessoa.novaPessoa();
        addInfoMessage("Empresa salva com sucesso!");
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
