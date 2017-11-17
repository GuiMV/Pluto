package com.vieira.pluto.dao;

import com.vieira.pluto.dto.ConsultaComissaoDto;
import com.vieira.pluto.entity.ItemVenda;
import com.vieira.pluto.entity.query.ComissaoFuncionarioDto;
import com.vieira.pluto.persistence.GenericDao;
import com.vieira.pluto.persistence.PersistenceUtil;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class ItemVendaDao extends GenericDao<ItemVenda> {

    @SuppressWarnings("unchecked")
    public List<ComissaoFuncionarioDto> getComissoes(ConsultaComissaoDto consultaComissaoDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("select sum(iv.comissao) as comissao, v.id, p.razao_social, mv.nome, vv.placa from item_venda iv\n");
        sb.append("join venda v on iv.id_venda = v.id\n");
        sb.append("join cliente c on v.id_cliente = c.id\n");
        sb.append("join pessoa p on c.id_pessoa = p.id\n");
        sb.append("join veiculo_venda vv on v.id_veiculo_venda = vv.id\n");
        sb.append("join modelo_veiculo mv on vv.id_modelo_veiculo = mv.id\n");
        sb.append("where iv.id_funcionario = ").append(consultaComissaoDto.getIdFuncionario()).append("\n");
        sb.append(String.format("and v.data >= '%tF 00:00:00'\n", consultaComissaoDto.getDataInicial()));
        sb.append(String.format("and v.data <= '%tF 23:59:59'\n", consultaComissaoDto.getDataFinal()));
        sb.append("GROUP BY 2,3,4,5");
        Query query = getEntityManager().createNativeQuery(sb.toString(), "ComissaoFuncionarioDto");
        return query.getResultList();
    }
}
