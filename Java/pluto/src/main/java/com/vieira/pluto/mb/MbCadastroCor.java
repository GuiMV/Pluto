package com.vieira.pluto.mb;

import com.vieira.pluto.dao.CorDao;
import com.vieira.pluto.entity.Cor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MbCadastroCor extends BasicMb {

    private Cor cor;

    @PostConstruct
    public void init(){
        cor = new Cor();
    }

    public void salvar(){
        CorDao corDao = new CorDao();
        cor.setNome(cor.getNome().toUpperCase() );
        corDao.add(cor);
        cor = new Cor();
        addInfoMessage("Cor salva com sucesso!");
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
}
