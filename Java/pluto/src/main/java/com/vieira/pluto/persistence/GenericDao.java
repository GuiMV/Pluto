package com.vieira.pluto.persistence;

import org.jinq.orm.stream.JinqStream;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;

import static java.util.Objects.isNull;

public abstract class GenericDao<Entity>  implements Serializable{
    
    private final EntityManager em;
    protected final Class<Entity> entityClass;
    private JinqStream<Entity> entities;

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
        return getEntities().toList();
    }
    
    public void add(Entity entity){
        em.persist(entity);
    }
    
    public void addAll(List<Entity> entities){
        for (Entity entity : entities) {
            add(entity);
        }
    }
    
    public Entity edit(Entity entity){
        return em.merge(entity);
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

    public JinqStream<Entity> getEntities() {
        if (isNull(entities)){
            entities = PersistenceUtil.getEntities(entityClass);
        }
        return entities;
    }
}
