package com.vieira.pluto.ws.client.dto;

/**
 * Entidade baseada nos dados do WS do viacep.com
 */
public class EnderecoViaCEP {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;

    public EnderecoViaCEP() {
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getIbge() {
        return ibge;
    }

    public String getGia() {
        return gia;
    }

    public EnderecoViaCEP setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public EnderecoViaCEP setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public EnderecoViaCEP setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public EnderecoViaCEP setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecoViaCEP setLocalidade(String localidade) {
        this.localidade = localidade;
        return this;
    }

    public EnderecoViaCEP setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public EnderecoViaCEP setUnidade(String unidade) {
        this.unidade = unidade;
        return this;
    }

    public EnderecoViaCEP setIbge(String ibge) {
        this.ibge = ibge;
        return this;
    }

    public EnderecoViaCEP setGia(String gia) {
        this.gia = gia;
        return this;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", unidade='" + unidade + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                '}';
    }
}
