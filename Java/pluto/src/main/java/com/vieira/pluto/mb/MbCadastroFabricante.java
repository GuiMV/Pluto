package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.Funcionario;
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
public class MbCadastroFabricante extends BasicMb {

    @Inject
    private FabricanteDao fabricanteDao;
    @Inject
    private MbPessoa mbPessoa;
    private Fabricante fabricante;

    @PostConstruct
    public void init() {
        mbPessoa.setTipoPessoa(new TipoPessoa(2L));
        Long idFabricante = getOnSession(PROPERTY.FABRICANTE_EDITAR.name(), Long.class);
        removeFromSession(PROPERTY.FABRICANTE_EDITAR.name());
        if (Objects.isNull(idFabricante)) {
           novoFabricante();
        } else {
            fabricante = fabricanteDao.get(idFabricante);
            mbPessoa.setPessoaCompleta(fabricante.getPessoa());
        }
    }

    private void novoFabricante() {
        fabricante = new Fabricante();
        mbPessoa.novaPessoa();
    }
    public void buscarCpfCnpj() {
        Pessoa pessoa = mbPessoa.getPessoa();
        String cpfCnpj = Strings.apenasNumeros(pessoa.getCpfCnpj());
        if (cpfCnpj.length() != 14) {
            pessoa.setCpfCnpj("");
            return;
        }
        Fabricante fabricanteDb = fabricanteDao.getByCpfCnpj(cpfCnpj);
        if (isNull(fabricanteDb)) {
            mbPessoa.buscarCpfCnpj();
        } else {
            fabricante = Fabricante.class.cast(Hibernate.unproxy(fabricanteDb));
            mbPessoa.setPessoaCompleta(fabricante.getPessoa());
            if (nonNull(fabricante.getDataExclusao())){
                addWarnMessage("Fabricante inativado com este CPF, ao salvar ele será ativado");
            } else {
                addInfoMessage("Fabricante já cadastrado com este CPF, será realizada a edição");
            }
        }
    }
    
    public void salvar(){
        fabricante.setPessoa(mbPessoa.getPessoaCompleta());
        fabricante.setDataExclusao(null);
        fabricanteDao.save(fabricante);
        novoFabricante();
        addInfoMessage("Fabricante salvo com sucesso!");
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

}
