package com.vieira.pluto.dao;

import com.vieira.pluto.dto.ConsultaOrcamentoDto;
import com.vieira.pluto.entity.*;
import com.vieira.pluto.persistence.GenericDao;
import org.jinq.orm.stream.JinqStream;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

public class OrcamentoDao extends GenericDao<Orcamento> {

    public List<Orcamento> filterOrcamento(ConsultaOrcamentoDto consultaOrcamentoDto){
        JinqStream<Orcamento> query = getEntities();
        Date dataFim = consultaOrcamentoDto.getDataFim();
        if (nonNull(dataFim)) {
            query = query.where(obj -> !obj.getData().after(dataFim));
        }
        Date dataInicio = consultaOrcamentoDto.getDataInicio();
        if (nonNull(dataInicio)) {
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
        ItemOrcamentoDao itemOrcamentoDao = new ItemOrcamentoDao();
        ModeloVeiculoDao modeloVeiculoDao = new ModeloVeiculoDao();
        CorDao corDao = new CorDao();
        VeiculoOrcamentoDao veiculoOrcamentoDao = new VeiculoOrcamentoDao();
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
}
