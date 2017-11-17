package com.vieira.pluto.dao;

import com.vieira.pluto.dto.ConsultaContaPagarDto;
import com.vieira.pluto.entity.*;
import com.vieira.pluto.persistence.GenericDao;
import com.vieira.pluto.util.Dates;
import org.jinq.orm.stream.JinqStream;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

public class ContaPagarDao extends GenericDao<ContaPagar> {

    @Inject
    private ParcelaContaPagarDao parcelaContaPagarDao;
    @Inject
    private QuitacaoParcelaContaPagarDao quitacaoParcelaContaPagarDao;

    public List<ContaPagar> filterContaPagar(ConsultaContaPagarDto consultaContaPagarDto) {
        JinqStream<ContaPagar> query = getEntities().where(obj -> obj.getDataExclusao() == null);
        if (nonNull(consultaContaPagarDto.getDataFim())) {
            Date dataFim = Dates.getDateInLastTimeOfDay(consultaContaPagarDto.getDataFim());
            query = query.where(obj -> !obj.getData().after(dataFim));
        }
        if (nonNull(consultaContaPagarDto.getDataInicio())) {
            Date dataInicio = Dates.getDateInMidnight(consultaContaPagarDto.getDataInicio());
            query = query.where(obj -> !obj.getData().before(dataInicio));
        }
        Long idPessoa = consultaContaPagarDto.getIdPessoa();
        if (nonNull(idPessoa)) {
            query = query.where(obj -> obj.getPessoa().getId().equals(idPessoa));
        }
        return query.toList();
    }

    public void salvar(ContaPagar contaPagar, Boolean quitado, Integer quantidadeParcelas, LocalDate vencimento, Long idFormaPagamento, Double descontoTotal) {
        Double descontoParcela = descontoTotal / quantidadeParcelas;
        if (quitado) {
            contaPagar.setDataQuitacao(LocalDate.now());
        }
        add(contaPagar);

        double valorParcela = contaPagar.getValorTotal() / quantidadeParcelas;
        for (int i = 0; i < quantidadeParcelas; i++) {
            ParcelaContaPagar parcela = new ParcelaContaPagar();
            parcela.setValor(valorParcela);
            parcela.setDataVencimento(vencimento);
            parcela.setContaPagar(contaPagar);
            parcela.setDataQuitacao(contaPagar.getDataQuitacao());
            parcelaContaPagarDao.add(parcela);
            vencimento = vencimento.plusMonths(1);

            if(quitado){
                QuitacaoParcelaContaPagar quitacaoParcelaContaPagar = new QuitacaoParcelaContaPagar();
                quitacaoParcelaContaPagar.setIdFormaPagamento(idFormaPagamento);
                quitacaoParcelaContaPagar.setIdParcelaContaPagar(parcela.getId());
                quitacaoParcelaContaPagar.setValor(parcela.getValor() - descontoParcela);
                quitacaoParcelaContaPagarDao.add(quitacaoParcelaContaPagar);
            }
        }
    }
}
