package com.vieira.pluto.filter;

import com.vieira.pluto.persistence.PersistenceUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PersistenceFilter implements Filter {

    @Inject
    private PersistenceUtil persistenceUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().startsWith(req.getContextPath() + "/resources/") || req.getRequestURI().startsWith(req.getContextPath() + "/javax.faces.resource/")) {
            chain.doFilter(request, response);
        } else {
            EntityManager em = persistenceUtil.getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                if (!transaction.isActive()) {
                    transaction.begin();
                }
                chain.doFilter(request, response);
                if (transaction.getRollbackOnly()) {
                    transaction.rollback();
                } else {
                    transaction.commit();
                }
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            } finally {
                persistenceUtil.closeEntityManager();
            }
        }

    }

    @Override
    public void destroy() {
        persistenceUtil.closeEntityManager();
    }

}
