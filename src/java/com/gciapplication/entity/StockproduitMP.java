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
@Table(name = "stock_produit_MP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockproduitMP.findAll", query = "SELECT s FROM StockproduitMP s"),
    @NamedQuery(name = "StockproduitMP.findByLastInsert", query = "SELECT s FROM StockproduitMP s WHERE s.idStock = SELECT MAX(s.idStock) FROM StockproduitMP s"),
    @NamedQuery(name = "StockproduitMP.findStockByIdCategorie", query = "SELECT s FROM StockproduitMP s WHERE s.categorie.idCategorie = :categorie"),
    @NamedQuery(name = "StockproduitMP.findProduitCritique", query = "SELECT s FROM StockproduitMP s  WHERE s.categorie.magasinPrincipal.idMagasin = :idMagasin AND s.quantite <= :critique"),
    @NamedQuery(name = "StockproduitMP.findProduitWarmin", query = "SELECT s FROM StockproduitMP s  WHERE s.categorie.magasinPrincipal.idMagasin = :idMagasin AND s.quantite > :critique AND s.quantite <= :warmin"),
    @NamedQuery(name = "StockproduitMP.findBycodeProduitByidMP", query = "SELECT DISTINCT s FROM StockproduitMP s WHERE s.codeProduit = :codeProduit AND s.categorie.magasinPrincipal.idMagasin = :idMP"),
    @NamedQuery(name = "StockproduitMP.findByCodeProduit", query = "SELECT s FROM StockproduitMP s WHERE s.codeProduit = :codeProduit "),
    @NamedQuery(name = "StockproduitMP.findByDesignation", query = "SELECT s FROM StockproduitMP s WHERE s.designation = :designation AND s.categorie.magasinPrincipal.idMagasin = :id"),
    @NamedQuery(name = "StockproduitMP.findByidMP", query = "SELECT s FROM StockproduitMP s WHERE s.categorie.magasinPrincipal.idMagasin = :idMagasin"),
    @NamedQuery(name = "StockproduitMP.findByQuantite", query = "SELECT s FROM StockproduitMP s WHERE s.quantite = :quantite"),
    @NamedQuery(name = "StockproduitMP.findByPrixUnitaire", query = "SELECT s FROM StockproduitMP s WHERE s.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "StockproduitMP.findByPrixTotal", query = "SELECT s FROM StockproduitMP s WHERE s.prixTotal = :prixTotal"),
    @NamedQuery(name = "StockproduitMP.findByDateLivraison", query = "SELECT s FROM StockproduitMP s WHERE s.dateLivraison = :dateLivraison")})
public class StockproduitMP implements Serializable {

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
    private CategorieproduitMP categorie;

    public StockproduitMP() {
    }

    public StockproduitMP(Integer idStock) {
        this.idStock = idStock;
    }

    public StockproduitMP(Integer idStock, String codeProduit, String designation, int quantite, double prixUnitaire, double prixTotal, Date dateLivraison) {
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

    public CategorieproduitMP getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieproduitMP categorie) {
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
        if (!(object instanceof StockproduitMP)) {
            return false;
        }
        StockproduitMP other = (StockproduitMP) object;
        if ((this.idStock == null && other.idStock != null) || (this.idStock != null && !this.idStock.equals(other.idStock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.StockproduitMP[ idStock=" + idStock + " ]";
    }
    
}
