package com.vieira.pluto.mb;


import com.vieira.pluto.dao.FuncionarioDao;
import com.vieira.pluto.dto.ConsultaComissaoDto;
import com.vieira.pluto.entity.Funcionario;
import org.omnifaces.cdi.ViewScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class MbFiltroComissao extends BasicMb {

    @Inject
    private FuncionarioDao funcionarioDao;
    private ConsultaComissaoDto consultaComissaoDto;
    private List<Funcionario> funcionarios;

    @PostConstruct
    public void init(){
        consultaComissaoDto = new ConsultaComissaoDto();
        funcionarios = funcionarioDao.getAllAtivos();
    }

    public void pesquisasr(){
        putOnSession("ConsultaComissaoDto", consultaComissaoDto);
        redirect("/pluto/pages/venda/comissao/consulta/consultaComissao.xhtml");
    }

    public ConsultaComissaoDto getConsultaComissaoDto() {
        return consultaComissaoDto;
    }

    public void setConsultaComissaoDto(ConsultaComissaoDto consultaComissaoDto) {
        this.consultaComissaoDto = consultaComissaoDto;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
