package com.vieira.pluto.mb;

import com.vieira.pluto.dao.CorDao;
import com.vieira.pluto.entity.Cor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class MbConsultaCor extends BasicMb {

    private List<Cor> cores;

    @PostConstruct
    public void init(){
        CorDao corDao = new CorDao();
        cores = corDao.getAll();
    }

    public List<Cor> getCores() {
        return cores;
    }
}
