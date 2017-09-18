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
import javax.persistence.FetchType;
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
@Table(name = "categorie_produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategorieProduit.findAll", query = "SELECT c FROM CategorieProduit c"),
    @NamedQuery(name = "CategorieProduit.findLastInser", query = "SELECT c FROM CategorieProduit c WHERE  c.idCategorieProduit = SELECT MAX(c.idCategorieProduit) FROM CategorieProduit c"),
    @NamedQuery(name = "CategorieProduit.findByIdCategorieProduit", query = "SELECT c FROM CategorieProduit c WHERE c.idCategorieProduit = :idCategorieProduit"),
    @NamedQuery(name = "CategorieProduit.findByTypeCategorie", query = "SELECT DISTINCT c FROM CategorieProduit c WHERE c.typeCategorie = :typeCategorie")})
public class CategorieProduit implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie", fetch = FetchType.EAGER)
    private List<Article> articleList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categorie_produit")
    private Integer idCategorieProduit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type_categorie")
    private String typeCategorie;
    @OneToMany(mappedBy = "idCategorie")
    private List<Butget> butgetList;

    public CategorieProduit() {
    }

    public CategorieProduit(Integer idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    public CategorieProduit(Integer idCategorieProduit, String typeCategorie) {
        this.idCategorieProduit = idCategorieProduit;
        this.typeCategorie = typeCategorie;
    }

    public Integer getIdCategorieProduit() {
        return idCategorieProduit;
    }

    public void setIdCategorieProduit(Integer idCategorieProduit) {
        this.idCategorieProduit = idCategorieProduit;
    }

    public String getTypeCategorie() {
        return typeCategorie;
    }

    public void setTypeCategorie(String typeCategorie) {
        this.typeCategorie = typeCategorie;
    }

    @XmlTransient
    public List<Butget> getButgetList() {
        return butgetList;
    }

    public void setButgetList(List<Butget> butgetList) {
        this.butgetList = butgetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategorieProduit != null ? idCategorieProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategorieProduit)) {
            return false;
        }
        CategorieProduit other = (CategorieProduit) object;
        if ((this.idCategorieProduit == null && other.idCategorieProduit != null) || (this.idCategorieProduit != null && !this.idCategorieProduit.equals(other.idCategorieProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Authentification.CategorieProduit[ idCategorieProduit=" + idCategorieProduit + " ]";
    }

    @XmlTransient
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
    
}
