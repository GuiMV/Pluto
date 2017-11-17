package com.vieira.pluto.mb;

import com.vieira.pluto.dao.*;
import com.vieira.pluto.entity.*;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.exception.HandledException;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

@Named
@ViewScoped
public class MbCadastroVenda extends BasicMb {

    @Inject
    private VendaDao vendaDao;
    @Inject
    private TipoItemComercializavelDao tipoItemComercializavelDao;
    @Inject
    private ItemComercializavelDao itemComercializavelDao;
    @Inject
    private ClienteDao clienteDao;
    @Inject
    private FuncionarioDao funcionarioDao;
    @Inject
    private ParcelaDao parcelaDao;
    @Inject
    private PessoaVeiculoDao pessoaVeiculoDao;
    @Inject
    private FormaPagamentoDao formaPagamentoDao;
    @Inject
    private OrcamentoDao orcamentoDao;
    private Venda venda;
    private ItemVenda itemVenda;
    private TipoItemComercializavel tipoItemComercializavel;
    private PessoaVeiculo pessoaVeiculo;
    private Long decremental;
    private List<Cliente> clientes;
    private List<PessoaVeiculo> pessoaVeiculos;
    private List<FormaPagamento> formasPagamento;
    private List<TipoItemComercializavel> tiposItemComercializaveis;
    private List<ItemComercializavel> itensComercializaveis;
    private List<Funcionario> funcionarios;
    private List<Parcela> parcelas;
    private List<Orcamento> orcamentos;
    private LocalDate vencimento;
    private Integer quantidadeParcelas;
    private Boolean quitado;
    private Long idFormaPagamento;
    private Double desconto;

    @PostConstruct
    public void init() {
        decremental = 0L;
        tipoItemComercializavel = new TipoItemComercializavel(1L);
        tiposItemComercializaveis = tipoItemComercializavelDao.getAll();
        formasPagamento = formaPagamentoDao.getAll();
        clientes = clienteDao.getAllAtivos();
        funcionarios = funcionarioDao.getAllAtivos();
        parcelas = parcelaDao.getAll();
        orcamentos = orcamentoDao.getOrcamentosLiberados();
        vencimento = LocalDate.now();
        alterarTipoItemComercializavel();
        novaVenda();
    }

    public void alterarTipoItemComercializavel() {
        itensComercializaveis = itemComercializavelDao.getsAtivos(tipoItemComercializavel.getId());
    }

    private void novaVenda() {
        venda = new Venda();
        venda.setValorTotal(0d);
        venda.setDescricao("Venda de serviço");
        venda.setItemVendaList(new ArrayList<>());
        if (!clientes.isEmpty()) {
            venda.setCliente(clientes.get(0));
        }
        alterarCliente();
        novoItem();
        quitado = false;
        desconto = 0d;
        quantidadeParcelas = 1;
    }

    public void alterarCliente() {
        if (nonNull(venda.getCliente())) {
            pessoaVeiculos = pessoaVeiculoDao.getPessoaVeiculos(venda.getCliente().getPessoa().getId());
        } else {
            pessoaVeiculos = null;
        }
    }

    private void novoItem() {
        itemVenda = new ItemVenda(--decremental);
    }

    public void adicionarItem() {
        if(itemVenda.getQuantidade() < 1){
            throw new HandledException("Campo Quantidade deve ser maior que 0");
        }
        itemVenda.setValorVenda(itemVenda.getItemComercializavel().getValorVenda());
        itemVenda.setValorTotal(itemVenda.getValorVenda() * itemVenda.getQuantidade());
        venda.setValorTotal(venda.getValorTotal() + itemVenda.getValorTotal());
        if (isItemTipoServico()) {
            itemVenda.setComissao((itemVenda.getFuncionario().getComissao() / 100) * itemVenda.getValorTotal());
        }
        venda.getItemVendaList().add(itemVenda);
        novoItem();
    }

    public void excluirItem(ItemOrcamento itemOrcamentoDelete) {
        venda.setValorTotal(venda.getValorTotal() - itemOrcamentoDelete.getValorTotal());
        venda.getItemVendaList().remove(itemOrcamentoDelete);
    }

    public void selecionarOrcamento(Long idOrcamento) {
        novaVenda();
        if(nonNull(idOrcamento)){
            Orcamento orcamento = orcamentoDao.get(idOrcamento);
            venda.setCliente(orcamento.getCliente());
            venda.setDescricao(orcamento.getDescricao());

            pessoaVeiculo = new PessoaVeiculo(-1L);
            pessoaVeiculo.setPlaca(orcamento.getVeiculoOrcamento().getPlaca());
            pessoaVeiculo.setModeloVeiculo(orcamento.getVeiculoOrcamento().getModeloVeiculo());
            pessoaVeiculo.setCor(orcamento.getVeiculoOrcamento().getCor());

            pessoaVeiculos = new ArrayList<>();
            pessoaVeiculos.add(pessoaVeiculo);

            for (ItemOrcamento itemOrcamento : orcamento.getItemOrcamentoList()) {
                itemVenda.setItemComercializavel(itemOrcamento.getItemComercializavel());
                itemVenda.setQuantidade(itemOrcamento.getQuantidade());
                itemVenda.setFuncionario(itemOrcamento.getFuncionario());
                adicionarItem();
            }
        }
    }

    public void salvar() {
        VeiculoVenda veiculoVenda = new VeiculoVenda();
        veiculoVenda.setCor(pessoaVeiculo.getCor());
        veiculoVenda.setModeloVeiculo(pessoaVeiculo.getModeloVeiculo());
        veiculoVenda.setPlaca(pessoaVeiculo.getPlaca());
        venda.setVeiculoVenda(veiculoVenda);
        venda.setData(new Date());
        venda.setIdUsuario(getUsuarioLogado().getId());
        vendaDao.save(venda, vencimento, quantidadeParcelas, quitado, idFormaPagamento, desconto);
        novaVenda();
        addInfoMessage("Venda salva com sucesso!");
    }

    public boolean isItemTipoServico() {
        return 2 == tipoItemComercializavel.getId();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
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

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
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

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}
