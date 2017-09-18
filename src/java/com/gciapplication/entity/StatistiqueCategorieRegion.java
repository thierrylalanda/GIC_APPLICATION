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
@Table(name = "statistique_categorie_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueCategorieRegion.findAll", query = "SELECT s FROM StatistiqueCategorieRegion s"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findByRegion", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.region.idRegion = :region"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findAllByRgionAndCategorie", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.region.idRegion = :region AND s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findAllByRgionAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.region.idRegion = :region AND s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findByCategorie", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.categorie = :region"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.categorie = :categorie AND s.region.idRegion = :service AND s.dateSorti = :d1"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findByIdstatCatRegion", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.idstatCatRegion = :idstatCatRegion"),
    //@NamedQuery(name = "StatistiqueCategorieRegion.findByCategorie", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findByDateSorti", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueCategorieRegion.findByQuantite", query = "SELECT s FROM StatistiqueCategorieRegion s WHERE s.quantite = :quantite")})
public class StatistiqueCategorieRegion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statCatRegion")
    private Integer idstatCatRegion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "categorie")
    private String categorie;
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

    public StatistiqueCategorieRegion() {
    }

    public StatistiqueCategorieRegion(Integer idstatCatRegion) {
        this.idstatCatRegion = idstatCatRegion;
    }

    public StatistiqueCategorieRegion(Integer idstatCatRegion, String categorie, Date dateSorti, int quantite) {
        this.idstatCatRegion = idstatCatRegion;
        this.categorie = categorie;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdstatCatRegion() {
        return idstatCatRegion;
    }

    public void setIdstatCatRegion(Integer idstatCatRegion) {
        this.idstatCatRegion = idstatCatRegion;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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
        hash += (idstatCatRegion != null ? idstatCatRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueCategorieRegion)) {
            return false;
        }
        StatistiqueCategorieRegion other = (StatistiqueCategorieRegion) object;
        if ((this.idstatCatRegion == null && other.idstatCatRegion != null) || (this.idstatCatRegion != null && !this.idstatCatRegion.equals(other.idstatCatRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueCategorieRegion[ idstatCatRegion=" + idstatCatRegion + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

}
