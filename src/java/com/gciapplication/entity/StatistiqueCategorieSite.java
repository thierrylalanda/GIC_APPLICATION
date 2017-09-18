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
@Table(name = "statistique_categorie_site")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueCategorieSite.findAll", query = "SELECT s FROM StatistiqueCategorieSite s"),
    @NamedQuery(name = "StatistiqueCategorieSite.findBySite", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.site.idSite = :region"),
    @NamedQuery(name = "StatistiqueCategorieSite.findAllBySiteAndCategorie", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.site.idSite = :region AND s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieSite.findAllBySiteAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.site.idSite = :region AND s.categorie = :categorie AND s.dateSortie BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieSite.findByCategorie", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.categorie = :region"),
    @NamedQuery(name = "StatistiqueCategorieSite.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.categorie = :categorie AND s.dateSortie BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieSite.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.categorie = :categorie AND s.site.idSite = :service AND s.dateSortie = :d1"),
    @NamedQuery(name = "StatistiqueCategorieSite.findByIdstatCatSite", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.idstatCatSite = :idstatCatSite"),
   // @NamedQuery(name = "StatistiqueCategorieSite.findByCategorie", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieSite.findByQuantite", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.quantite = :quantite"),
    @NamedQuery(name = "StatistiqueCategorieSite.findByDateSortie", query = "SELECT s FROM StatistiqueCategorieSite s WHERE s.dateSortie = :dateSortie")})
public class StatistiqueCategorieSite implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statCatSite")
    private Integer idstatCatSite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "categorie")
    private String categorie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private int quantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_Sortie")
    @Temporal(TemporalType.DATE)
    private Date dateSortie;
    @JoinColumn(name = "site", referencedColumnName = "id_site")
    @ManyToOne(optional = false)
    private Site site;

    public StatistiqueCategorieSite() {
    }

    public StatistiqueCategorieSite(Integer idstatCatSite) {
        this.idstatCatSite = idstatCatSite;
    }

    public StatistiqueCategorieSite(Integer idstatCatSite, String categorie, int quantite, Date dateSortie) {
        this.idstatCatSite = idstatCatSite;
        this.categorie = categorie;
        this.quantite = quantite;
        this.dateSortie = dateSortie;
    }

    public Integer getIdstatCatSite() {
        return idstatCatSite;
    }

    public void setIdstatCatSite(Integer idstatCatSite) {
        this.idstatCatSite = idstatCatSite;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
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
        hash += (idstatCatSite != null ? idstatCatSite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueCategorieSite)) {
            return false;
        }
        StatistiqueCategorieSite other = (StatistiqueCategorieSite) object;
        if ((this.idstatCatSite == null && other.idstatCatSite != null) || (this.idstatCatSite != null && !this.idstatCatSite.equals(other.idstatCatSite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueCategorieSite[ idstatCatSite=" + idstatCatSite + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

}
