package com.vieira.pluto.dao;

import com.vieira.pluto.entity.*;
import com.vieira.pluto.persistence.GenericDao;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class ParcelaContaPagarDao extends GenericDao<ParcelaContaPagar> {

    @Inject
    private QuitacaoParcelaContaPagarDao quitacaoParcelaContaPagarDao;
    @Inject
    private Instance<ContaPagarDao> contaPagarDao;


    public List<ParcelaContaPagar> getParcelas(Long idContaPagar){
        return getEntities().where(obj -> obj.getContaPagar().getId() == idContaPagar).toList();
    }

    public void quitarParcela(ParcelaContaPagar parcela, Long idFormaPagamento, Double desconto){
        parcela.setDataQuitacao(LocalDate.now());
        edit(parcela);

        QuitacaoParcelaContaPagar quitacaoParcelaContaPagar = new QuitacaoParcelaContaPagar();
        quitacaoParcelaContaPagar.setIdFormaPagamento(idFormaPagamento);
        quitacaoParcelaContaPagar.setIdParcelaContaPagar(parcela.getId());
        quitacaoParcelaContaPagar.setValor(parcela.getValor() - desconto);
        quitacaoParcelaContaPagarDao.add(quitacaoParcelaContaPagar);

        ContaPagar contaPagar = parcela.getContaPagar();
        if (parcelasQuitadas(contaPagar.getId())){
            contaPagar.setDataQuitacao(parcela.getDataQuitacao());
            contaPagarDao.get().edit(contaPagar);
        }
    }

    public boolean parcelasQuitadas(Long idContaPagar){
        return getEntities().noneMatch(obj ->  obj.getContaPagar().getId() == idContaPagar && obj.getDataQuitacao() == null);
    }

}
