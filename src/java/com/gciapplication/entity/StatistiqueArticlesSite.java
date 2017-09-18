/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "statistique_articles_site")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueArticlesSite.findAll", query = "SELECT s FROM StatistiqueArticlesSite s"),
    @NamedQuery(name = "StatistiqueArticlesSite.findBySite", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.site.idSite = :region"),
    @NamedQuery(name = "StatistiqueArticlesSite.findAllBySiteAndCategorie", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.site.idSite = :region AND s.articles = :categorie"),
    @NamedQuery(name = "StatistiqueArticlesSite.findAllBySiteAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.site.idSite = :region AND s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesSite.findByCategorie", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.articles = :region"),
    @NamedQuery(name = "StatistiqueArticlesSite.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesSite.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.articles = :categorie AND s.site.idSite = :service AND s.dateSorti = :d1"),
    @NamedQuery(name = "StatistiqueArticlesSite.findByIdstatSite", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.idstatSite = :idstatSite"),
    @NamedQuery(name = "StatistiqueArticlesSite.findByArticles", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.articles = :articles"),
    @NamedQuery(name = "StatistiqueArticlesSite.findByDateSorti", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueArticlesSite.findByQuantite", query = "SELECT s FROM StatistiqueArticlesSite s WHERE s.quantite = :quantite")})
public class StatistiqueArticlesSite implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statSite")
    private Integer idstatSite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "articles")
    private String articles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_sorti")
    @Temporal(TemporalType.DATE)
    private Date dateSorti;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private int quantite;
    @JoinColumn(name = "site", referencedColumnName = "id_site")
    @ManyToOne(optional = false)
    private Site site;

    public StatistiqueArticlesSite() {
    }

    public StatistiqueArticlesSite(Integer idstatSite) {
        this.idstatSite = idstatSite;
    }

    public StatistiqueArticlesSite(Integer idstatSite, String articles, Date dateSorti, int quantite) {
        this.idstatSite = idstatSite;
        this.articles = articles;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdstatSite() {
        return idstatSite;
    }

    public void setIdstatSite(Integer idstatSite) {
        this.idstatSite = idstatSite;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public Date getDateSorti() {
        return dateSorti;
    }

    public void setDateSorti(Date dateSorti) {
        this.dateSorti = dateSorti;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatSite != null ? idstatSite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueArticlesSite)) {
            return false;
        }
        StatistiqueArticlesSite other = (StatistiqueArticlesSite) object;
        if ((this.idstatSite == null && other.idstatSite != null) || (this.idstatSite != null && !this.idstatSite.equals(other.idstatSite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueArticlesSite[ idstatSite=" + idstatSite + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

}
