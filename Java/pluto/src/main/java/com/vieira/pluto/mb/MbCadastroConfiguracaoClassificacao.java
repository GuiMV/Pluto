package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ConfiguracaoClassificacaoDao;
import com.vieira.pluto.dao.ConfiguracaoEmailDao;
import com.vieira.pluto.entity.ConfiguracaoClassificacao;
import com.vieira.pluto.entity.ConfiguracaoEmail;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MbCadastroConfiguracaoClassificacao extends BasicMb{

    @Inject
    private ConfiguracaoClassificacaoDao configuracaoClassificacaoDao;
    private ConfiguracaoClassificacao configuracaoClassificacao;

    @PostConstruct
    public void init() {
        configuracaoClassificacao = configuracaoClassificacaoDao.getConfiguracao();
    }
    
    public void salvar(){
        configuracaoClassificacaoDao.edit(configuracaoClassificacao);
        addInfoMessage("Configuração salva com sucesso!");
    }

    public ConfiguracaoClassificacao getConfiguracaoClassificacao() {
        return configuracaoClassificacao;
    }

    public void setConfiguracaoClassificacao(ConfiguracaoClassificacao configuracaoClassificacao) {
        this.configuracaoClassificacao = configuracaoClassificacao;
    }
}
