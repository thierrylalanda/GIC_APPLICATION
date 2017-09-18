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
@Table(name = "statistique_articles_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueArticlesService.findAll", query = "SELECT s FROM StatistiqueArticlesService s"),
    @NamedQuery(name = "StatistiqueArticlesService.findByService", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.service.idService = :region"),
    @NamedQuery(name = "StatistiqueArticlesService.findAllByServiceAndCategorie", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.service.idService = :region AND s.articles = :categorie"),
    @NamedQuery(name = "StatistiqueArticlesService.findAllByServiseAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.service.idService = :region AND s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesService.findByCategorie", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.articles = :region"),
    @NamedQuery(name = "StatistiqueArticlesService.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.articles = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueArticlesService.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.articles = :categorie AND s.service.idService = :service AND s.dateSorti = :d1"),
    @NamedQuery(name = "StatistiqueArticlesService.findByIdStatservice", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.idStatservice = :idStatservice"),
    @NamedQuery(name = "StatistiqueArticlesService.findByArticles", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.articles = :articles"),
    @NamedQuery(name = "StatistiqueArticlesService.findByDateSorti", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueArticlesService.findByQuantite", query = "SELECT s FROM StatistiqueArticlesService s WHERE s.quantite = :quantite")})
public class StatistiqueArticlesService implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statservice")
    private Integer idStatservice;
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
    @JoinColumn(name = "service", referencedColumnName = "id_service")
    @ManyToOne(optional = false)
    private Service service;

    public StatistiqueArticlesService() {
    }

    public StatistiqueArticlesService(Integer idStatservice) {
        this.idStatservice = idStatservice;
    }

    public StatistiqueArticlesService(Integer idStatservice, String articles, Date dateSorti, int quantite) {
        this.idStatservice = idStatservice;
        this.articles = articles;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdStatservice() {
        return idStatservice;
    }

    public void setIdStatservice(Integer idStatservice) {
        this.idStatservice = idStatservice;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatservice != null ? idStatservice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueArticlesService)) {
            return false;
        }
        StatistiqueArticlesService other = (StatistiqueArticlesService) object;
        if ((this.idStatservice == null && other.idStatservice != null) || (this.idStatservice != null && !this.idStatservice.equals(other.idStatservice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueArticlesService[ idStatservice=" + idStatservice + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

}
