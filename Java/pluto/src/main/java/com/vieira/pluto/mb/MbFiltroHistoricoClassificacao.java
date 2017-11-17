package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.dto.ConsultaHistoricoClassificacaoDto;
import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.enums.Mes;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named
@ViewScoped
public class MbFiltroHistoricoClassificacao extends BasicMb {

    @Inject
    private ClienteDao clienteDao;
    private ConsultaHistoricoClassificacaoDto consultaHistoricoClassificacaoDto;
    private List<Cliente> clientes;
    private Mes[] meses;

    @PostConstruct
    public void init() {
        consultaHistoricoClassificacaoDto = new ConsultaHistoricoClassificacaoDto();
        clientes = clienteDao.getAllAtivos();
        meses = Mes.values();
    }

    public void pesquisasr() {
        putOnSession("ConsultaHistoricoClassificacaoDto", consultaHistoricoClassificacaoDto);
        redirect("/pluto/pages/cliente/classificacao/consulta/consultaHistoricoClassificacao.xhtml");
    }

    public ConsultaHistoricoClassificacaoDto getConsultaHistoricoClassificacaoDto() {
        return consultaHistoricoClassificacaoDto;
    }

    public void setConsultaHistoricoClassificacaoDto(ConsultaHistoricoClassificacaoDto consultaHistoricoClassificacaoDto) {
        this.consultaHistoricoClassificacaoDto = consultaHistoricoClassificacaoDto;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Mes[] getMeses() {
        return meses;
    }

    public void setMeses(Mes[] meses) {
        this.meses = meses;
    }
}
