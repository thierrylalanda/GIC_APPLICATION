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
@Table(name = "commande_MP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandeMP.findAll", query = "SELECT c FROM CommandeMP c"),
    @NamedQuery(name = "CommandeMP.findByIdCommande", query = "SELECT c FROM CommandeMP c WHERE c.idCommande = :idCommande"),
    @NamedQuery(name = "CommandeMP.findByIdFournisseurAndidMP", query = "SELECT c FROM CommandeMP c WHERE c.idSA.idFounisseur = :fournisseur AND c.idMP.idMagasin = :magasin AND c.livraison = :livrer AND c.etat = :etat"),
    @NamedQuery(name = "CommandeMP.findByLivraisonAndidMP", query = "SELECT c FROM CommandeMP c WHERE c.idMP.idMagasin = :magasin AND c.livraison = :livrer"),
    @NamedQuery(name = "CommandeMP.findByRecenDate", query = "SELECT c FROM CommandeMP c WHERE c.idMP.idMagasin = :idMagasin AND c.date = ( SELECT MAX(c.date) FROM CommandeMP c)AND c.etat='OK' OR c.etat ='rejeter' "),
    @NamedQuery(name = "CommandeMP.findByIdMPAndEtat", query = "SELECT c FROM CommandeMP c WHERE c.idMP.idMagasin = :idMagasin AND c.etat = :etat "),
    @NamedQuery(name = "CommandeMP.findByIdMPAndEtatIndice", query = "SELECT c FROM CommandeMP c WHERE c.idMP.idMagasin = :idMagasin AND c.etat = :etat AND c.indice = :indice"),
    @NamedQuery(name = "CommandeMP.findByIdMPAndEtatOrOk", query = "SELECT c FROM CommandeMP c WHERE c.idMP.idMagasin = :idMagasin AND c.etat = :etat OR c.etat = :ok "),
    @NamedQuery(name = "CommandeMP.findByTypeProduit", query = "SELECT c FROM CommandeMP c WHERE c.typeProduit = :typeProduit"),
    @NamedQuery(name = "CommandeMP.findByCodeProduit", query = "SELECT c FROM CommandeMP c WHERE c.codeProduit = :codeProduit"),
    @NamedQuery(name = "CommandeMP.findByDesignation", query = "SELECT c FROM CommandeMP c WHERE c.designation = :designation"),
    @NamedQuery(name = "CommandeMP.findByQuantiteEnStock", query = "SELECT c FROM CommandeMP c WHERE c.quantiteEnStock = :quantiteEnStock"),
    @NamedQuery(name = "CommandeMP.findByQuantiteCommande", query = "SELECT c FROM CommandeMP c WHERE c.quantiteCommande = :quantiteCommande"),
    @NamedQuery(name = "CommandeMP.findByDerniereLivraison", query = "SELECT c FROM CommandeMP c WHERE c.derniereLivraison = :derniereLivraison"),
    @NamedQuery(name = "CommandeMP.findByDate", query = "SELECT c FROM CommandeMP c WHERE c.date = :date")})
public class CommandeMP implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_commande")
    private Integer idCommande;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "livraison")
    private String livraison;
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
    @Column(name = "derniere_livraison")
    @Temporal(TemporalType.DATE)
    private Date derniereLivraison;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Etat")
    private String etat;
    @Column(name = "indice")
    private Integer indice;
    @Column(name = "quantite_commande")
    private Integer quantiteCommande;
    @Column(name = "prixUnitaire")
    private Double prixUnitaire;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prixTotal")
    private Double prixTotal;
    @Column(name = "quantite_en_stock")
    private Integer quantiteEnStock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    private List<VisaChef> visaChefList;
    @JoinColumn(name = "id_SA", referencedColumnName = "id_founisseur")
    @ManyToOne(optional = false)
    private Founisseur idSA;
    @JoinColumn(name = "id_MP", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinPrincipal idMP;

    public CommandeMP() {
    }

    public CommandeMP(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public CommandeMP(Integer idCommande, String typeProduit, String codeProduit, String designation, int quantiteEnStock, int quantiteCommande, Date derniereLivraison, Date date, String etat) {
        this.idCommande = idCommande;
        this.typeProduit = typeProduit;
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.quantiteEnStock = quantiteEnStock;
        this.quantiteCommande = quantiteCommande;
        this.derniereLivraison = derniereLivraison;
        this.date = date;
        this.etat = etat;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<VisaChef> getVisaChefList() {
        return visaChefList;
    }

    public void setVisaChefList(List<VisaChef> visaChefList) {
        this.visaChefList = visaChefList;
    }

    public Founisseur getIdSA() {
        return idSA;
    }

    public void setIdSA(Founisseur idSA) {
        this.idSA = idSA;
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
        if (!(object instanceof CommandeMP)) {
            return false;
        }
        CommandeMP other = (CommandeMP) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.CommandeMP[ idCommande=" + idCommande + " ]";
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public void setQuantiteCommande(Integer quantiteCommande) {
        this.quantiteCommande = quantiteCommande;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }

   
    
}
