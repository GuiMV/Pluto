package com.vieira.pluto.dao;

import com.vieira.pluto.dto.ConsultaContaReceberDto;
import com.vieira.pluto.entity.*;
import com.vieira.pluto.entity.query.RecenciaClienteDto;
import com.vieira.pluto.entity.query.ValorFrequenciaClienteDto;
import com.vieira.pluto.persistence.GenericDao;
import com.vieira.pluto.util.Dates;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jinq.orm.stream.JinqStream;

import javax.inject.Inject;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

public class ContaReceberDao extends GenericDao<ContaReceber> {

    @Inject
    private ParcelaContaReceberDao parcelaContaReceberDao;
    @Inject
    private ConfiguracaoEmailDao configuracaoEmailDao;
    @Inject
    private QuitacaoParcelaContaReceberDao quitacaoParcelaContaReceberDao;

    public List<ContaReceber> filterContaReceber(ConsultaContaReceberDto consultaContaReceberDto) {
        JinqStream<ContaReceber> query = getEntities().where(obj -> obj.getDataExclusao() == null);
        if (nonNull(consultaContaReceberDto.getDataFim())) {
            Date dataFim = Dates.getDateInLastTimeOfDay(consultaContaReceberDto.getDataFim());
            query = query.where(obj -> !obj.getData().after(dataFim));
        }
        if (nonNull(consultaContaReceberDto.getDataInicio())) {
            Date dataInicio = Dates.getDateInMidnight(consultaContaReceberDto.getDataInicio());
            query = query.where(obj -> !obj.getData().before(dataInicio));
        }
        Long idCliente = consultaContaReceberDto.getIdCliente();
        if (nonNull(idCliente)) {
            query = query.where(obj -> obj.getCliente().getId().equals(idCliente));
        }
        return query.toList();
    }

    public void salvar(ContaReceber contaReceber, Boolean quitado, Integer quantidadeParcelas, LocalDate vencimento, Long idFormaPagamento, Double descontoTotal){
        Double descontoParcela = descontoTotal / quantidadeParcelas;
        if (quitado) {
            contaReceber.setDataQuitacao(LocalDate.now());
        }
        add(contaReceber);

        double valorParcela = contaReceber.getValorTotal() / quantidadeParcelas;
        for (int i = 0; i < quantidadeParcelas; i++) {
            ParcelaContaReceber parcela = new ParcelaContaReceber();
            parcela.setValor(valorParcela);
            parcela.setDataVencimento(vencimento);
            parcela.setContaReceber(contaReceber);
            parcela.setDataQuitacao(contaReceber.getDataQuitacao());
            parcelaContaReceberDao.add(parcela);

            vencimento = vencimento.plusMonths(1);

            if(quitado){
                QuitacaoParcelaContaReceber quitacaoParcelaContaReceber = new QuitacaoParcelaContaReceber();
                quitacaoParcelaContaReceber.setIdFormaPagamento(idFormaPagamento);
                quitacaoParcelaContaReceber.setIdParcelaContaReceber(parcela.getId());
                quitacaoParcelaContaReceber.setValor(parcela.getValor() - descontoParcela);
                quitacaoParcelaContaReceberDao.add(quitacaoParcelaContaReceber);
            }
        }
        if (quitado) {
            enviarEmail(contaReceber.getCliente().getPessoa());
        }
    }

    public void enviarEmail(Pessoa pessoa) {
        try {
            SimpleEmail email = new SimpleEmail();
            ConfiguracaoEmail configuracaoEmail = configuracaoEmailDao.getConfiguracao();
            email.setHostName(configuracaoEmail.getHostName());
            email.setSSLOnConnect(configuracaoEmail.getSsl());
            email.setSmtpPort(configuracaoEmail.getPort());
            email.setSslSmtpPort(String.valueOf(configuracaoEmail.getPort()));
            email.setStartTLSEnabled(true);

            System.out.println("autenticando...");
            email.setAuthentication(configuracaoEmail.getUserName(), configuracaoEmail.getPassword());
            System.out.println("autenticado...");

            email.setFrom(configuracaoEmail.getUserName(), configuracaoEmail.getEmitter());
            email.addTo(pessoa.getEmail().getEndereco());
            email.setSubject(configuracaoEmail.getSubject());
            email.setMsg(configuracaoEmail.getMessage());

            System.out.println("enviando...");
            email.send();
            System.out.println("Email enviado!");
        } catch (EmailException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<RecenciaClienteDto> getRecenciaClientes(Date dataInicial, Date dataFinal) {
        getEntityManager().flush();
        getEntityManager().clear();
        StringBuilder sb = new StringBuilder();
        sb.append("select cr.id_cliente, max(cr.data) as data from conta_receber cr\n");
        sb.append(String.format("where cr.data > '%tF 23:59:59'\n", dataInicial));
        sb.append(String.format("and cr.data <= '%tF 23:59:59'\n", dataFinal));
        sb.append("and cr.data_exclusao is null\n");
        sb.append("GROUP BY cr.id_cliente");
        Query query = getEntityManager().createNativeQuery(sb.toString(), "RecenciaClienteDto");
        return query.getResultList();
    }

    public ValorFrequenciaClienteDto getValorFrequenciaCliente(Date dataInicial, Date dataFinal, Long idCliente) {
        StringBuilder sb = new StringBuilder();
        sb.append("select id_cliente, sum(valor_total) as valor, count(id_cliente) as frequencia from conta_receber\n");
        sb.append(String.format("where data > '%tF 23:59:59'\n", dataInicial));
        sb.append(String.format("and data <= '%tF 23:59:59'\n", dataFinal));
        sb.append("and id_cliente = ").append(idCliente).append("\n");
        sb.append("and data_exclusao is null\n");
        sb.append("GROUP BY id_cliente");
        Query query = getEntityManager().createNativeQuery(sb.toString(), "ValorFrequenciaClienteDto");
        return ValorFrequenciaClienteDto.class.cast(query.getSingleResult());
    }
}
