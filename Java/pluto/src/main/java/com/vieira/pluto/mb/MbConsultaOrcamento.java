package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.dao.OrcamentoDao;
import com.vieira.pluto.dao.StatusOrcamentoDao;
import com.vieira.pluto.dto.ConsultaOrcamentoDto;
import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.Orcamento;
import com.vieira.pluto.entity.StatusOrcamento;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaOrcamento extends BasicMb{

    @Inject
    private OrcamentoDao orcamentoDao;
    @Inject
    private StatusOrcamentoDao statusOrcamentoDao;
    @Inject
    private ClienteDao clienteDao;
    private List<Orcamento> orcamentos;
    private List<Cliente> clientes;
    private List<StatusOrcamento> statusOrcamentos;
    private ConsultaOrcamentoDto consultaOrcamentoDto;

    @PostConstruct
    public void init(){
        consultaOrcamentoDto = new ConsultaOrcamentoDto();
        filtrar();
        statusOrcamentos = statusOrcamentoDao.getAll();
    }

    public void filtrar() {
        orcamentos = orcamentoDao.filterOrcamento(consultaOrcamentoDto);
        hideModal("filtroModal");
    }

    public void cancelar(Orcamento orcamento){
        orcamento.setStatusOrcamento(new StatusOrcamento(2L));
        orcamentoDao.edit(orcamento);
        orcamentos.remove(orcamento);
        addInfoMessage("Orçamento cancelado com sucesso!");
    }

    public void liberar(Orcamento orcamento){
        orcamento.setStatusOrcamento(new StatusOrcamento(3L));
        orcamentoDao.edit(orcamento);
        orcamentos.remove(orcamento);
        addInfoMessage("Orçamento liberado com sucesso!");
    }

    public boolean renderLiberar(StatusOrcamento statusOrcamento){
        return (1L)==(statusOrcamento.getId());
    }

    public boolean renderCancelar(StatusOrcamento statusOrcamento){
        return (1L)==(statusOrcamento.getId()) || 2L == (statusOrcamento.getId());
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<StatusOrcamento> getStatusOrcamentos() {
        return statusOrcamentos;
    }

    public void setStatusOrcamentos(List<StatusOrcamento> statusOrcamentos) {
        this.statusOrcamentos = statusOrcamentos;
    }

    public ConsultaOrcamentoDto getConsultaOrcamentoDto() {
        return consultaOrcamentoDto;
    }

    public void setConsultaOrcamentoDto(ConsultaOrcamentoDto consultaOrcamentoDto) {
        this.consultaOrcamentoDto = consultaOrcamentoDto;
    }
}
