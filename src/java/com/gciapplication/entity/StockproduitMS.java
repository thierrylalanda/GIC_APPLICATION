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
@Table(name = "stock_produit_MS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockproduitMS.findAll", query = "SELECT s FROM StockproduitMS s"),
    @NamedQuery(name = "StockproduitMS.findStockByMS", query = "SELECT s FROM StockproduitMS s WHERE s.categorie.magasinSecondaire.idMagasin = :idMS"),
    @NamedQuery(name = "StockproduitMS.findStockByLasInsert", query = "SELECT s FROM StockproduitMS s WHERE s.idStock = SELECT MAX(s.idStock)  FROM StockproduitMS s"),
    @NamedQuery(name = "StockproduitMS.findStockByidCategorie", query = "SELECT s FROM StockproduitMS s WHERE s.categorie.idCategorie = :categorie"),
    @NamedQuery(name = "StockproduitMS.findBycodeProduitByidMS", query = "SELECT s FROM StockproduitMS s  WHERE s.categorie.magasinSecondaire.idMagasin = :idMagasin AND s.codeProduit = :codeProduit"),
    @NamedQuery(name = "StockproduitMS.findProduitCritique", query = "SELECT s FROM StockproduitMS s  WHERE s.categorie.magasinSecondaire.idMagasin = :idMagasin AND s.quantite <= :critique"),
    @NamedQuery(name = "StockproduitMS.findProduitWarmin", query = "SELECT s FROM StockproduitMS s  WHERE s.categorie.magasinSecondaire.idMagasin = :idMagasin AND s.quantite > :critique AND s.quantite <= :warmin"),
    @NamedQuery(name = "StockproduitMS.findByDesignationByidMS", query = "SELECT DISTINCT s FROM StockproduitMS s  WHERE  s.categorie.magasinSecondaire.idMagasin = :idMagasin AND s.designation = :designation"),
    @NamedQuery(name = "StockproduitMS.findByCodeProduit", query = "SELECT s FROM StockproduitMS s WHERE s.codeProduit = :codeProduit"),
    @NamedQuery(name = "StockproduitMS.findByDesignation", query = "SELECT s FROM StockproduitMS s WHERE s.designation = :designation"),
    @NamedQuery(name = "StockproduitMS.findByQuantite", query = "SELECT s FROM StockproduitMS s WHERE s.quantite = :quantite"),
    @NamedQuery(name = "StockproduitMS.findByPrixUnitaire", query = "SELECT s FROM StockproduitMS s WHERE s.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "StockproduitMS.findByPrixTotal", query = "SELECT s FROM StockproduitMS s WHERE s.prixTotal = :prixTotal"),
    @NamedQuery(name = "StockproduitMS.findByDateLivraison", query = "SELECT s FROM StockproduitMS s WHERE s.dateLivraison = :dateLivraison")})
public class StockproduitMS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_stock")
    private Integer idStock;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code_produit")
    private String codeProduit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "designation")
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private int quantite;
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
    @Column(name = "date_livraison")
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    @JoinColumn(name = "categorie", referencedColumnName = "id_categorie")
    @ManyToOne(optional = false)
    private CategorieproduitMS categorie;

    public StockproduitMS() {
    }

    public StockproduitMS(Integer idStock) {
        this.idStock = idStock;
    }

    public StockproduitMS(Integer idStock, String codeProduit, String designation, int quantite, double prixUnitaire, double prixTotal, Date dateLivraison) {
        this.idStock = idStock;
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixTotal = prixTotal;
        this.dateLivraison = dateLivraison;
    }

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public CategorieproduitMS getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieproduitMS categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStock != null ? idStock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockproduitMS)) {
            return false;
        }
        StockproduitMS other = (StockproduitMS) object;
        if ((this.idStock == null && other.idStock != null) || (this.idStock != null && !this.idStock.equals(other.idStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StockproduitMS[ idStock=" + idStock + " ]";
    }

}
