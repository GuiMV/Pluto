package com.vieira.pluto.mb;

import com.vieira.pluto.dao.*;
import com.vieira.pluto.entity.*;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class MbCadastroContaReceber extends BasicMb {

    @Inject
    private ContaReceberDao contaReceberDao;
    @Inject
    private FormaPagamentoDao formaPagamentoDao;
    @Inject
    private ParcelaDao parcelaDao;
    @Inject
    private ClienteDao clienteDao;
    private ContaReceber contaReceber;
    private List<Cliente> clientes;
    private List<FormaPagamento> formasPagamento;
    private List<Parcela> parcelas;
    private LocalDate vencimento;
    private Integer quantidadeParcelas;
    private Boolean quitado;
    private Long idFormaPagamento;
    private Double descontoTotal;

    @PostConstruct
    public void init(){
        formasPagamento = formaPagamentoDao.getAll();
        parcelas = parcelaDao.getAll();
        clientes = clienteDao.getAllAtivos();
        novaConta();
    }

    private void novaConta() {
        contaReceber = new ContaReceber();
        contaReceber.setValorTotal(0d);
        contaReceber.setData(new Date());
        contaReceber.setParcelaContaReceberList( new ArrayList<>());
        if (!clientes.isEmpty()){
            contaReceber.setCliente(clientes.get(0));
        }
        vencimento = LocalDate.now();
        quantidadeParcelas = 1;
        descontoTotal = 0d;
        quitado = false;
    }

    public void salvar(){
        contaReceber.setIdUsuario(getUsuarioLogado().getId());
        contaReceberDao.salvar(contaReceber, quitado, quantidadeParcelas, vencimento, idFormaPagamento, descontoTotal);
        novaConta();
        addInfoMessage("Conta salva com sucesso!");
    }

    public ContaReceber getContaReceber() {
        return contaReceber;
    }

    public void setContaReceber(ContaReceber contaReceber) {
        this.contaReceber = contaReceber;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public Boolean getQuitado() {
        return quitado;
    }

    public void setQuitado(Boolean quitado) {
        this.quitado = quitado;
    }

    public Long getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public Double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(Double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }
}
