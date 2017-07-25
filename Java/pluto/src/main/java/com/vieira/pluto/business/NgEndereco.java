package com.vieira.pluto.business;

import com.vieira.pluto.dao.MunicipioDao;
import com.vieira.pluto.dao.UfDao;
import com.vieira.pluto.entity.Endereco;
import com.vieira.pluto.entity.Municipio;
import com.vieira.pluto.entity.Uf;
import com.vieira.pluto.ws.client.ViaCEPClient;
import com.vieira.pluto.ws.client.dto.EnderecoViaCEP;
import java.io.Serializable;
import java.util.Objects;

public class NgEndereco implements Serializable{

    public Endereco buscarCEP(Endereco endereco) {
        return buscarCEP(endereco.getCep(), endereco);
    }

    public Endereco buscarCEP(String cep) {
        return buscarCEP(cep, null);
    }

    public Endereco buscarCEP(String cep, Endereco endereco) {
        EnderecoViaCEP enderecoViaCEP = ViaCEPClient.consultaCEP(cep);

        MunicipioDao municipioDao = new MunicipioDao();
        final long codigoIbge = Long.parseLong(enderecoViaCEP.getIbge());
        Municipio municipio = municipioDao.get(codigoIbge);
        if (Objects.isNull(municipio)) {
            municipio = new Municipio(codigoIbge);

            UfDao ufDao = new UfDao();
            Uf uf = ufDao.getByAbreviacao(enderecoViaCEP.getUf());
            municipio.setUf(uf);
        }
        municipio.setNome(enderecoViaCEP.getLocalidade());

        if (Objects.isNull(endereco)) {
            endereco = new Endereco();
        }

        endereco.setCep(enderecoViaCEP.getCep());
        endereco.setLogradouro(enderecoViaCEP.getLogradouro());
        endereco.setBairro(enderecoViaCEP.getBairro());
        endereco.setMunicipio(municipio);

        return endereco;
    }
}
