package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FormaPagamentoDao;
import com.vieira.pluto.entity.FormaPagamento;

import javax.annotation.PostConstruct;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import static java.util.Objects.isNull;

@Named
@ViewScoped
public class MbCadastroFormaPagamento extends BasicMb {

    @Inject
    private FormaPagamentoDao formaPagamentoDao;
    private FormaPagamento formaPagamento;

    @PostConstruct
    public void init(){
        Long idFormaPagamento = getOnFlash(PROPERTY.FORMA_PAGAMENTO_EDITAR.name(), Long.class);
        if(isNull(idFormaPagamento)){
            novaFormaPagamento();
        } else {
            formaPagamento = formaPagamentoDao.get(idFormaPagamento);
        }
    }

    private void novaFormaPagamento() {
        formaPagamento = new FormaPagamento();
    }

    public void salvar(){
        formaPagamentoDao.save(formaPagamento);
        novaFormaPagamento();
        addInfoMessage("Forma de Pagamento salva com sucesso!");
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
