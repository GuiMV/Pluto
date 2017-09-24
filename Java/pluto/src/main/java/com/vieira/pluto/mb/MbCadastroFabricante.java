package com.vieira.pluto.mb;

import com.vieira.pluto.dao.FabricanteDao;
import com.vieira.pluto.entity.Fabricante;
import com.vieira.pluto.entity.TipoPessoa;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Objects;

@Named
@ViewScoped
public class MbCadastroFabricante extends BasicMb implements Serializable{

    private Fabricante fabricante;
    private FabricanteDao fabricanteDao;
    @Inject
    private MbPessoa mbPessoa;

    @PostConstruct
    public void init() {
        fabricanteDao = new FabricanteDao();
        mbPessoa.setTipoPessoa(new TipoPessoa(2L));
        fabricante = getOnSession("fabricanteEditar", Fabricante.class);
        if (Objects.isNull(fabricante)) {
           novoFabricante();
        } else {
            mbPessoa.setPessoaCompleta(fabricante.getPessoa());
        }
        removeFromSession("fabricanteEditar");
    }

    private void novoFabricante() {
        fabricante = new Fabricante();
        mbPessoa.novaPessoa();
    }
    
    public void salvar(){
        fabricante.setPessoa(mbPessoa.getPessoaCompleta());
        fabricanteDao.save(fabricante);
        novoFabricante();
        addInfoMessage("Fabricante salvo com sucesso!");
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

}
