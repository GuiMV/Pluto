package com.vieira.pluto.persistence;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

import static java.util.Objects.isNull;

public final class PersistenceUtil {
    
    private static final String UNIT_NAME = "plutoPU";
    public static final ThreadLocal<EntityManager> SESSION = new ThreadLocal<>();  
    protected static EntityManagerFactory emf;
    protected static PersistenceUtil instance;
    protected static JinqJPAStreamProvider streams;

    public static EntityManager getEntityManager() {
         EntityManager em = (EntityManager) SESSION.get();  
         if (em == null) {
            em = createEntityManager();
         }
         return em; 
    } 
    
    private static EntityManager createEntityManager(){
        loadInstance();  
        EntityManager em = emf.createEntityManager();  
        SESSION.set(em);
        return em;
        
    }

    private static synchronized void loadInstance() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(UNIT_NAME);
        }
    }
    
    public static void closeEntityManager() {  
         EntityManager entityManager = (EntityManager) SESSION.get();  
          if (entityManager != null) {  
              entityManager.close();  
         }  
         SESSION.set(null);  
     }

     public static JinqJPAStreamProvider getJinqHibernateStreamProvider(){
        if (isNull(streams)){
            streams = new JinqJPAStreamProvider(getEntityManager().getEntityManagerFactory());
        }
         return streams;
     }


    public static <Entity> JinqStream<Entity> getEntities(Class<Entity> entityClass) {
        return getJinqHibernateStreamProvider().streamAll(getEntityManager(), entityClass);
    }
    
}
