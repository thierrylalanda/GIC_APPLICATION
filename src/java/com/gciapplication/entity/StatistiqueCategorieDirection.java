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
@Table(name = "statistique_categorie_direction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueCategorieDirection.findAll", query = "SELECT s FROM StatistiqueCategorieDirection s"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findByDirection", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.direction.directeur = :region"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findAllByDirectionAndCategorie", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.direction.idDirection = :region AND s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findAllByDirectionAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.direction.idDirection = :region AND s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findByCategorie", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.categorie = :region"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.categorie = :categorie AND s.direction.idDirection = :service AND s.dateSorti = :d1"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findByIdstatCatDirect", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.idstatCatDirect = :idstatCatDirect"),
    //@NamedQuery(name = "StatistiqueCategorieDirection.findByCategorie", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findByDateSorti", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueCategorieDirection.findByQuantite", query = "SELECT s FROM StatistiqueCategorieDirection s WHERE s.quantite = :quantite")})
public class StatistiqueCategorieDirection implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statCatDirect")
    private Integer idstatCatDirect;
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
    @JoinColumn(name = "direction", referencedColumnName = "id_direction")
    @ManyToOne(optional = false)
    private Direction direction;

    public StatistiqueCategorieDirection() {
    }

    public StatistiqueCategorieDirection(Integer idstatCatDirect) {
        this.idstatCatDirect = idstatCatDirect;
    }

    public StatistiqueCategorieDirection(Integer idstatCatDirect, String categorie, Date dateSorti, int quantite) {
        this.idstatCatDirect = idstatCatDirect;
        this.categorie = categorie;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdstatCatDirect() {
        return idstatCatDirect;
    }

    public void setIdstatCatDirect(Integer idstatCatDirect) {
        this.idstatCatDirect = idstatCatDirect;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatCatDirect != null ? idstatCatDirect.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueCategorieDirection)) {
            return false;
        }
        StatistiqueCategorieDirection other = (StatistiqueCategorieDirection) object;
        if ((this.idstatCatDirect == null && other.idstatCatDirect != null) || (this.idstatCatDirect != null && !this.idstatCatDirect.equals(other.idstatCatDirect))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueCategorieDirection[ idstatCatDirect=" + idstatCatDirect + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }

}
