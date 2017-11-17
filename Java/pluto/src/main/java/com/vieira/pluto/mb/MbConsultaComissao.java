package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ItemVendaDao;
import com.vieira.pluto.dto.ConsultaComissaoDto;
import com.vieira.pluto.entity.query.ComissaoFuncionarioDto;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Named
@ViewScoped
public class MbConsultaComissao extends BasicMb {

    @Inject
    private ItemVendaDao itemVendaDao;
    private List<ComissaoFuncionarioDto> comissoes;

    @PostConstruct
    public void init() {
        ConsultaComissaoDto consultaComissaoDto = getOnSession("ConsultaComissaoDto", ConsultaComissaoDto.class);
        if (Objects.isNull(consultaComissaoDto)) {
            redirect("/pluto/pages/venda/comissao/filtro/filtroComissao.xhtml");
        }
        comissoes = itemVendaDao.getComissoes(consultaComissaoDto);
    }

    public List<ComissaoFuncionarioDto> getComissoes() {
        return comissoes;
    }

    public void setComissoes(List<ComissaoFuncionarioDto> comissoes) {
        this.comissoes = comissoes;
    }
}
