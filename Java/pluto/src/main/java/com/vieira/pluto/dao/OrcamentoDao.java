package com.vieira.pluto.dao;

import com.vieira.pluto.dto.ConsultaOrcamentoDto;
import com.vieira.pluto.entity.*;
import com.vieira.pluto.exception.HandledException;
import com.vieira.pluto.persistence.GenericDao;
import com.vieira.pluto.util.Dates;
import org.jinq.orm.stream.JinqStream;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

public class OrcamentoDao extends GenericDao<Orcamento> {

    @Inject
    private ItemOrcamentoDao itemOrcamentoDao;
    @Inject
    private ModeloVeiculoDao modeloVeiculoDao;
    @Inject
    private CorDao corDao;
    @Inject
    private VeiculoOrcamentoDao veiculoOrcamentoDao;

    public List<Orcamento> filterOrcamento(ConsultaOrcamentoDto consultaOrcamentoDto){
        JinqStream<Orcamento> query = getEntities();
        if (nonNull(consultaOrcamentoDto.getDataFim())) {
            Date dataFim = Dates.getDateInLastTimeOfDay(consultaOrcamentoDto.getDataFim());
            query = query.where(obj -> !obj.getData().after(dataFim));
        }
        if (nonNull(consultaOrcamentoDto.getDataInicio())) {
            Date dataInicio = Dates.getDateInMidnight(consultaOrcamentoDto.getDataInicio());
            query = query.where(obj -> !obj.getData().before(dataInicio));
        }
        Long idCliente = consultaOrcamentoDto.getIdCliente();
        if (nonNull(idCliente)) {
            query = query.where(obj -> obj.getCliente().getId().equals(idCliente));
        }
        StatusOrcamento statusOrcamento = consultaOrcamentoDto.getStatusOrcamento();
        if (nonNull(statusOrcamento)) {
            query = query.where(obj -> obj.getStatusOrcamento().equals(statusOrcamento));
        }
        return query.toList();
    }

    public void save(Orcamento orcamento){
        if(orcamento.getItemOrcamentoList().isEmpty()){
            throw new HandledException("Deve ser inserido ao menos um item no orçamento");
        }
        VeiculoOrcamento veiculoOrcamento = orcamento.getVeiculoOrcamento();
        veiculoOrcamento.setModeloVeiculo(modeloVeiculoDao.edit(veiculoOrcamento.getModeloVeiculo()));
        veiculoOrcamento.setCor(corDao.edit(veiculoOrcamento.getCor()));
        veiculoOrcamentoDao.add(veiculoOrcamento);
        List<ItemOrcamento> itemOrcamentoList = orcamento.getItemOrcamentoList();
        orcamento.setItemOrcamentoList(null);
        add(orcamento);
        for (ItemOrcamento item : itemOrcamentoList) {
            item.setOrcamento(orcamento);
            item.setId(null);
            itemOrcamentoDao.add(item);

        }
    }

    public void cancelarOrcamentosExpirados() {
        StringBuilder sb = new StringBuilder("UPDATE Orcamento SET statusOrcamento.id = 5\n");
        sb.append("WHERE statusOrcamento.id = 1\n");
        sb.append(String.format("and data <= '%tF 00:00:00'\n", Dates.minusDays(new Date(), 7)));
        Query query = getEntityManager().createQuery(sb.toString());
        query.executeUpdate();
    }

    public List<Orcamento> getOrcamentosLiberados(){
        return getEntities().where(obj -> obj.getStatusOrcamento().getId() == 3).toList();
    }
}
