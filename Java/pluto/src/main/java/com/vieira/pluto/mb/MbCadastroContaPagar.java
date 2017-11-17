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
public class MbCadastroContaPagar extends BasicMb {

    @Inject
    private ContaPagarDao contasPagarDao;
    @Inject
    private FormaPagamentoDao formaPagamentoDao;
    @Inject
    private ParcelaDao parcelaDao;
    @Inject
    private PessoaDao pessoaDao;
    private ContaPagar contasPagar;
    private List<Pessoa> pessoas;
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
        pessoas = pessoaDao.getAll();
        novaConta();
    }

    private void novaConta() {
        contasPagar = new ContaPagar();
        contasPagar.setValorTotal(0d);
        contasPagar.setParcelaContaPagarList( new ArrayList<>());
        if (!pessoas.isEmpty()){
            contasPagar.setPessoa(pessoas.get(0));
        }
        vencimento = LocalDate.now();
        quantidadeParcelas = 1;
        quitado = false;
        descontoTotal = 0d;
    }

    public void salvar(){
        contasPagar.setData(new Date());
        contasPagar.setIdUsuario(getUsuarioLogado().getId());
        contasPagarDao.salvar(contasPagar, quitado, quantidadeParcelas, vencimento, idFormaPagamento, descontoTotal);
        novaConta();
        addInfoMessage("Conta salva com sucesso!");
    }

    public ContaPagar getContaPagar() {
        return contasPagar;
    }

    public void setContaPagar(ContaPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
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

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public Double getDescontoTotal() {
        return descontoTotal;
    }

    public void setDescontoTotal(Double descontoTotal) {
        this.descontoTotal = descontoTotal;
    }
}
