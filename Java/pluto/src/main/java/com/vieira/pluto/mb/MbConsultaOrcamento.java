package com.vieira.pluto.mb;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.vieira.pluto.dao.OrcamentoDao;
import com.vieira.pluto.dto.ConsultaOrcamentoDto;
import com.vieira.pluto.entity.Orcamento;
import com.vieira.pluto.entity.StatusOrcamento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbConsultaOrcamento extends BasicMb{

    private List<Orcamento> orcamentos;
    private OrcamentoDao orcamentoDao;
    private ConsultaOrcamentoDto consultaOrcamentoDto;

    @PostConstruct
    public void init(){
        consultaOrcamentoDto = new ConsultaOrcamentoDto();
        orcamentoDao = new OrcamentoDao();
        orcamentos = orcamentoDao.filterOrcamento(consultaOrcamentoDto);
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

    public void concluir(Orcamento orcamento){
        orcamento.setStatusOrcamento(new StatusOrcamento(4L));
        orcamentoDao.edit(orcamento);
        orcamentos.remove(orcamento);
        addInfoMessage("Orçamento concluído com sucesso!");
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
}
