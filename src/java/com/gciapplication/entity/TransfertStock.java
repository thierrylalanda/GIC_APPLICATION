/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "transfert_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransfertStock.findAll", query = "SELECT t FROM TransfertStock t"),
    @NamedQuery(name = "TransfertStock.findAllByIdMP", query = "SELECT t FROM TransfertStock t WHERE t.mp.idMagasin = :idMP"),
    @NamedQuery(name = "TransfertStock.findAllDinstinctByIdNameMS", query = "SELECT DISTINCT t.ms.nomMagasin FROM TransfertStock t WHERE t.mp.idMagasin = :idMP"),
    @NamedQuery(name = "TransfertStock.findAllByNameMs", query = "SELECT  t FROM TransfertStock t WHERE t.ms.nomMagasin = :name"),
    @NamedQuery(name = "TransfertStock.findByIdTransfert", query = "SELECT t FROM TransfertStock t WHERE t.idTransfert = :idTransfert"),
    @NamedQuery(name = "TransfertStock.findByTypeProduit", query = "SELECT t FROM TransfertStock t WHERE t.typeProduit = :typeProduit"),
    @NamedQuery(name = "TransfertStock.findByCodeProduit", query = "SELECT t FROM TransfertStock t WHERE t.codeProduit = :codeProduit"),
    @NamedQuery(name = "TransfertStock.findByDesignationMP", query = "SELECT t FROM TransfertStock t WHERE t.designation = :designation AND t.mp.idMagasin = :idMP"),
    @NamedQuery(name = "TransfertStock.findByDesignation", query = "SELECT t FROM TransfertStock t WHERE t.designation = :designation AND t.ms.idMagasin = :idMS"),
    @NamedQuery(name = "TransfertStock.findByEtatAndIdMP", query = "SELECT t FROM TransfertStock t WHERE t.etat = :etat AND t.mp.idMagasin = :idMP"),
    @NamedQuery(name = "TransfertStock.findByPrixUnitaire", query = "SELECT t FROM TransfertStock t WHERE t.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "TransfertStock.findByPrixTotal", query = "SELECT t FROM TransfertStock t WHERE t.prixTotal = :prixTotal"),
    @NamedQuery(name = "TransfertStock.findByDate", query = "SELECT t FROM TransfertStock t WHERE t.date = :date"),
    @NamedQuery(name = "TransfertStock.findByVisaMS", query = "SELECT t FROM TransfertStock t WHERE t.visaMS = :visaMS"),
    @NamedQuery(name = "TransfertStock.findByPeriodeMP", query = "SELECT t FROM TransfertStock t WHERE t.date BETWEEN :date1 AND :date2 AND t.mp.idMagasin = :idMP AND t.designation = :designation ORDER BY t.date DESC"),
    @NamedQuery(name = "TransfertStock.findByPeriodeMS", query = "SELECT t FROM TransfertStock t WHERE t.date BETWEEN :date1 AND :date2 AND t.ms.idMagasin = :idMS AND t.designation = :designation ORDER BY t.date DESC"),
    @NamedQuery(name = "TransfertStock.findByPeriode", query = "SELECT t FROM TransfertStock t WHERE t.date BETWEEN :date1 AND :date2 AND t.mp.idMagasin = :idMP ORDER BY t.date,t.typeProduit DESC"),
    @NamedQuery(name = "TransfertStock.findByVisaMSByidMS", query = "SELECT t FROM TransfertStock t WHERE t.visaMS = :visaMS AND t.ms.idMagasin = :idMagasin"),
    @NamedQuery(name = "TransfertStock.findByCategorieAndMPAndDate", query = "SELECT t FROM TransfertStock t WHERE t.date  >= :datedebut AND t.date <= :datefin AND t.mp.idMagasin = :idmagasinp AND t.typeProduit = :categorie AND t.etat = 'ok' "),
    @NamedQuery(name = "TransfertStock.findByVisaMP", query = "SELECT t FROM TransfertStock t WHERE t.visaMS = :visaMS AND t.mp.idMagasin = :idMagasin")})
public class TransfertStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_transfert")
    private Integer idTransfert;
    @Size(max = 255)
    @Column(name = "type_produit")
    private String typeProduit;
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
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "visa_MS")
    private String visaMS;
    @Size(max = 255)
    @Column(name = "visa_MP")
    private String visaMP;
    @Size(max = 255)
    @Column(name = "Commentaire_MP")
    private String commentaireMP;
    @Size(max = 255)
    @Column(name = "Commentaire_MS")
    private String commentaireMS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "operateur")
    private String operateur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "etat")
    private String etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransfert")
    private List<MouvementProduits> mouvementProduitsList;
    @JoinColumn(name = "MS", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinSecondaire ms;
    @JoinColumn(name = "MP", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinPrincipal mp;

    public TransfertStock() {
    }

    public TransfertStock(Integer idTransfert) {
        this.idTransfert = idTransfert;
    }

    public TransfertStock(Integer idTransfert, String codeProduit, String designation, int quantite, double prixUnitaire, double prixTotal, Date date, String visaMS, String operateur, String etat) {
        this.idTransfert = idTransfert;
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixTotal = prixTotal;
        this.date = date;
        this.visaMS = visaMS;
        this.operateur = operateur;
        this.etat = etat;
    }

    public Integer getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(Integer idTransfert) {
        this.idTransfert = idTransfert;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVisaMS() {
        return visaMS;
    }

    public void setVisaMS(String visaMS) {
        this.visaMS = visaMS;
    }

    public String getVisaMP() {
        return visaMP;
    }

    public void setVisaMP(String visaMP) {
        this.visaMP = visaMP;
    }

    public String getCommentaireMP() {
        return commentaireMP;
    }

    public void setCommentaireMP(String commentaireMP) {
        this.commentaireMP = commentaireMP;
    }

    public String getCommentaireMS() {
        return commentaireMS;
    }

    public void setCommentaireMS(String commentaireMS) {
        this.commentaireMS = commentaireMS;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<MouvementProduits> getMouvementProduitsList() {
        return mouvementProduitsList;
    }

    public void setMouvementProduitsList(List<MouvementProduits> mouvementProduitsList) {
        this.mouvementProduitsList = mouvementProduitsList;
    }

    public MagasinSecondaire getMs() {
        return ms;
    }

    public void setMs(MagasinSecondaire ms) {
        this.ms = ms;
    }

    public MagasinPrincipal getMp() {
        return mp;
    }

    public void setMp(MagasinPrincipal mp) {
        this.mp = mp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransfert != null ? idTransfert.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransfertStock)) {
            return false;
        }
        TransfertStock other = (TransfertStock) object;
        if ((this.idTransfert == null && other.idTransfert != null) || (this.idTransfert != null && !this.idTransfert.equals(other.idTransfert))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.TransfertStock[ idTransfert=" + idTransfert + " ]";
    }
    
}
