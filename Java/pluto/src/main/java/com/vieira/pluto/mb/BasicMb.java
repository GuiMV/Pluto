package com.vieira.pluto.mb;

import com.vieira.pluto.entity.Usuario;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class BasicMb implements Serializable {

    public void addErrorMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        context.addMessage(null, facesMessage);
    }

    public void addInfoMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        context.addMessage(null, facesMessage);
    }

    public void addWarnMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, message, null);
        context.addMessage(null, facesMessage);
    }

    public void addFatalMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null);
        context.addMessage(null, facesMessage);
    }

    public void putOnSession(String key, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put(key, value);
    }

    public void removeFromSession(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove(key);
    }

    public Object getOnSession(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().get(key);
    }
    
    public <T> T getOnSession(String key, Class<T> classe) {
        return classe.cast(getOnSession(key));
    }

    public void redirect(String url) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            addErrorMessage("Erro ao redirecionar Pagina!");
        }
    }

    public void redirectOnContextPath(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        redirect(context.getExternalContext().getRequestContextPath() + url);
    }

    public Usuario getUsuarioLogado() {
        return getOnSession("usuarioLogado", Usuario.class);
    }

}
