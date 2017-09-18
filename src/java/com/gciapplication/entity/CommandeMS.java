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
@Table(name = "commande_MS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandeMS.findAll", query = "SELECT c FROM CommandeMS c"),
    @NamedQuery(name = "CommandeMS.findByIdCommande", query = "SELECT c FROM CommandeMS c WHERE c.idCommande = :idCommande"),
    @NamedQuery(name = "CommandeMS.findByTypeProduit", query = "SELECT c FROM CommandeMS c WHERE c.typeProduit = :typeProduit"),
    @NamedQuery(name = "CommandeMS.findByCodeProduitByEtat", query = "SELECT c FROM CommandeMS c WHERE c.codeProduit = :codeProduit AND c.idMS.idMagasin= :idMS AND c.etatCommande = :etatCommande"),
    @NamedQuery(name = "CommandeMS.findByCodeProduit", query = "SELECT c FROM CommandeMS c WHERE c.codeProduit = :codeProduit AND c.idMS.idMagasin = :idMS"),
    @NamedQuery(name = "CommandeMS.findByDesignation", query = "SELECT c FROM CommandeMS c WHERE c.designation = :designation"),
    @NamedQuery(name = "CommandeMS.findByQuantiteEnStock", query = "SELECT c FROM CommandeMS c WHERE c.quantiteEnStock = :quantiteEnStock"),
    @NamedQuery(name = "CommandeMS.findByQuantiteCommande", query = "SELECT c FROM CommandeMS c WHERE c.quantiteCommande = :quantiteCommande"),
    @NamedQuery(name = "CommandeMS.findByDerniereLivraison", query = "SELECT c FROM CommandeMS c WHERE c.derniereLivraison = :derniereLivraison"),
    @NamedQuery(name = "CommandeMS.findByDate", query = "SELECT c FROM CommandeMS c WHERE c.date = :date"),
    @NamedQuery(name = "CommandeMS.findByEtatCommandeForMP", query = "SELECT c FROM CommandeMS c WHERE c.etatCommande = :etatCommande AND c.idMP.idMagasin = :idMagasin"),
    @NamedQuery(name = "CommandeMS.findByEtatCommandeForMPDisting", query = "SELECT DISTINCT c.idMS FROM CommandeMS c WHERE c.etatCommande = :etatCommande OR c.etatCommande = 'incomplet' AND c.idMP.idMagasin = :idMagasin"),
    @NamedQuery(name = "CommandeMS.findByEtatCommande", query = "SELECT DISTINCT c FROM CommandeMS c WHERE c.etatCommande = :etatCommande  AND c.idMS.idMagasin = :idMagasin ")})
public class CommandeMS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_commande")
    private Integer idCommande;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
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
    @Column(name = "quantite_en_stock")
    private int quantiteEnStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite_commande")
    private int quantiteCommande;
    @Basic(optional = false)
    @NotNull
    @Column(name = "derniere_livraison")
    @Temporal(TemporalType.TIMESTAMP)
    private Date derniereLivraison;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Etat_Commande")
    private String etatCommande;
    @JoinColumn(name = "id_MS", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinSecondaire idMS;
    @JoinColumn(name = "id_MP", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinPrincipal idMP;

    public CommandeMS() {
    }

    public CommandeMS(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public CommandeMS(Integer idCommande, String typeProduit, String codeProduit, String designation, int quantiteEnStock, int quantiteCommande, Date derniereLivraison, Date date, String etatCommande) {
        this.idCommande = idCommande;
        this.typeProduit = typeProduit;
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.quantiteEnStock = quantiteEnStock;
        this.quantiteCommande = quantiteCommande;
        this.derniereLivraison = derniereLivraison;
        this.date = date;
        this.etatCommande = etatCommande;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
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

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public int getQuantiteCommande() {
        return quantiteCommande;
    }

    public void setQuantiteCommande(int quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    public Date getDerniereLivraison() {
        return derniereLivraison;
    }

    public void setDerniereLivraison(Date derniereLivraison) {
        this.derniereLivraison = derniereLivraison;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(String etatCommande) {
        this.etatCommande = etatCommande;
    }

    public MagasinSecondaire getIdMS() {
        return idMS;
    }

    public void setIdMS(MagasinSecondaire idMS) {
        this.idMS = idMS;
    }

    public MagasinPrincipal getIdMP() {
        return idMP;
    }

    public void setIdMP(MagasinPrincipal idMP) {
        this.idMP = idMP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCommande != null ? idCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandeMS)) {
            return false;
        }
        CommandeMS other = (CommandeMS) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.CommandeMS[ idCommande=" + idCommande + " ]";
    }
    
}
