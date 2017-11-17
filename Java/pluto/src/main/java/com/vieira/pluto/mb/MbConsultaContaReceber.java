package com.vieira.pluto.mb;

import com.vieira.pluto.dao.*;
import com.vieira.pluto.dto.ConsultaContaReceberDto;
import com.vieira.pluto.entity.*;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaContaReceber extends BasicMb{

    @Inject
    private ContaReceberDao contaReceberDao;
    @Inject
    private ParcelaContaReceberDao parcelaContaReceberDao;
    @Inject
    private FormaPagamentoDao formaPagamentoDao;
    @Inject
    private ClienteDao clienteDao;
    private List<ContaReceber> contasReceber;
    private List<FormaPagamento> formasPagamento;
    private ConsultaContaReceberDto consultaContaReceberDto;
    private List<ParcelaContaReceber> parcelasQuitar;
    private List<Cliente> clientes;
    private Long idFormaPagamento;
    private Double descontoParcela;
    private ContaReceber contaReceber;


    @PostConstruct
    public void init(){
        consultaContaReceberDto = new ConsultaContaReceberDto();
        clientes = clienteDao.getAllAtivos();
        formasPagamento = formaPagamentoDao.getAll();
        filtrar();
        descontoParcela = 0d;
    }

    public void cancelar(ContaReceber contaReceber){
        contaReceber.setDataExclusao(new Date());
        contaReceberDao.edit(contaReceber);
        contasReceber.remove(contaReceber);
        addInfoMessage("Conta cancelada com sucesso!");
    }

    public void quitar(ContaReceber contaReceber){
        this.contaReceber = contaReceber;
        parcelasQuitar = parcelaContaReceberDao.getParcelas(contaReceber.getId());
        showModal("parcelasModal");
    }

    public void quitar(ParcelaContaReceber parcelaContaReceber){
        parcelaContaReceberDao.quitarParcela(parcelaContaReceber, idFormaPagamento, descontoParcela);
        descontoParcela = 0d;
        filtrar();
        hideModal("parcelasModal");
        addInfoMessage("Parcela quitada com sucesso!");
    }

    public void filtrar(){
        contasReceber = contaReceberDao.filterContaReceber(consultaContaReceberDto);
        hideModal("filtroModal");
    }

    public List<ContaReceber> getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(List<ContaReceber> contasReceber) {
        this.contasReceber = contasReceber;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public ConsultaContaReceberDto getConsultaContaReceberDto() {
        return consultaContaReceberDto;
    }

    public void setConsultaContaReceberDto(ConsultaContaReceberDto consultaContaReceberDto) {
        this.consultaContaReceberDto = consultaContaReceberDto;
    }

    public List<ParcelaContaReceber> getParcelasQuitar() {
        return parcelasQuitar;
    }

    public void setParcelasQuitar(List<ParcelaContaReceber> parcelasQuitar) {
        this.parcelasQuitar = parcelasQuitar;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Double getDescontoParcela() {
        return descontoParcela;
    }

    public void setDescontoParcela(Double descontoParcela) {
        this.descontoParcela = descontoParcela;
    }

    public ContaReceber getContaReceber() {
        return contaReceber;
    }

    public void setContaReceber(ContaReceber contaReceber) {
        this.contaReceber = contaReceber;
    }
}
