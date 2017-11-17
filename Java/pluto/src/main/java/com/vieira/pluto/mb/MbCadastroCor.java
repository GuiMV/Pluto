package com.vieira.pluto.mb;

import com.vieira.pluto.dao.CorDao;
import com.vieira.pluto.entity.Cor;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;

import static java.util.Objects.isNull;

@Named
@ViewScoped
public class MbCadastroCor extends BasicMb {

    @Inject
    private CorDao corDao;
    private Cor cor;

    @PostConstruct
    public void init(){
        Long idCor = getOnFlash(PROPERTY.COR_EDITAR.name(), Long.class);
        if(isNull(idCor)){
            novaCor();
        } else {
            cor = corDao.get(idCor);
        }
    }

    private void novaCor() {
        cor = new Cor();
    }

    public void salvar(){
        cor.setNome(cor.getNome().toUpperCase() );
        corDao.save(cor);
        novaCor();
        addInfoMessage("Cor salva com sucesso!");
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
}
