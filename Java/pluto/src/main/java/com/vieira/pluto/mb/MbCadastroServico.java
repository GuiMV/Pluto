package com.vieira.pluto.mb;

import com.vieira.pluto.dao.ItemComercializavelDao;
import com.vieira.pluto.entity.ItemComercializavel;
import com.vieira.pluto.entity.TipoItemComercializavel;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.vieira.pluto.enums.PROPERTY;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;

import static java.util.Objects.isNull;

@Named
@ViewScoped
public class MbCadastroServico extends BasicMb {

    @Inject
    private ItemComercializavelDao itemComercializavelDao;
    private ItemComercializavel servico;

    @PostConstruct
    public void init(){
        Long idServico = getOnFlash(PROPERTY.SERVICO_EDITAR.name(), Long.class);
        if(isNull(idServico)){
            novoServico();
        } else {
            servico = itemComercializavelDao.get(idServico);
        }

    }

    private void novoServico() {
        servico = new ItemComercializavel();
        servico.setTipoItemComercializavel(new TipoItemComercializavel(2L));
    }

    public void salvar(){
        itemComercializavelDao.save(servico);
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
