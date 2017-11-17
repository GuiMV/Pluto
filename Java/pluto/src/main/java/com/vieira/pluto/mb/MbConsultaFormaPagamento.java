package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FormaPagamentoDao;
import com.vieira.pluto.entity.FormaPagamento;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@Named
@ViewScoped
public class MbConsultaFormaPagamento extends BasicMb {

    @Inject
    private FormaPagamentoDao formaPagamentoDao;
    private List<FormaPagamento> formasPagamento;

    @PostConstruct
    public void init(){
        formasPagamento = formaPagamentoDao.getAll();
    }

    public void editar(FormaPagamento formaPagamento){
        putOnFlash(PROPERTY.FORMA_PAGAMENTO_EDITAR.name(), formaPagamento.getId());
        redirectOnContextPath("/pages/formaPagamento/cadastro/cadastroFormaPagamento.xhtml");
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }
}
