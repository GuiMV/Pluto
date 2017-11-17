package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ParcelaDao;
import com.vieira.pluto.entity.Parcela;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;

@Named
@ViewScoped
public class MbCadastroParcela extends BasicMb {

    @Inject
    private ParcelaDao parcelaDao;
    private Parcela parcela;

    @PostConstruct
    public void init(){
        parcela = new Parcela();
    }

    public void salvar(){
        parcelaDao.add(parcela);
        parcela = new Parcela();
        addInfoMessage("Parcela salva com sucesso!");
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }
}
