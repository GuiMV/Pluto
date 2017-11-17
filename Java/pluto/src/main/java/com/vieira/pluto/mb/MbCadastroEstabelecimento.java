package com.vieira.pluto.mb;

import com.vieira.pluto.dao.EstabelecimentoDao;
import com.vieira.pluto.entity.Estabelecimento;
import com.vieira.pluto.entity.TipoPessoa;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named
@ViewScoped
public class MbCadastroEstabelecimento extends BasicMb{

    @Inject
    private EstabelecimentoDao estabelecimentoDao;
    private Estabelecimento estabelecimento;
    @Inject
    private MbPessoa mbPessoa;

    @PostConstruct
    public void init() {
        mbPessoa.setTipoPessoa(new TipoPessoa(2L));
        estabelecimento = estabelecimentoDao.getEstabelecimento();
        mbPessoa.setPessoaCompleta(estabelecimento.getPessoa());
    }
    
    public void salvar(){
        estabelecimento.setPessoa(mbPessoa.getPessoaCompleta());
        estabelecimentoDao.save(estabelecimento);
        addInfoMessage("Estabelecimento salvo com sucesso!");
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

}
