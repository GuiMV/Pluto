package com.vieira.pluto.filter;

import com.vieira.pluto.entity.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Usuario usuario = null;
        HttpSession session = HttpServletRequest.class.cast(request).getSession(false);

        if (session != null){
            usuario = Usuario.class.cast(session.getAttribute("usuarioLogado"));
        }    

        if (usuario == null) {
            String contextPath = HttpServletRequest.class.cast(request).getContextPath();
            HttpServletResponse.class.cast(response).sendRedirect(contextPath + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        } 
    }

    @Override
    public void destroy() {
    }

}
