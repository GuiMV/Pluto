package com.vieira.pluto.ws.client;

import com.vieira.pluto.ws.client.dto.EnderecoViaCEP;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.vieira.pluto.exception.HandledException;
import com.vieira.pluto.util.Strings;

import java.io.Serializable;
import java.util.Objects;
import javax.ws.rs.core.MediaType;

/**
 * Classe para recuperar informações do WS do viacep.com.br
 */
public class ViaCEPClient implements Serializable {
    
    private static final String BASE_URI = "https://viacep.com.br/ws/";
    
    public static EnderecoViaCEP consultaCEP(String cep) {
        Client client = null;
        try {
            cep = Strings.apenasNumeros(cep);
            if (Objects.isNull(cep) ||  !Strings.validaCep(cep)) {
                throw new HandledException("Formato de CEP inválido");
            }
            ClientConfig config = new DefaultClientConfig();
            config.getClasses().add(JacksonJaxbJsonProvider.class);

            client = Client.create(config);

            WebResource webResource = client.resource(BASE_URI + cep + "/json/");

            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new HandledException("Erro ao consultar CEP. Código : " + response.getStatus());
            }

            final EnderecoViaCEP endereco = response.getEntity(EnderecoViaCEP.class);
            client.destroy();
            return endereco;

        } catch (Exception ex) {
            try {
                client.destroy();
            } catch (Exception ex2) {
            }
            throw new HandledException("Erro ao consultar cep");
        }
    }
}
