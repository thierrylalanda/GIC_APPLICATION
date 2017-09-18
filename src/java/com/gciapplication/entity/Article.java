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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "Article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByIdArticle", query = "SELECT a FROM Article a WHERE a.idArticle = :idArticle"),
    @NamedQuery(name = "Article.findByIdCategorieProduit", query = "SELECT a FROM Article a WHERE a.categorie.idCategorieProduit = :categorie"),
    @NamedQuery(name = "Article.findByCode", query = "SELECT a FROM Article a WHERE a.code = :code"),
    @NamedQuery(name = "Article.findByDesignation", query = "SELECT a FROM Article a WHERE a.designation = :designation"),
    @NamedQuery(name = "Article.findByPrix", query = "SELECT a FROM Article a WHERE a.prix = :prix"),
    @NamedQuery(name = "Article.findByCritique", query = "SELECT a FROM Article a WHERE a.critique = :critique")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_article")
    private Integer idArticle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix")
    private double prix;
    @Basic(optional = false)
    @NotNull
    @Column(name = "critique")
    private int critique;
    @JoinColumn(name = "categorie", referencedColumnName = "id_categorie_produit")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CategorieProduit categorie;

    public Article() {
    }

    public Article(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Article(Integer idArticle, String code, String designation, double prix, int critique) {
        this.idArticle = idArticle;
        this.code = code;
        this.designation = designation;
        this.prix = prix;
        this.critique = critique;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getCritique() {
        return critique;
    }

    public void setCritique(int critique) {
        this.critique = critique;
    }

    public CategorieProduit getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieProduit categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticle != null ? idArticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idArticle == null && other.idArticle != null) || (this.idArticle != null && !this.idArticle.equals(other.idArticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Article[ idArticle=" + idArticle + " ]";
    }
    
}
