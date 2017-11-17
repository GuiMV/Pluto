package com.vieira.pluto.dao;

import com.vieira.pluto.entity.ConfiguracaoClassificacao;
import com.vieira.pluto.entity.ConfiguracaoEmail;
import com.vieira.pluto.entity.Pessoa;
import com.vieira.pluto.exception.HandledException;
import com.vieira.pluto.persistence.GenericDao;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class ConfiguracaoEmailDao extends GenericDao<ConfiguracaoEmail> {

    public ConfiguracaoEmail getConfiguracao(){
        return get(1L);
    }

    public void enviarEmailTeste(ConfiguracaoEmail configuracaoEmail){
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(configuracaoEmail.getHostName());
            email.setSSLOnConnect(configuracaoEmail.getSsl());
            email.setSmtpPort(configuracaoEmail.getPort());
            email.setSslSmtpPort(String.valueOf(configuracaoEmail.getPort()));
            email.setStartTLSEnabled(true);

            System.out.println("autenticando...");
            email.setAuthentication(configuracaoEmail.getUserName(), configuracaoEmail.getPassword());
            System.out.println("autenticado...");

            email.setFrom(configuracaoEmail.getUserName(), configuracaoEmail.getEmitter());
            email.addTo(configuracaoEmail.getUserName());
            email.setSubject("Teste");
            email.setMsg("Email de teste");

            System.out.println("enviando...");
            email.send();
            System.out.println("Email enviado!");
        } catch (EmailException ex) {
            throw new HandledException("Erro ao envial email de teste", ex);
        }
    }
}
