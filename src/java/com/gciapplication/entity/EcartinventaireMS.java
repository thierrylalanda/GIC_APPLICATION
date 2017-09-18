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
@Table(name = "ecart_inventaire_MS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EcartinventaireMS.findAll", query = "SELECT e FROM EcartinventaireMS e"),
    @NamedQuery(name = "EcartinventaireMS.findAllByIdMS", query = "SELECT e FROM EcartinventaireMS e WHERE e.idMS.idMagasin = :idMS"),
    @NamedQuery(name = "EcartinventaireMS.findByIdEcat", query = "SELECT e FROM EcartinventaireMS e WHERE e.idEcat = :idEcat"),
    @NamedQuery(name = "EcartinventaireMS.findByCategorie", query = "SELECT e FROM EcartinventaireMS e WHERE e.categorie = :categorie"),
    @NamedQuery(name = "EcartinventaireMS.findByCodeProduit", query = "SELECT e FROM EcartinventaireMS e WHERE e.codeProduit = :codeProduit"),
    @NamedQuery(name = "EcartinventaireMS.findByDesignation", query = "SELECT e FROM EcartinventaireMS e WHERE e.designation = :designation"),
    @NamedQuery(name = "EcartinventaireMS.findByLastQuantite", query = "SELECT e FROM EcartinventaireMS e WHERE e.lastQuantite = :lastQuantite"),
    @NamedQuery(name = "EcartinventaireMS.findByNewQuantite", query = "SELECT e FROM EcartinventaireMS e WHERE e.newQuantite = :newQuantite"),
    @NamedQuery(name = "EcartinventaireMS.findByEcatQuantite", query = "SELECT e FROM EcartinventaireMS e WHERE e.ecatQuantite = :ecatQuantite"),
    @NamedQuery(name = "EcartinventaireMS.findByPrixUnitaire", query = "SELECT e FROM EcartinventaireMS e WHERE e.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "EcartinventaireMS.findByPrixTotal", query = "SELECT e FROM EcartinventaireMS e WHERE e.prixTotal = :prixTotal"),
    @NamedQuery(name = "EcartinventaireMS.findByDate", query = "SELECT e FROM EcartinventaireMS e WHERE e.date >= :date1 AND e.date <= :date2 AND e.idMS.idMagasin = :idMS")})
public class EcartinventaireMS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ecat")
    private Integer idEcat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "categorie")
    private String categorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max =255)
    @Column(name = "code_produit")
    private String codeProduit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_quantite")
    private int lastQuantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "new_quantite")
    private int newQuantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ecat_quantite")
    private int ecatQuantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_unitaire")
    private double prixUnitaire;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_total")
    private double prixTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "id_MS", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinSecondaire idMS;

    public EcartinventaireMS() {
    }

    public EcartinventaireMS(Integer idEcat) {
        this.idEcat = idEcat;
    }

    public EcartinventaireMS(Integer idEcat, String categorie, String codeProduit, String designation, int lastQuantite, int newQuantite, int ecatQuantite, double prixUnitaire, double prixTotal, Date date) {
        this.idEcat = idEcat;
        this.categorie = categorie;
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.lastQuantite = lastQuantite;
        this.newQuantite = newQuantite;
        this.ecatQuantite = ecatQuantite;
        this.prixUnitaire = prixUnitaire;
        this.prixTotal = prixTotal;
        this.date = date;
    }

    public Integer getIdEcat() {
        return idEcat;
    }

    public void setIdEcat(Integer idEcat) {
        this.idEcat = idEcat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getLastQuantite() {
        return lastQuantite;
    }

    public void setLastQuantite(int lastQuantite) {
        this.lastQuantite = lastQuantite;
    }

    public int getNewQuantite() {
        return newQuantite;
    }

    public void setNewQuantite(int newQuantite) {
        this.newQuantite = newQuantite;
    }

    public int getEcatQuantite() {
        return ecatQuantite;
    }

    public void setEcatQuantite(int ecatQuantite) {
        this.ecatQuantite = ecatQuantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MagasinSecondaire getIdMS() {
        return idMS;
    }

    public void setIdMS(MagasinSecondaire idMS) {
        this.idMS = idMS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEcat != null ? idEcat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EcartinventaireMS)) {
            return false;
        }
        EcartinventaireMS other = (EcartinventaireMS) object;
        if ((this.idEcat == null && other.idEcat != null) || (this.idEcat != null && !this.idEcat.equals(other.idEcat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.EcartinventaireMS[ idEcat=" + idEcat + " ]";
    }
    
}
