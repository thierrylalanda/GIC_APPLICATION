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
@Table(name = "statistique_articles_magasin_ms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findAll", query = "SELECT s FROM StatistiqueArticlesMagasinMs s"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findByService", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.magasin.idMagasin = :region"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findAllByServiceAndCategorie", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.magasin.idMagasin = :region AND s.articles = :categorie"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findAllByServiseAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.magasin.idMagasin = :region AND s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findByCategorie", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.articles = :region"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.articles = :categorie AND s.magasin.idMagasin = :service AND s.dateSorti = :d1"),    
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findByIdstatMag", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.idstatMag = :idstatMag"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findByArticles", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.articles = :articles"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findByDateSorti", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findByQuantite", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.quantite = :quantite"),
    @NamedQuery(name = "StatistiqueArticlesMagasinMs.findByPrixtotal", query = "SELECT s FROM StatistiqueArticlesMagasinMs s WHERE s.prixtotal = :prixtotal")})
public class StatistiqueArticlesMagasinMs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statMag")
    private Integer idstatMag;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;
    @JoinColumn(name = "magasin", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinSecondaire magasin;

    public StatistiqueArticlesMagasinMs() {
    }

    public StatistiqueArticlesMagasinMs(Integer idstatMag) {
        this.idstatMag = idstatMag;
    }

    public StatistiqueArticlesMagasinMs(Integer idstatMag, String articles, Date dateSorti, int quantite, double prixtotal) {
        this.idstatMag = idstatMag;
        this.articles = articles;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
        this.prixtotal = prixtotal;
    }

    public Integer getIdstatMag() {
        return idstatMag;
    }

    public void setIdstatMag(Integer idstatMag) {
        this.idstatMag = idstatMag;
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

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

    public MagasinSecondaire getMagasin() {
        return magasin;
    }

    public void setMagasin(MagasinSecondaire magasin) {
        this.magasin = magasin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatMag != null ? idstatMag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueArticlesMagasinMs)) {
            return false;
        }
        StatistiqueArticlesMagasinMs other = (StatistiqueArticlesMagasinMs) object;
        if ((this.idstatMag == null && other.idstatMag != null) || (this.idstatMag != null && !this.idstatMag.equals(other.idstatMag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueArticlesMagasinMs[ idstatMag=" + idstatMag + " ]";
    }
    
}
