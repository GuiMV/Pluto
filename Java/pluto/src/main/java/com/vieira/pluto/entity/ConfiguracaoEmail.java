/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vieira.pluto.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "configuracao_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfiguracaoEmail.findAll", query = "SELECT c FROM ConfiguracaoEmail c"),
    @NamedQuery(name = "ConfiguracaoEmail.findById", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.id = :id"),
    @NamedQuery(name = "ConfiguracaoEmail.findByHostName", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.hostName = :hostName"),
    @NamedQuery(name = "ConfiguracaoEmail.findByPort", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.port = :port"),
    @NamedQuery(name = "ConfiguracaoEmail.findBySsl", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.ssl = :ssl"),
    @NamedQuery(name = "ConfiguracaoEmail.findByUserName", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.userName = :userName"),
    @NamedQuery(name = "ConfiguracaoEmail.findByPassword", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.password = :password"),
    @NamedQuery(name = "ConfiguracaoEmail.findByEmitter", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.emitter = :emitter"),
    @NamedQuery(name = "ConfiguracaoEmail.findBySubject", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.subject = :subject"),
    @NamedQuery(name = "ConfiguracaoEmail.findByMessage", query = "SELECT c FROM ConfiguracaoEmail c WHERE c.message = :message")})
public class ConfiguracaoEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "host_name")
    private String hostName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "port")
    private int port;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ssl")
    private boolean ssl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "emitter")
    private String emitter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "message")
    private String message;

    public ConfiguracaoEmail() {
    }

    public ConfiguracaoEmail(Long id) {
        this.id = id;
    }

    public ConfiguracaoEmail(Long id, String hostName, int port, boolean ssl, String userName, String password, String emitter, String subject, String message) {
        this.id = id;
        this.hostName = hostName;
        this.port = port;
        this.ssl = ssl;
        this.userName = userName;
        this.password = password;
        this.emitter = emitter;
        this.subject = subject;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean getSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmitter() {
        return emitter;
    }

    public void setEmitter(String emitter) {
        this.emitter = emitter;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguracaoEmail)) {
            return false;
        }
        ConfiguracaoEmail other = (ConfiguracaoEmail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vieira.pluto.entity.ConfiguracaoEmail[ id=" + id + " ]";
    }
    
}
