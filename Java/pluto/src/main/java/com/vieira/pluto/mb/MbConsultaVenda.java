package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.dao.OrcamentoDao;
import com.vieira.pluto.dao.TipoTelefoneDao;
import com.vieira.pluto.dao.VendaDao;
import com.vieira.pluto.dto.ConsultaOrcamentoDto;
import com.vieira.pluto.dto.ConsultaVendaDto;
import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.Orcamento;
import com.vieira.pluto.entity.Venda;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaVenda extends BasicMb{

    @Inject
    private VendaDao vendaDao;
    @Inject
    private ClienteDao clienteDao;
    private List<Venda> vendas;
    private List<Cliente> clientes;
    private ConsultaVendaDto consultaOrcamentoDto;

    @PostConstruct
    public void init(){
        consultaOrcamentoDto = new ConsultaVendaDto();
        clientes = clienteDao.getAllAtivos();
        filtrar();
    }

    public void filtrar() {
        vendas = vendaDao.filterVenda(consultaOrcamentoDto);
        hideModal("filtroModal");
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ConsultaVendaDto getConsultaOrcamentoDto() {
        return consultaOrcamentoDto;
    }

    public void setConsultaOrcamentoDto(ConsultaVendaDto consultaOrcamentoDto) {
        this.consultaOrcamentoDto = consultaOrcamentoDto;
    }
}
