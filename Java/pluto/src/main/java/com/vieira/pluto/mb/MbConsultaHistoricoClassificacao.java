package com.vieira.pluto.mb;

import com.vieira.pluto.dao.HistoricoClassificacaoDao;
import com.vieira.pluto.dao.ItemVendaDao;
import com.vieira.pluto.dto.ConsultaComissaoDto;
import com.vieira.pluto.dto.ConsultaHistoricoClassificacaoDto;
import com.vieira.pluto.entity.HistoricoClassificacao;
import com.vieira.pluto.entity.query.ComissaoFuncionarioDto;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class MbConsultaHistoricoClassificacao extends BasicMb {

    @Inject
    private HistoricoClassificacaoDao historicoClassificacaoDao;
    private List<HistoricoClassificacao> historicosClassificacao;

    @PostConstruct
    public void init() {
        ConsultaHistoricoClassificacaoDto consultaComissaoDto = getOnSession("ConsultaHistoricoClassificacaoDto", ConsultaHistoricoClassificacaoDto.class);
        if (Objects.isNull(consultaComissaoDto)) {
            redirect("/pluto/pages/cliente/classificacao/filtro/filtroHistoricoClassificacao.xhtml");
        }
        historicosClassificacao = historicoClassificacaoDao.getHistoricosClassificacao(consultaComissaoDto);
    }

    public List<HistoricoClassificacao> getHistoricosClassificacao() {
        return historicosClassificacao;
    }

    public void setHistoricosClassificacao(List<HistoricoClassificacao> historicosClassificacao) {
        this.historicosClassificacao = historicosClassificacao;
    }
}
