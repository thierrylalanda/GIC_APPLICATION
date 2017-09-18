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
@Table(name = "Butget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Butget.findAll", query = "SELECT b FROM Butget b"),
    @NamedQuery(name = "Butget.findBudgetForOnCategorieProduit", query = "SELECT b FROM Butget b WHERE b.magasin.idMagasin = :idMS AND b.typeBudget = :typebudget AND b.idCategorie.idCategorieProduit = :idCategorie"),
    @NamedQuery(name = "Butget.findByIdBudget", query = "SELECT b FROM Butget b WHERE b.idBudget = :idBudget"),
     @NamedQuery(name = "Butget.findByIdMS", query = "SELECT b FROM Butget b WHERE b.magasin.idMagasin = :idMagasin"),
      @NamedQuery(name = "Butget.findByRegion", query = "SELECT b FROM Butget b WHERE b.magasin.site.region.nomRegion = :region"),
    @NamedQuery(name = "Butget.findByRegionAndTypeBudget", query = "SELECT DISTINCT b.typeBudget FROM Butget b WHERE b.magasin.site.region.nomRegion = :region"),
    @NamedQuery(name = "Butget.findLastBudget", query = "SELECT b FROM Butget b WHERE b.idBudget = SELECT MAX(b.idBudget) FROM Butget b"),
    @NamedQuery(name = "Butget.findByMontant", query = "SELECT b FROM Butget b WHERE b.montant = :montant"),
    @NamedQuery(name = "Butget.findByDateDatribution", query = "SELECT b FROM Butget b WHERE b.dateDatribution = :dateDatribution"),
    @NamedQuery(name = "Butget.findByDateExpiration", query = "SELECT b FROM Butget b WHERE b.dateExpiration = :dateExpiration")})
public class Butget implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_budget")
    private Integer idBudget;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_datribution")
    @Temporal(TemporalType.DATE)
    private Date dateDatribution;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_expiration")
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    @Size(max = 255)
    @Column(name = "type_budget")
    private String typeBudget;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Double montant;
    @Column(name = "montant_restant")
    private Double montantRestant;
    @JoinColumn(name = "id_categorie", referencedColumnName = "id_categorie_produit")
    @ManyToOne
    private CategorieProduit idCategorie;
    @JoinColumn(name = "magasin", referencedColumnName = "id_magasin")
    @ManyToOne
    private MagasinSecondaire magasin;

    public Butget() {
    }

    public Butget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public Butget(Integer idBudget, double montant, Date dateDatribution, Date dateExpiration) {
        this.idBudget = idBudget;
        this.montant = montant;
        this.dateDatribution = dateDatribution;
        this.dateExpiration = dateExpiration;
    }

    public Integer getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public Date getDateDatribution() {
        return dateDatribution;
    }

    public void setDateDatribution(Date dateDatribution) {
        this.dateDatribution = dateDatribution;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBudget != null ? idBudget.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Butget)) {
            return false;
        }
        Butget other = (Butget) object;
        if ((this.idBudget == null && other.idBudget != null) || (this.idBudget != null && !this.idBudget.equals(other.idBudget))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Butget[ idBudget=" + idBudget + " ]";
    }

    public String getTypeBudget() {
        return typeBudget;
    }

    public void setTypeBudget(String typeBudget) {
        this.typeBudget = typeBudget;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Double getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public CategorieProduit getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(CategorieProduit idCategorie) {
        this.idCategorie = idCategorie;
    }

    public MagasinSecondaire getMagasin() {
        return magasin;
    }

    public void setMagasin(MagasinSecondaire magasin) {
        this.magasin = magasin;
    }

}
