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
@Table(name = "statistique_categorie_personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findAll", query = "SELECT s FROM StatistiqueCategoriePersonnel s"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findByPersonnel", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.personnel.idPersonnel = :region"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findAllByPersonnelAndCategorie", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.personnel.idPersonnel = :region AND s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findAllByPersonnelAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.personnel.idPersonnel = :region AND s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findByCategorie", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.categorie = :region"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.categorie = :categorie AND s.personnel.idPersonnel = :service AND s.dateSorti = :d1"),  
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findByIdstatCatPersonne", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.idstatCatPersonne = :idstatCatPersonne"),
   // @NamedQuery(name = "StatistiqueCategoriePersonnel.findByCategorie", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findByDateSorti", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueCategoriePersonnel.findByQuantite", query = "SELECT s FROM StatistiqueCategoriePersonnel s WHERE s.quantite = :quantite")})
public class StatistiqueCategoriePersonnel implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statCatPersonne")
    private Integer idstatCatPersonne;
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
    @JoinColumn(name = "personnel", referencedColumnName = "id_personnel")
    @ManyToOne(optional = false)
    private Personnel personnel;

    public StatistiqueCategoriePersonnel() {
    }

    public StatistiqueCategoriePersonnel(Integer idstatCatPersonne) {
        this.idstatCatPersonne = idstatCatPersonne;
    }

    public StatistiqueCategoriePersonnel(Integer idstatCatPersonne, String categorie, Date dateSorti, int quantite) {
        this.idstatCatPersonne = idstatCatPersonne;
        this.categorie = categorie;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdstatCatPersonne() {
        return idstatCatPersonne;
    }

    public void setIdstatCatPersonne(Integer idstatCatPersonne) {
        this.idstatCatPersonne = idstatCatPersonne;
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

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatCatPersonne != null ? idstatCatPersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueCategoriePersonnel)) {
            return false;
        }
        StatistiqueCategoriePersonnel other = (StatistiqueCategoriePersonnel) object;
        if ((this.idstatCatPersonne == null && other.idstatCatPersonne != null) || (this.idstatCatPersonne != null && !this.idstatCatPersonne.equals(other.idstatCatPersonne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueCategoriePersonnel[ idstatCatPersonne=" + idstatCatPersonne + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }
    
}
