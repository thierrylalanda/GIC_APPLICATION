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
@Table(name = "operation_consommateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperationConsommateur.findAll", query = "SELECT o FROM OperationConsommateur o"),
    @NamedQuery(name = "OperationConsommateur.findByIdOperation", query = "SELECT o FROM OperationConsommateur o WHERE o.idOperation = :idOperation"),
    @NamedQuery(name = "OperationConsommateur.findByIdPersonnel", query = "SELECT o FROM OperationConsommateur o WHERE o.personnel.idPersonnel = :personnel"),
    @NamedQuery(name = "OperationConsommateur.findByCategorie", query = "SELECT o FROM OperationConsommateur o WHERE o.categorie = :categorie"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAnNamePersonnel", query = "SELECT o FROM OperationConsommateur o WHERE o.categorie = :categorie AND o.personnel.idPersonnel = :nom"),
    @NamedQuery(name = "OperationConsommateur.findByCodeProduit", query = "SELECT o FROM OperationConsommateur o WHERE o.codeProduit = :codeProduit"),
    @NamedQuery(name = "OperationConsommateur.findByDesignation", query = "SELECT o FROM OperationConsommateur o WHERE o.designation = :designation AND o.magasin.idMagasin = :idMS ORDER BY o.date DESC"),
    @NamedQuery(name = "OperationConsommateur.findByAppariel", query = "SELECT o FROM OperationConsommateur o WHERE o.appariel = :appariel"),
    @NamedQuery(name = "OperationConsommateur.findByQuantite", query = "SELECT o FROM OperationConsommateur o WHERE o.quantite = :quantite"),
    @NamedQuery(name = "OperationConsommateur.findByPrix", query = "SELECT o FROM OperationConsommateur o WHERE o.prix = :prix"),
    @NamedQuery(name = "OperationConsommateur.findByPrixTotal", query = "SELECT o FROM OperationConsommateur o WHERE o.prixTotal = :prixTotal"),
    @NamedQuery(name = "OperationConsommateur.findByIdMS", query = "SELECT o FROM OperationConsommateur o WHERE  o.magasin.idMagasin = :idMS ORDER BY o.date DESC"),
    @NamedQuery(name = "OperationConsommateur.findByIdMP", query = "SELECT o FROM OperationConsommateur o WHERE  o.magasinP.idMagasin = :idMP ORDER BY o.date DESC"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAndSite", query = "SELECT DISTINCT o.categorie FROM OperationConsommateur o WHERE  o.magasin.site.idSite = :idsite"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAndRegionAndDate", query = "SELECT o FROM OperationConsommateur o WHERE o.date  >= :datedebut AND o.date <= :datefin AND o.magasin.site.region.idRegion = :idregion AND o.categorie = :categorie"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAndMSAndDate", query = "SELECT o FROM OperationConsommateur o WHERE o.date  >= :datedebut AND o.date <= :datefin AND o.magasin.idMagasin = :idmagasin AND o.categorie = :categorie"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAndPersonnelAndDate", query = "SELECT o FROM OperationConsommateur o WHERE o.date  >= :datedebut AND o.date <= :datefin AND o.personnel.idPersonnel = :idpersonnel AND o.categorie = :categorie"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAndServiceAndDate", query = "SELECT o FROM OperationConsommateur o WHERE o.date  >= :datedebut AND o.date <= :datefin AND o.personnel.service.idService = :idservice AND o.categorie = :categorie"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAndSiteAndDate", query = "SELECT o FROM OperationConsommateur o WHERE o.date  >= :datedebut AND o.date <= :datefin AND o.magasin.site.idSite = :idsite AND o.categorie = :categorie"),
    @NamedQuery(name = "OperationConsommateur.findByCategorieAndDirectionAndDate", query = "SELECT o FROM OperationConsommateur o WHERE o.date  >= :datedebut AND o.date <= :datefin AND o.personnel.service.direction.idDirection = :iddirection AND o.categorie = :categorie"),
    @NamedQuery(name = "OperationConsommateur.findByDateByDesignation", query = "SELECT o FROM OperationConsommateur o WHERE o.date >= :date1 AND o.date <= :date2 AND o.magasin.idMagasin = :idMS AND o.designation = :designation ORDER BY o.date DESC"),
    @NamedQuery(name = "OperationConsommateur.findByDate", query = "SELECT o FROM OperationConsommateur o WHERE o.date  >= :date1 AND o.date <= :date2 AND o.magasin.idMagasin = :idMS ORDER BY o.date DESC")})
public class OperationConsommateur implements Serializable {

    @JoinColumn(name = "MagasinP", referencedColumnName = "id_magasin")
    @ManyToOne
    private MagasinPrincipal magasinP;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_operation")
    private Integer idOperation;
    @Size(max = 255)
    @Column(name = "categorie")
    private String categorie;
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
    @Size(max = 255)
    @Column(name = "appariel")
    private String appariel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private int quantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix")
    private double prix;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_total")
    private double prixTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Column(name = "stock_apres")
    private Integer stockApres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Operateur")
    private String operateur;
    @JoinColumn(name = "personnel", referencedColumnName = "id_personnel")
    @ManyToOne(optional = false)
    private Personnel personnel;
    @JoinColumn(name = "magasin", referencedColumnName = "id_magasin")
    @ManyToOne
    private MagasinSecondaire magasin;

    public OperationConsommateur() {
    }

    public OperationConsommateur(Integer idOperation) {
        this.idOperation = idOperation;
    }

    public OperationConsommateur(Integer idOperation, String codeProduit, String designation, int quantite, double prix, double prixTotal, Date date, int stock, String operateur) {
        this.idOperation = idOperation;
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.quantite = quantite;
        this.prix = prix;
        this.prixTotal = prixTotal;
        this.date = date;
        this.stock = stock;
        this.operateur = operateur;
    }

    public Integer getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Integer idOperation) {
        this.idOperation = idOperation;
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

    public String getAppariel() {
        return appariel;
    }

    public void setAppariel(String appariel) {
        this.appariel = appariel;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer getStockApres() {
        return stockApres;
    }

    public void setStockApres(Integer stockApres) {
        this.stockApres = stockApres;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public MagasinSecondaire getMagasin() {
        return magasin;
    }

    public void setMagasin(MagasinSecondaire magasin) {
        this.magasin = magasin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperation != null ? idOperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperationConsommateur)) {
            return false;
        }
        OperationConsommateur other = (OperationConsommateur) object;
        if ((this.idOperation == null && other.idOperation != null) || (this.idOperation != null && !this.idOperation.equals(other.idOperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.OperationConsommateur[ idOperation=" + idOperation + " ]";
    }

    public MagasinPrincipal getMagasinP() {
        return magasinP;
    }

    public void setMagasinP(MagasinPrincipal magasinP) {
        this.magasinP = magasinP;
    }
    
}
