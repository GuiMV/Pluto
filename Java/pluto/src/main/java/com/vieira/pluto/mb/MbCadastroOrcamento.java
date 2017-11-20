package com.vieira.pluto.mb;

import com.vieira.pluto.dao.*;
import com.vieira.pluto.entity.*;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.exception.HandledException;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

@Named
@ViewScoped
public class MbCadastroOrcamento extends BasicMb {

    @Inject
    private OrcamentoDao orcamentoDao;
    @Inject
    private  ClienteDao clienteDao;
    @Inject
    private TipoItemComercializavelDao tipoItemComercializavelDao;
    @Inject
    private  FormaPagamentoDao formaPagamentoDao;
    @Inject
    private PessoaVeiculoDao pessoaVeiculoDao;
    @Inject
    private ItemComercializavelDao itemComercializavelDao;
    @Inject
    private FuncionarioDao funcionarioDao;
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
    private List<Funcionario> funcionarios;

    @PostConstruct
    public void init(){
        decremental = 0L;
        tipoItemComercializavel = new TipoItemComercializavel(1L);
        tiposItemComercializaveis = tipoItemComercializavelDao.getAll();
        alterarTipoItemComercializavel();
        formasPagamento = formaPagamentoDao.getAll();
        clientes = clienteDao.getAllAtivos();
        funcionarios = funcionarioDao.getAllAtivos();
        novoOrcamento();
    }

    public void alterarTipoItemComercializavel() {
        itensComercializaveis = itemComercializavelDao.getsAtivos(tipoItemComercializavel.getId());
    }

    private void novoOrcamento() {
        orcamento = new Orcamento();
        orcamento.setValorTotal(0d);
        orcamento.setDescricao("Venda de serviço");
        orcamento.setItemOrcamentoList( new ArrayList<>());
        orcamento.setStatusOrcamento(new StatusOrcamento(1L));
        if (!clientes.isEmpty()){
            orcamento.setCliente(clientes.get(0));
        }
        alterarCliente();
        novoItem();
    }

    public void alterarCliente() {
        if (nonNull(orcamento.getCliente())) {
            pessoaVeiculos = pessoaVeiculoDao.getPessoaVeiculos(orcamento.getCliente().getPessoa().getId());
        } else {
            pessoaVeiculos = null;
        }
    }
    private void novoItem() {
        itemOrcamento = new ItemOrcamento(--decremental);
    }

    public void adicionarItem(){
        if(itemOrcamento.getQuantidade() < 1){
            throw new HandledException("Campo Quantidade deve ser maior que 0");
        }
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

    public boolean isItemTipoServico() {
        return 2 == tipoItemComercializavel.getId();
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

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
