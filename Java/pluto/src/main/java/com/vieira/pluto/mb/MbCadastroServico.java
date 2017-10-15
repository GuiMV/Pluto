package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.TipoItemComercializavel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MbCadastroServico extends BasicMb {

    private ItemComercializavel servico;

    @PostConstruct
    public void init(){
        novoServico();
    }

    private void novoServico() {
        servico = new ItemComercializavel();
        servico.setTipoItemComercializavel(new TipoItemComercializavel(2L));
        servico.setMargemLucro(0.0);
    }

    public void salvar(){
        ItemComercializavelDao modeloVeiculoDao = new ItemComercializavelDao();
        modeloVeiculoDao.save(servico);
        novoServico();
        addInfoMessage("Serviço salvo com sucesso!");
    }

    public ItemComercializavel getServico() {
        return servico;
    }

    public void setServico(ItemComercializavel servico) {
        this.servico = servico;
    }
}
