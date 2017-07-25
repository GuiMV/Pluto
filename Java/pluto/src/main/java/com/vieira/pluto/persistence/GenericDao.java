package com.vieira.pluto.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class GenericDao<Entity>  implements Serializable{
    
    private final EntityManager em;
    private final Class<Entity> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        this.em = PersistenceUtil.getEntityManager();
        final Type genericSuperclass = getClass().getGenericSuperclass();
        final ParameterizedType param = ParameterizedType.class.cast(genericSuperclass);
        final Type typeParam = param.getActualTypeArguments()[0];
        entityClass = Class.class.cast(typeParam);
    }
    
    public GenericDao(Class<Entity> entityClass) {
        this.em = PersistenceUtil.getEntityManager();
        this.entityClass = entityClass;
    }
    
    public Entity get(Object primaryKey){
        return em.find(entityClass, primaryKey);
    }
    
    @SuppressWarnings("unchecked")
    public List<Entity> getAll(){
        String sql = String.format("FROM %s", entityClass.getSimpleName());
        Query query = em.createQuery(sql);
        return query.getResultList();
    }
    
    public void add(Entity entity){
        em.persist(entity);
    }
    
    public void addAll(List<Entity> entities){
        for (Entity entity : entities) {
            add(entity);
        }
    }
    
    public void edit(Entity entity){
        em.merge(entity);
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
