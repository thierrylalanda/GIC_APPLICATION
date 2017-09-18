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
@Table(name = "statistique_articles_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueArticlesRegion.findAll", query = "SELECT s FROM StatistiqueArticlesRegion s"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findByRegion", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.region.idRegion = :region"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findAllByRgionAndCategorie", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.region.idRegion = :region AND s.articles = :categorie"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findAllByRgionAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.region.idRegion = :region AND s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findByCategorie", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.articles = :region"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.articles = :categorie AND s.region.idRegion = :service AND s.dateSorti = :d1"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findByIdStatregion", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.idStatregion = :idStatregion"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findByArticles", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.articles = :articles"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findByDateSorti", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueArticlesRegion.findByQuantite", query = "SELECT s FROM StatistiqueArticlesRegion s WHERE s.quantite = :quantite")})
public class StatistiqueArticlesRegion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statregion")
    private Integer idStatregion;
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
    @JoinColumn(name = "region", referencedColumnName = "id_region")
    @ManyToOne(optional = false)
    private Region region;

    public StatistiqueArticlesRegion() {
    }

    public StatistiqueArticlesRegion(Integer idStatregion) {
        this.idStatregion = idStatregion;
    }

    public StatistiqueArticlesRegion(Integer idStatregion, String articles, Date dateSorti, int quantite) {
        this.idStatregion = idStatregion;
        this.articles = articles;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdStatregion() {
        return idStatregion;
    }

    public void setIdStatregion(Integer idStatregion) {
        this.idStatregion = idStatregion;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatregion != null ? idStatregion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueArticlesRegion)) {
            return false;
        }
        StatistiqueArticlesRegion other = (StatistiqueArticlesRegion) object;
        if ((this.idStatregion == null && other.idStatregion != null) || (this.idStatregion != null && !this.idStatregion.equals(other.idStatregion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueArticlesRegion[ idStatregion=" + idStatregion + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

}
