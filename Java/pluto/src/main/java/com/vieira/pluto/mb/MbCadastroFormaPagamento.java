package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FormaPagamentoDao;
import com.vieira.pluto.entity.FormaPagamento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MbCadastroFormaPagamento extends BasicMb {

    private FormaPagamento formaPagamento;

    @PostConstruct
    public void init(){
        formaPagamento = new FormaPagamento();
    }

    public void salvar(){
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao();
        formaPagamentoDao.add(formaPagamento);
        formaPagamento = new FormaPagamento();
        addInfoMessage("Forma de Pagamento salva com sucesso!");
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
