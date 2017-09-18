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
@Table(name = "statistique_articles_personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findAll", query = "SELECT s FROM StatistiqueArticlesPersonnel s"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findByPersonnel", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.personnel.idPersonnel = :region"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findAllByPersonnelAndCategorie", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.personnel.idPersonnel = :region AND s.articles = :categorie"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findAllByPersonnelAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.personnel.idPersonnel = :region AND s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findByCategorie", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.articles = :region"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.articles = :categorie AND s.personnel.idPersonnel = :service AND s.dateSorti = :d1"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findByIdStatpersonne", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.idStatpersonne = :idStatpersonne"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findByArticles", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.articles = :articles"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findByDateSortiAndPersonnel", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.dateSorti BETWEEN :d1 AND :d2 AND s.personnel.idPersonnel = :personnel"),
    @NamedQuery(name = "StatistiqueArticlesPersonnel.findByQuantite", query = "SELECT s FROM StatistiqueArticlesPersonnel s WHERE s.quantite = :quantite")})
public class StatistiqueArticlesPersonnel implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statpersonne")
    private Integer idStatpersonne;
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
    @JoinColumn(name = "personnel", referencedColumnName = "id_personnel")
    @ManyToOne(optional = false)
    private Personnel personnel;

    public StatistiqueArticlesPersonnel() {
    }

    public StatistiqueArticlesPersonnel(Integer idStatpersonne) {
        this.idStatpersonne = idStatpersonne;
    }

    public StatistiqueArticlesPersonnel(Integer idStatpersonne, String articles, Date dateSorti, int quantite) {
        this.idStatpersonne = idStatpersonne;
        this.articles = articles;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdStatpersonne() {
        return idStatpersonne;
    }

    public void setIdStatpersonne(Integer idStatpersonne) {
        this.idStatpersonne = idStatpersonne;
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

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatpersonne != null ? idStatpersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueArticlesPersonnel)) {
            return false;
        }
        StatistiqueArticlesPersonnel other = (StatistiqueArticlesPersonnel) object;
        if ((this.idStatpersonne == null && other.idStatpersonne != null) || (this.idStatpersonne != null && !this.idStatpersonne.equals(other.idStatpersonne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueArticlesPersonnel[ idStatpersonne=" + idStatpersonne + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

}
