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
@Table(name = "statistique_categorie_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatistiqueCategorieService.findAll", query = "SELECT s FROM StatistiqueCategorieService s"),
    @NamedQuery(name = "StatistiqueCategorieService.findByService", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.service.idService = :region"),
    @NamedQuery(name = "StatistiqueCategorieService.findAllByServiceAndCategorie", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.service.idService = :region AND s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieService.findAllByServiseAndCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.service.idService = :region AND s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieService.findByCategorie", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.categorie = :region"),
    @NamedQuery(name = "StatistiqueCategorieService.findAllByCategorieAndPeriode", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.categorie = :categorie AND s.dateSorti BETWEEN :d1 AND :d2"),
    @NamedQuery(name = "StatistiqueCategorieService.findAllByCategorieAndServiceAndOneDate", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.categorie = :categorie AND s.service.idService = :service AND s.dateSorti = :d1"),   
    @NamedQuery(name = "StatistiqueCategorieService.findByIdstatCatService", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.idstatCatService = :idstatCatService"),
    //@NamedQuery(name = "StatistiqueCategorieService.findByCategorie", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.categorie = :categorie"),
    @NamedQuery(name = "StatistiqueCategorieService.findByDateSorti", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.dateSorti = :dateSorti"),
    @NamedQuery(name = "StatistiqueCategorieService.findByQuantite", query = "SELECT s FROM StatistiqueCategorieService s WHERE s.quantite = :quantite")})
public class StatistiqueCategorieService implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prixtotal")
    private double prixtotal;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_statCatService")
    private Integer idstatCatService;
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
    @JoinColumn(name = "service", referencedColumnName = "id_service")
    @ManyToOne(optional = false)
    private Service service;

    public StatistiqueCategorieService() {
    }

    public StatistiqueCategorieService(Integer idstatCatService) {
        this.idstatCatService = idstatCatService;
    }

    public StatistiqueCategorieService(Integer idstatCatService, String categorie, Date dateSorti, int quantite) {
        this.idstatCatService = idstatCatService;
        this.categorie = categorie;
        this.dateSorti = dateSorti;
        this.quantite = quantite;
    }

    public Integer getIdstatCatService() {
        return idstatCatService;
    }

    public void setIdstatCatService(Integer idstatCatService) {
        this.idstatCatService = idstatCatService;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatCatService != null ? idstatCatService.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatistiqueCategorieService)) {
            return false;
        }
        StatistiqueCategorieService other = (StatistiqueCategorieService) object;
        if ((this.idstatCatService == null && other.idstatCatService != null) || (this.idstatCatService != null && !this.idstatCatService.equals(other.idstatCatService))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StatistiqueCategorieService[ idstatCatService=" + idstatCatService + " ]";
    }

    public double getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(double prixtotal) {
        this.prixtotal = prixtotal;
    }
    
}
