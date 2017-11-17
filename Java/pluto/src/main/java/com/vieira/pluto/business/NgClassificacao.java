package com.vieira.pluto.business;

import com.vieira.pluto.dao.ClienteDao;
import com.vieira.pluto.dao.ConfiguracaoClassificacaoDao;
import com.vieira.pluto.dao.ContaReceberDao;
import com.vieira.pluto.dao.HistoricoClassificacaoDao;
import com.vieira.pluto.entity.Cliente;
import com.vieira.pluto.entity.ConfiguracaoClassificacao;
import com.vieira.pluto.entity.HistoricoClassificacao;
import com.vieira.pluto.entity.query.RecenciaClienteDto;
import com.vieira.pluto.entity.query.ValorFrequenciaClienteDto;
import com.vieira.pluto.util.Dates;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

public class NgClassificacao implements Serializable {

    private static final int RECENCIA_OURO = 2;
    private static final int RECENCIA_PRATA = 1;
    private static final int FREQUENCIA_OURO = 2;
    private static final int FREQUENCIA_PRATA = 1;
    private static final int VALOR_OURO = 2;
    private static final int VALOR_PRATA = 1;
    private static final int PONTUACAO_OURO = 6;
    private static final int PONTUACAO_PRATA = 3;
    private static final int PONTUACAO_INICIAL = 0;
    private static final long ID_CLASSIFICACAO_OURO = 2L;
    private static final long ID_CLASSIFICACAO_PRATA = 3L;
    private static final long ID_CLASSIFICACAO_BRONZE = 4L;
    @Inject
    private ContaReceberDao contaReceberDao;
    @Inject
    private ClienteDao clienteDao;
    @Inject
    private ConfiguracaoClassificacaoDao configuracaoClassificacaoDao;
    @Inject
    private HistoricoClassificacaoDao historicoClassificacaoDao;

    public void atualizarClassificacaoClientes() {
        System.out.println(String.format("%1$tF %1$tT ----Iniciando Classificação---", new Date()));
        ConfiguracaoClassificacao configuracaoClassificacao = configuracaoClassificacaoDao.getConfiguracao();
        Date dataAtual = Dates.getDateInMidnight(new Date());
        Date dataInicial = Dates.minusMonths(dataAtual, configuracaoClassificacao.getPeriodo());
        Date dataOuro = Dates.minusDays(dataAtual, configuracaoClassificacao.getRecenciaOuro());
        Date dataPrata = Dates.minusDays(dataAtual, configuracaoClassificacao.getRecenciaPrata());

        clienteDao.removerClassificacao();
        List<RecenciaClienteDto> recenciaClientes = contaReceberDao.getRecenciaClientes(dataInicial, dataAtual);
        for (RecenciaClienteDto recenciaClienteDto : recenciaClientes) {
            Date dataRecencia = Dates.getDateInLastTimeOfDay(recenciaClienteDto.getData());
            Date dataInicialFrequenciaValor = Dates.minusMonths(dataRecencia, configuracaoClassificacao.getPeriodo());
            ValorFrequenciaClienteDto valorFrequenciaCliente = contaReceberDao.getValorFrequenciaCliente(dataInicialFrequenciaValor, dataRecencia, recenciaClienteDto.getIdCliente());

            int pontuacao = PONTUACAO_INICIAL;
            if (!recenciaClienteDto.getData().before(dataOuro)){
                pontuacao += RECENCIA_OURO;
            } else if (!recenciaClienteDto.getData().before(dataPrata)){
                pontuacao += RECENCIA_PRATA;
            }

            if (valorFrequenciaCliente.getFrequencia() >= configuracaoClassificacao.getFrequenciaOuro()){
                pontuacao += FREQUENCIA_OURO;
            } else if (valorFrequenciaCliente.getFrequencia() >= configuracaoClassificacao.getFrequenciaPrata()){
                pontuacao += FREQUENCIA_PRATA;
            }

            if (valorFrequenciaCliente.getValor() >= configuracaoClassificacao.getValorOuro()){
                pontuacao += VALOR_OURO;
            } else if (valorFrequenciaCliente.getValor() >= configuracaoClassificacao.getValorPrata()){
                pontuacao += VALOR_PRATA;
            }
            Cliente cliente = clienteDao.get(recenciaClienteDto.getIdCliente());
            if (pontuacao >= PONTUACAO_OURO) {
                cliente.setIdClassificacao(ID_CLASSIFICACAO_OURO);
            } else if (pontuacao >= PONTUACAO_PRATA) {
                cliente.setIdClassificacao(ID_CLASSIFICACAO_PRATA);
            } else {
                cliente.setIdClassificacao(ID_CLASSIFICACAO_BRONZE);
            }
            clienteDao.edit(cliente);
        }
        System.out.println(String.format("%1$tF %1$tT ----Finalizando Classificação---", new Date()));
    }

