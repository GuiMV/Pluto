package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FormaPagamentoDao;
import com.vieira.pluto.entity.FormaPagamento;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbConsultaFormaPagamento extends BasicMb {

    private List<FormaPagamento> formasPagamento;

    @PostConstruct
    public void init(){
        FormaPagamentoDao formaPagamentoDao = new FormaPagamentoDao();
        formasPagamento = formaPagamentoDao.getAll();
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }
}
