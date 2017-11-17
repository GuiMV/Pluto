package com.vieira.pluto.mb;

import com.vieira.pluto.dao.CorDao;
import com.vieira.pluto.dao.ParcelaDao;
import com.vieira.pluto.entity.Cor;
import com.vieira.pluto.entity.Parcela;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaParcela extends BasicMb {

    @Inject
    private ParcelaDao parcelaDao;
    private List<Parcela> parcelas;

    @PostConstruct
    public void init(){
        parcelas = parcelaDao.getAll();
    }

    public void excluir(Parcela parcela){
        parcelas.remove(parcela);
        parcelaDao.delete(parcela);
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }
}
