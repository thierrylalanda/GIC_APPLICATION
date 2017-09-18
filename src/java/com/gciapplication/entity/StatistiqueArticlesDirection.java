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
@Table(name = "statistique_articles_direction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueArticlesDirection.findAll", query = "SELECT s FROM StatistiqueArticlesDirection s"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findByDirection", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.direction.directeur = :region"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findAllByDirectionAndCategorie", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.direction.idDirection = :region AND s.articles = :categorie"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findAllByDirectionAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.direction.idDirection = :region AND s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findByCategorie", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.articles = :region"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.articles = :categorie AND s.direction.idDirection = :service AND s.dateSorti = :d1"),  
    @NamedQuery(name = "StatistiqueArticlesDirection.findByIdstatDirect", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.idstatDirect = :idstatDirect"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findByArticles", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.articles = :articles"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findByDateSorti", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueArticlesDirection.findByQuantite", query = "SELECT s FROM StatistiqueArticlesDirection s WHERE s.quantite = :quantite")})
public class StatistiqueArticlesDirection implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prixtotal")
    private Double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statDirect")
    private Integer idstatDirect;
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
    @JoinColumn(name = "direction", referencedColumnName = "id_direction")
    @ManyToOne(optional = false)
    private Direction direction;

    public StatistiqueArticlesDirection() {
    }

    public StatistiqueArticlesDirection(Integer idstatDirect) {
        this.idstatDirect = idstatDirect;
    }

    public StatistiqueArticlesDirection(Integer idstatDirect, String articles, Date dateSorti, int quantite) {
        this.idstatDirect = idstatDirect;
        this.articles = articles;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdstatDirect() {
        return idstatDirect;
    }

    public void setIdstatDirect(Integer idstatDirect) {
        this.idstatDirect = idstatDirect;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatDirect != null ? idstatDirect.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueArticlesDirection)) {
            return false;
        }
        StatistiqueArticlesDirection other = (StatistiqueArticlesDirection) object;
        if ((this.idstatDirect == null && other.idstatDirect != null) || (this.idstatDirect != null && !this.idstatDirect.equals(other.idstatDirect))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueArticlesDirection[ idstatDirect=" + idstatDirect + " ]";
    }

    public Double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(Double prixtotal) {
        this.prixtotal = prixtotal;
    }
    
}
