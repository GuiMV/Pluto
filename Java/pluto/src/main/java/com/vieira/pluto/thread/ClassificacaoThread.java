package com.vieira.pluto.thread;

import com.vieira.pluto.business.NgClassificacao;
import com.vieira.pluto.persistence.PersistenceUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.TimerTask;

@ApplicationScoped
public class ClassificacaoThread extends TimerTask {

    @Inject
    private PersistenceUtil persistenceUtil;
    @Inject
    private NgClassificacao ngClassificacao;

    @Override
    public void run() {
        try {
            EntityManager em = persistenceUtil.getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            if(!transaction.isActive()){
                transaction.begin();
            }
            try {
                ngClassificacao.atualizarClassificacaoClientes();
                ngClassificacao.atualizarHistoricoClassificacaoClientes();
                if(transaction.getRollbackOnly()){
                    transaction.rollback();
                }else {
                    transaction.commit();
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            } finally {
                persistenceUtil.closeEntityManager();
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar classificações");
            e.printStackTrace();
        }
    }
}
