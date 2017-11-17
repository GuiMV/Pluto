package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ConfiguracaoEmailDao;
import com.vieira.pluto.dao.EstabelecimentoDao;
import com.vieira.pluto.entity.ConfiguracaoEmail;
import com.vieira.pluto.entity.Estabelecimento;
import com.vieira.pluto.entity.TipoPessoa;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MbCadastroConfiguracaoEmail extends BasicMb{

    @Inject
    private ConfiguracaoEmailDao configuracaoEmailDao;
    private ConfiguracaoEmail configuracaoEmail;

    @PostConstruct
    public void init() {
        configuracaoEmail = configuracaoEmailDao.getConfiguracao();
    }

    public void enviarEmailTeste(){
        configuracaoEmailDao.enviarEmailTeste(configuracaoEmail);
        addInfoMessage("Email teste enviado com sucesso!");
    }
    
    public void salvar(){
        configuracaoEmailDao.edit(configuracaoEmail);
        addInfoMessage("Configuração salva com sucesso!");
    }

    public ConfiguracaoEmail getConfiguracaoEmail() {
        return configuracaoEmail;
    }

    public void setConfiguracaoEmail(ConfiguracaoEmail configuracaoEmail) {
        this.configuracaoEmail = configuracaoEmail;
    }
}