    public void atualizarHistoricoClassificacaoClientes() {
        System.out.println(String.format("%1$tF %1$tT ----Iniciando Inclusão Histórico de Classificação---", new Date()));
        ConfiguracaoClassificacao configuracaoClassificacao = configuracaoClassificacaoDao.getConfiguracao();

        Date mesAtual =  Dates.getDateInMidnight(new Date());
        Date dataHistorico =  Dates.minusMonths(mesAtual, 2);
        if(nonNull(configuracaoClassificacao.getUltimoMesHistorico()) && nonNull(configuracaoClassificacao.getUltimoAnoHistorico())){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataHistorico);
            calendar.set(Calendar.MONTH, configuracaoClassificacao.getUltimoMesHistorico());
            calendar.set(Calendar.YEAR, configuracaoClassificacao.getUltimoAnoHistorico());
            dataHistorico = calendar.getTime();
        }

        Date mesInicioConsulta = Dates.plusMonths(dataHistorico, 1);
        calcularHistorico(mesInicioConsulta, configuracaoClassificacao);
        configuracaoClassificacaoDao.edit(configuracaoClassificacao);
        System.out.println(String.format("%1$tF %1$tT ----Finalizando Inclusão Histórico de Classificação---", new Date()));
    }

    private void calcularHistorico(Date mesInicioConsulta, ConfiguracaoClassificacao configuracaoClassificacao) {
        Date mesConsulta = mesInicioConsulta;
        Date mesAtual =  Dates.getDateInMidnight(new Date());
        mesAtual = Dates.getBeginningOfTheMonth(mesAtual);

        while (mesConsulta.before(mesAtual)){
            mesConsulta = Dates.getEndOfTheMonth(mesConsulta);
            Date dataInicial = Dates.minusMonths(mesConsulta, configuracaoClassificacao.getPeriodo());
            Date dataOuro = Dates.minusDays(mesConsulta, configuracaoClassificacao.getRecenciaOuro());
            Date dataPrata = Dates.minusDays(mesConsulta, configuracaoClassificacao.getRecenciaPrata());

            List<RecenciaClienteDto> recenciaClientes = contaReceberDao.getRecenciaClientes(dataInicial, mesConsulta);
            for (RecenciaClienteDto recenciaClienteDto : recenciaClientes) {
                Date dataRecencia = Dates.getDateInLastTimeOfDay(recenciaClienteDto.getData());
                Date dataInicialFrequenciaValor = Dates.minusMonths(dataRecencia, configuracaoClassificacao.getPeriodo());
                ValorFrequenciaClienteDto valorFrequenciaCliente = contaReceberDao.getValorFrequenciaCliente(dataInicialFrequenciaValor, dataRecencia, recenciaClienteDto.getIdCliente());

                int pontuacao = PONTUACAO_INICIAL;
                if (!recenciaClienteDto.getData().before(dataOuro)){
                    pontuacao += RECENCIA_OURO;
                } else if (!recenciaClienteDto.getData().before(dataPrata)){
                    pontuacao += RECENCIA_PRATA;
                }

                if (valorFrequenciaCliente.getFrequencia() >= configuracaoClassificacao.getFrequenciaOuro()){
                    pontuacao += FREQUENCIA_OURO;
                } else if (valorFrequenciaCliente.getFrequencia() >= configuracaoClassificacao.getFrequenciaPrata()){
                    pontuacao += FREQUENCIA_PRATA;
                }

                if (valorFrequenciaCliente.getValor() >= configuracaoClassificacao.getValorOuro()){
                    pontuacao += VALOR_OURO;
                } else if (valorFrequenciaCliente.getValor() >= configuracaoClassificacao.getValorPrata()){
                    pontuacao += VALOR_PRATA;
                }
                HistoricoClassificacao historicoClassificacao = new HistoricoClassificacao();
                historicoClassificacao.setIdCliente(recenciaClienteDto.getIdCliente());
                historicoClassificacao.setMes(Dates.getMes(mesConsulta));
                historicoClassificacao.setAno(Dates.getAno(mesConsulta));

                if (pontuacao >= PONTUACAO_OURO) {
                    historicoClassificacao.setIdClassificacao(ID_CLASSIFICACAO_OURO);
                } else if (pontuacao >= PONTUACAO_PRATA) {
                    historicoClassificacao.setIdClassificacao(ID_CLASSIFICACAO_PRATA);
                } else {
                    historicoClassificacao.setIdClassificacao(ID_CLASSIFICACAO_BRONZE);
                }

                historicoClassificacaoDao.add(historicoClassificacao);
            }
            configuracaoClassificacao.setUltimoAnoHistorico(Dates.getAno(mesConsulta));
            configuracaoClassificacao.setUltimoMesHistorico(Dates.getMes(mesConsulta));

            mesConsulta = Dates.plusMonths(mesConsulta, 1);
        }
    }
}
