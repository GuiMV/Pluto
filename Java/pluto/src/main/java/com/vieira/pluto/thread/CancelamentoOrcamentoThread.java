package com.vieira.pluto.thread;

import com.vieira.pluto.business.NgClassificacao;
import com.vieira.pluto.dao.OrcamentoDao;
import com.vieira.pluto.persistence.PersistenceUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.TimerTask;


@ApplicationScoped
public class CancelamentoOrcamentoThread extends TimerTask {

    @Inject
    private PersistenceUtil persistenceUtil;
    @Inject
    private OrcamentoDao orcamentoDao;

    @Override
    public void run() {
        try {
            EntityManager em = persistenceUtil.getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            if(!transaction.isActive()){
                transaction.begin();
            }
            try {
                orcamentoDao.cancelarOrcamentosExpirados();

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
            System.out.println("Erro ao cancelar Orçamentos");
            e.printStackTrace();
        }
    }
}
