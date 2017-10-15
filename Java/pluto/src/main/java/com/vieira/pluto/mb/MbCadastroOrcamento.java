package com.vieira.pluto.mb;

import com.vieira.pluto.dao.*;
import com.vieira.pluto.entity.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbCadastroOrcamento extends BasicMb {

    private Orcamento orcamento;
    private ItemOrcamento itemOrcamento;
    private TipoItemComercializavel tipoItemComercializavel;
    private PessoaVeiculo pessoaVeiculo;
    private Long decremental;
    private List<Cliente> clientes;
    private List<PessoaVeiculo> pessoaVeiculos;
    private List<FormaPagamento> formasPagamento;
    private List<TipoItemComercializavel> tiposItemComercializaveis;
    private List<ItemComercializavel> itensComercializaveis;

    @PostConstruct
    public void init(){
        decremental = 0L;
        tipoItemComercializavel = new TipoItemComercializavel(1L);
        tiposItemComercializaveis = new TipoItemComercializavelDao().getAll();
        itensComercializaveis = new ItemComercializavelDao().getsAtivos(tipoItemComercializavel.getId());
        formasPagamento = new FormaPagamentoDao().getAll();
        clientes = new ClienteDao().getAllAtivos();
        novoOrcamento();
    }

    private void novoOrcamento() {
        orcamento = new Orcamento();
        orcamento.setValorTotal(0d);
        orcamento.setItemOrcamentoList( new ArrayList<>());
        orcamento.setStatusOrcamento(new StatusOrcamento(1L));
        if (!clientes.isEmpty()){
            orcamento.setCliente(clientes.get(0));
            pessoaVeiculos = new PessoaVeiculoDao().getPessoaVeiculos(orcamento.getCliente().getPessoa().getId());
        }
        novoItem();
    }

    private void novoItem() {
        itemOrcamento = new ItemOrcamento(--decremental);
    }

    public void adicionarItem(){
        itemOrcamento.setValorVenda(itemOrcamento.getItemComercializavel().getValorVenda());
        itemOrcamento.setValorTotal(itemOrcamento.getValorVenda() * itemOrcamento.getQuantidade());
        orcamento.setValorTotal(orcamento.getValorTotal() + itemOrcamento.getValorTotal());
        orcamento.getItemOrcamentoList().add(itemOrcamento);
        novoItem();
    }

    public void excluirItem(ItemOrcamento itemOrcamentoDelete){
        orcamento.setValorTotal(orcamento.getValorTotal() - itemOrcamentoDelete.getValorTotal());
        orcamento.getItemOrcamentoList().remove(itemOrcamentoDelete);
    }

    public void salvar(){
        OrcamentoDao orcamentoDao = new OrcamentoDao();
        VeiculoOrcamento veiculoOrcamento = new VeiculoOrcamento();
        veiculoOrcamento.setCor(pessoaVeiculo.getCor());
        veiculoOrcamento.setModeloVeiculo(pessoaVeiculo.getModeloVeiculo());
        veiculoOrcamento.setPlaca(pessoaVeiculo.getPlaca());
        orcamento.setVeiculoOrcamento(veiculoOrcamento);
        orcamento.setData(new Date());
        orcamento.setUsuario(getUsuarioLogado());
        orcamentoDao.save(orcamento);
        novoOrcamento();
        addInfoMessage("Orcamento salvo com sucesso!");
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public ItemOrcamento getItemOrcamento() {
        return itemOrcamento;
    }

    public void setItemOrcamento(ItemOrcamento itemOrcamento) {
        this.itemOrcamento = itemOrcamento;
    }

    public TipoItemComercializavel getTipoItemComercializavel() {
        return tipoItemComercializavel;
    }

    public void setTipoItemComercializavel(TipoItemComercializavel tipoItemComercializavel) {
        this.tipoItemComercializavel = tipoItemComercializavel;
    }

    public PessoaVeiculo getPessoaVeiculo() {
        return pessoaVeiculo;
    }

    public void setPessoaVeiculo(PessoaVeiculo pessoaVeiculo) {
        this.pessoaVeiculo = pessoaVeiculo;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<PessoaVeiculo> getPessoaVeiculos() {
        return pessoaVeiculos;
    }

    public void setPessoaVeiculos(List<PessoaVeiculo> pessoaVeiculos) {
        this.pessoaVeiculos = pessoaVeiculos;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public List<TipoItemComercializavel> getTiposItemComercializaveis() {
        return tiposItemComercializaveis;
    }

    public void setTiposItemComercializaveis(List<TipoItemComercializavel> tiposItemComercializaveis) {
        this.tiposItemComercializaveis = tiposItemComercializaveis;
    }

    public List<ItemComercializavel> getItensComercializaveis() {
        return itensComercializaveis;
    }

    public void setItensComercializaveis(List<ItemComercializavel> itensComercializaveis) {
        this.itensComercializaveis = itensComercializaveis;
    }
}
