package com.vieira.pluto.filter;

import com.vieira.pluto.persistence.PersistenceUtil;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PersistenceFilter implements Filter{
        
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        if(!transaction.isActive()){
            transaction.begin();
        }
        try {
            chain.doFilter(request, response);
            if(transaction.getRollbackOnly()){
                transaction.rollback();
            }else {
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }        
    }

    @Override
    public void destroy() {
        PersistenceUtil.closeEntityManager();
    }

}
