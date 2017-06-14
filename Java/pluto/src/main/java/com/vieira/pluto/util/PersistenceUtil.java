package com.vieira.pluto.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceUtil {
    
    private static final String UNIT_NAME = "plutoPU";
    public static final ThreadLocal<EntityManager> SESSION = new ThreadLocal<>();  
    protected static EntityManagerFactory emf;
    protected static PersistenceUtil instance; 

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
    
}
