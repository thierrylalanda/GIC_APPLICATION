/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l"),
    @NamedQuery(name = "Login.findLoginByPersonnel", query = "SELECT l FROM Login l WHERE l.personnel.idPersonnel = :id"),
    @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password"),
    @NamedQuery(name = "Login.findByIdGroupe", query = "SELECT l FROM Login l WHERE l.groupes.idGroupes = :groupe"),
    @NamedQuery(name = "Login.findByUsername", query = "SELECT l FROM Login l WHERE l.username = :username AND l.password = :password"),
    @NamedQuery(name = "Login.findByNiveauAcces", query = "SELECT l FROM Login l WHERE l.niveauAcces = :niveauAcces")})
public class Login implements Serializable {

    

    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_login")
    private Integer idLogin;
    
    @Column(name = "niveau_acces")
    private Integer niveauAcces;
    @Column(name = "Admin_Region")
    private Integer adminRegion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @JoinColumn(name = "personnel", referencedColumnName = "id_personnel")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Personnel personnel;
    @JoinColumn(name = "groupes", referencedColumnName = "id_groupes")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Groupes groupes;

    public Login() {
    }

    public Login(String password) {
        this.password = password;
    }

    public Login(String password, String username, int niveauAcces) {
        this.password = password;
        this.username = username;
        this.niveauAcces = niveauAcces;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNiveauAcces() {
        return niveauAcces;
    }

    public void setNiveauAcces(int niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Groupes getGroupes() {
        return groupes;
    }

    public void setGroupes(Groupes groupes) {
        this.groupes = groupes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (password != null ? password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.password == null && other.password != null) || (this.password != null && !this.password.equals(other.password))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Login[ password=" + password + " ]";
    }

    public Login(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }
    public void setNiveauAcces(Integer niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    public Integer getAdminRegion() {
        return adminRegion;
    }

    public void setAdminRegion(Integer adminRegion) {
        this.adminRegion = adminRegion;
    }

    

}
