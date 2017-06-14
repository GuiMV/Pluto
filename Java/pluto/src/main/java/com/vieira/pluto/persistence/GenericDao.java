package com.vieira.pluto.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDao<Entity> {
    
    private final EntityManager em;
    private Class<Entity> entityClass = ((Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

    public GenericDao() {
        this.em = PersistenceUtil.getEntityManager();
    }
    
    public GenericDao(Class<Entity> entityClass) {
        this.em = PersistenceUtil.getEntityManager();
        this.entityClass = entityClass;
    }
    
    public Entity get(Object primatyKey){
        return em.find(entityClass, primatyKey);
    }
    
    public List<Entity> getAll(){
        String sql = String.format("FROM %s", entityClass.getSimpleName());
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
    
    public void add(Entity entity){
        em.persist(entity);
        em.flush();
    }
    
    public void addAll(List<Entity> entities){
        for (Entity entity : entities) {
            add(entity);
        }
    }
    
    public void edit(Entity entity){
        em.merge(entity);
        em.flush();
    }
    
    public void editAll(List<Entity> entities){
        for (Entity entity : entities) {
            edit(entity);
        }
    }
    
    public void delete(Entity entity){
        em.remove(entity);
        em.flush();
    }
    
    public void deleteAll(List<Entity> entities){
        for (Entity entity : entities) {
            delete(entity);
        }
    }
    
    protected EntityManager getEntityManager(){
        return em;
    }
}
