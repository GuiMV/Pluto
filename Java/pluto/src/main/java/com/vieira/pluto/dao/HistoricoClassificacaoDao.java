package com.vieira.pluto.dao;

import com.vieira.pluto.dto.ConsultaComissaoDto;
import com.vieira.pluto.dto.ConsultaHistoricoClassificacaoDto;
import com.vieira.pluto.entity.HistoricoClassificacao;
import com.vieira.pluto.entity.query.ComissaoFuncionarioDto;
import com.vieira.pluto.persistence.GenericDao;
import org.jinq.orm.stream.JinqStream;

import javax.persistence.Query;
import java.util.List;

import static java.util.Objects.nonNull;

public class HistoricoClassificacaoDao extends GenericDao<HistoricoClassificacao> {

    public List<HistoricoClassificacao> getHistoricosClassificacao(ConsultaHistoricoClassificacaoDto consultaHistoricoClassificacaoDto) {
        Long idCliente = consultaHistoricoClassificacaoDto.getIdCliente();
        JinqStream<HistoricoClassificacao> query = getEntities().where(obj -> obj.getIdCliente() == idCliente);

        Integer anoFim = consultaHistoricoClassificacaoDto.getAnoFim();
        if(nonNull(anoFim)){
            query = query.where(obj -> obj.getAno() <= anoFim);
        }
        Integer anoInicio = consultaHistoricoClassificacaoDto.getAnoInicio();
        if(nonNull(anoInicio)){
            query = query.where(obj -> obj.getAno() >= anoInicio);
        }
        Integer mesFim = consultaHistoricoClassificacaoDto.getMesFim();
        if(nonNull(mesFim)){
            query = query.where(obj -> obj.getMes() <= mesFim);
        }
        Integer mesInicio = consultaHistoricoClassificacaoDto.getMesInicio();
        if(nonNull(mesInicio)){
            query = query.where(obj -> obj.getMes() >= mesInicio);
        }
        return query.toList();
    }
}
