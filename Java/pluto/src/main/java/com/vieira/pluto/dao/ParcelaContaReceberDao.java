package com.vieira.pluto.dao;

import com.vieira.pluto.entity.ContaReceber;
import com.vieira.pluto.entity.ParcelaContaReceber;
import com.vieira.pluto.entity.QuitacaoParcelaContaReceber;
import com.vieira.pluto.persistence.GenericDao;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class ParcelaContaReceberDao extends GenericDao<ParcelaContaReceber> {

    @Inject
    private QuitacaoParcelaContaReceberDao quitacaoParcelaContaReceberDao;
    @Inject
    private Instance<ContaReceberDao> contaReceberDao;

    public List<ParcelaContaReceber> getParcelas(Long idContaReceber){
        return getEntities().where(obj -> obj.getContaReceber().getId() == idContaReceber).toList();
    }

    public void quitarParcela(ParcelaContaReceber parcela, Long idFormaPagamento, Double desconto){
        parcela.setDataQuitacao(LocalDate.now());
        edit(parcela);

        QuitacaoParcelaContaReceber quitacaoParcelaContaReceber = new QuitacaoParcelaContaReceber();
        quitacaoParcelaContaReceber.setIdFormaPagamento(idFormaPagamento);
        quitacaoParcelaContaReceber.setIdParcelaContaReceber(parcela.getId());
        quitacaoParcelaContaReceber.setValor(parcela.getValor() - desconto);
        quitacaoParcelaContaReceberDao.add(quitacaoParcelaContaReceber);

        ContaReceber contaReceber = parcela.getContaReceber();
        if (parcelasQuitadas(contaReceber.getId())){
            contaReceber.setDataQuitacao(parcela.getDataQuitacao());
            contaReceberDao.get().edit(contaReceber);
            contaReceberDao.get().enviarEmail(contaReceber.getCliente().getPessoa());
        }
    }

    public boolean parcelasQuitadas(Long idContaReceber){
        return getEntities().noneMatch(obj ->  obj.getContaReceber().getId() == idContaReceber && obj.getDataQuitacao() == null);
    }

}
