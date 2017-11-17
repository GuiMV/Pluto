package com.vieira.pluto.dao;

import com.vieira.pluto.dto.ConsultaOrcamentoDto;
import com.vieira.pluto.dto.ConsultaVendaDto;
import com.vieira.pluto.entity.*;
import com.vieira.pluto.exception.HandledException;
import com.vieira.pluto.persistence.GenericDao;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jinq.orm.stream.JinqStream;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

public class VendaDao extends GenericDao<Venda> {

    @Inject
    private ItemVendaDao itemVendaDao;
    @Inject
    private ModeloVeiculoDao modeloVeiculoDao;
    @Inject
    private CorDao corDao;
    @Inject
    private VeiculoVendaDao veiculoVendaDao;
    @Inject
    private ContaReceberDao contaReceberDao;

    public List<Venda> filterVenda(ConsultaVendaDto consultaVendaDto) {
        JinqStream<Venda> query = getEntities();
        Date dataFim = consultaVendaDto.getDataFim();
        if (nonNull(dataFim)) {
            query = query.where(obj -> !obj.getData().after(dataFim));
        }
        Date dataInicio = consultaVendaDto.getDataInicio();
        if (nonNull(dataInicio)) {
            query = query.where(obj -> !obj.getData().before(dataInicio));
        }
        Long idCliente = consultaVendaDto.getIdCliente();
        if (nonNull(idCliente)) {
            query = query.where(obj -> obj.getCliente().getId().equals(idCliente));
        }
        return query.toList();
    }

    public void save(Venda venda, LocalDate vencimento, Integer quantidadeParcelas, Boolean quitado, Long idFormaPagamento, Double descontoTotal) {
        if(venda.getItemVendaList().isEmpty()){
            throw new HandledException("Deve ser inserido ao menos um item na venda");
        }
        VeiculoVenda veiculoVenda = venda.getVeiculoVenda();
        veiculoVendaDao.add(veiculoVenda);
        List<ItemVenda> itemVendaList = venda.getItemVendaList();
        venda.setItemVendaList(null);
        add(venda);
        for (ItemVenda item : itemVendaList) {
            item.setVenda(venda);
            item.setId(null);
            itemVendaDao.add(item);
        }
        ContaReceber contaReceber = new ContaReceber();
        contaReceber.setCliente(venda.getCliente());
        contaReceber.setData(venda.getData());
        contaReceber.setIdUsuario(venda.getIdUsuario());
        contaReceber.setValorTotal(venda.getValorTotal());
        contaReceber.setVenda(venda);

        contaReceberDao.salvar(contaReceber, quitado, quantidadeParcelas, vencimento, idFormaPagamento, descontoTotal);
    }
}