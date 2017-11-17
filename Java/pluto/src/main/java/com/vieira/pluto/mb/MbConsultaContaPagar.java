package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ContaPagarDao;
import com.vieira.pluto.dao.FormaPagamentoDao;
import com.vieira.pluto.dao.ParcelaContaPagarDao;
import com.vieira.pluto.dao.PessoaDao;
import com.vieira.pluto.dto.ConsultaContaPagarDto;
import com.vieira.pluto.entity.ContaPagar;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.entity.FormaPagamento;
import com.vieira.pluto.entity.ParcelaContaPagar;
import com.vieira.pluto.entity.Pessoa;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaContaPagar extends BasicMb{

    @Inject
    private ContaPagarDao contaReceberDao;
    @Inject
    private ParcelaContaPagarDao parcelaContaPagarDao;
    @Inject
    private FormaPagamentoDao formaPagamentoDao;
    @Inject
    private PessoaDao pessoaDao;
    private List<ContaPagar> contasPagar;
    private List<FormaPagamento> formasPagamento;
    private ConsultaContaPagarDto consultaContaPagarDto;
    private List<ParcelaContaPagar> parcelasQuitar;
    private List<Pessoa> pessoas;
    private Long idFormaPagamento;
    private Double descontoParcela;

    @PostConstruct
    public void init(){
        consultaContaPagarDto = new ConsultaContaPagarDto();
        pessoas = pessoaDao.getAll();
        formasPagamento = formaPagamentoDao.getAll();
        filtrar();
        descontoParcela = 0d;
    }

    public void filtrar() {
        contasPagar = contaReceberDao.filterContaPagar(consultaContaPagarDto);
        hideModal("filtroModal");
    }

    public void cancelar(ContaPagar contaReceber){
        contaReceber.setDataExclusao(new Date());
        contaReceberDao.edit(contaReceber);
        contasPagar.remove(contaReceber);
        addInfoMessage("Conta cancelada com sucesso!");
    }

    public void quitar(ContaPagar contaReceber){
        parcelasQuitar = parcelaContaPagarDao.getParcelas(contaReceber.getId());
        showModal("parcelasModal");
    }

    public void quitar(ParcelaContaPagar parcelaContaPagar){
        parcelaContaPagarDao.quitarParcela(parcelaContaPagar, idFormaPagamento, descontoParcela);
        descontoParcela = 0d;
        filtrar();
        hideModal("parcelasModal");
        addInfoMessage("Parcela quitada com sucesso!");
    }

    public List<ContaPagar> getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(List<ContaPagar> contasPagar) {
        this.contasPagar = contasPagar;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public ConsultaContaPagarDto getConsultaContaPagarDto() {
        return consultaContaPagarDto;
    }

    public void setConsultaContaPagarDto(ConsultaContaPagarDto consultaContaPagarDto) {
        this.consultaContaPagarDto = consultaContaPagarDto;
    }

    public List<ParcelaContaPagar> getParcelasQuitar() {
        return parcelasQuitar;
    }

    public void setParcelasQuitar(List<ParcelaContaPagar> parcelasQuitar) {
        this.parcelasQuitar = parcelasQuitar;
    }

    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Double getDescontoParcela() {
        return descontoParcela;
    }

    public void setDescontoParcela(Double descontoParcela) {
        this.descontoParcela = descontoParcela;
    }
}
