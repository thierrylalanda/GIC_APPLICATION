/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "page")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Page.findAll", query = "SELECT p FROM Page p"),
    @NamedQuery(name = "Page.findByIdPage", query = "SELECT p FROM Page p WHERE p.idPage = :idPage"),
    @NamedQuery(name = "Page.findByNomPage", query = "SELECT p FROM Page p WHERE p.nomPage = :nomPage"),
    @NamedQuery(name = "Page.findByNiveau", query = "SELECT p FROM Page p WHERE p.niveau = :niveau"),
    @NamedQuery(name = "Page.findByLien", query = "SELECT p FROM Page p WHERE p.lien = :lien")})
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_page")
    private Integer idPage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_page")
    private String nomPage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau")
    private int niveau;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lien")
    private String lien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "page")
    private List<Permissions> permissionsList;

    public Page() {
    }

    public Page(Integer idPage) {
        this.idPage = idPage;
    }

    public Page(Integer idPage, String nomPage, int niveau, String lien) {
        this.idPage = idPage;
        this.nomPage = nomPage;
        this.niveau = niveau;
        this.lien = lien;
    }

    public Integer getIdPage() {
        return idPage;
    }

    public void setIdPage(Integer idPage) {
        this.idPage = idPage;
    }

    public String getNomPage() {
        return nomPage;
    }

    public void setNomPage(String nomPage) {
        this.nomPage = nomPage;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    @XmlTransient
    public List<Permissions> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(List<Permissions> permissionsList) {
        this.permissionsList = permissionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPage != null ? idPage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Page)) {
            return false;
        }
        Page other = (Page) object;
        if ((this.idPage == null && other.idPage != null) || (this.idPage != null && !this.idPage.equals(other.idPage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Page[ idPage=" + idPage + " ]";
    }
    
}
