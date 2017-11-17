package com.vieira.pluto.persistence;

import org.jinq.jpa.JinqJPAStreamProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.Serializable;
import java.util.HashMap;

@ApplicationScoped
public class PersistenceUtil implements Serializable {

    private static final String UNIT_NAME = "plutoPU";
    private static HashMap<Integer, EntityManager> THREAD_LOCAL_EM = new HashMap<>();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(UNIT_NAME);
    private static final JinqJPAStreamProvider STREAMS = new JinqJPAStreamProvider(emf);

    public static EntityManager getEntityManager() {
        EntityManager em = THREAD_LOCAL_EM.get(0);
        if (em == null) {
            em = createEntityManager();
            THREAD_LOCAL_EM.put(0, em);
        }
        return em;
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeEntityManager() {
        EntityManager entityManager = THREAD_LOCAL_EM.get(0);
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        THREAD_LOCAL_EM.remove(0);
    }

    public static JinqJPAStreamProvider getJinqHibernateStreamProvider() {
        return STREAMS;
    }

}
