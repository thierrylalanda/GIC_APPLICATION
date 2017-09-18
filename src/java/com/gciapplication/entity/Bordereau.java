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
@Table(name = "bordereau")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bordereau.findAll", query = "SELECT b FROM Bordereau b"),
    @NamedQuery(name = "Bordereau.findByIdBordereau", query = "SELECT b FROM Bordereau b WHERE b.idBordereau = :idBordereau"),
    @NamedQuery(name = "Bordereau.findAllByIdMP", query = "SELECT b FROM Bordereau b WHERE b.magasin.idMagasin = :idMP"),
    @NamedQuery(name = "Bordereau.findByTypeProduit", query = "SELECT b FROM Bordereau b WHERE b.typeProduit = :typeProduit"),
    @NamedQuery(name = "Bordereau.findByCodeProduit", query = "SELECT b FROM Bordereau b WHERE b.codeProduit = :codeProduit"),
    @NamedQuery(name = "Bordereau.findByDesignation", query = "SELECT b FROM Bordereau b WHERE b.designation = :designation"),
    @NamedQuery(name = "Bordereau.findByQuantite", query = "SELECT b FROM Bordereau b WHERE b.quantite = :quantite"),
    @NamedQuery(name = "Bordereau.findByPrixUnitaire", query = "SELECT b FROM Bordereau b WHERE b.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "Bordereau.findByPrixTotal", query = "SELECT b FROM Bordereau b WHERE b.prixTotal = :prixTotal"),
    @NamedQuery(name = "Bordereau.findByDesignationMP", query = "SELECT t FROM Bordereau t WHERE t.designation = :designation AND t.magasin.idMagasin = :idMP"),
    @NamedQuery(name = "Bordereau.findByPeriodeMP", query = "SELECT t FROM Bordereau t WHERE t.date >= :date1 AND t.date <= :date2 AND t.magasin.idMagasin = :idMP AND t.designation = :designation ORDER BY t.date DESC"),
    @NamedQuery(name = "Bordereau.findByDate", query = "SELECT b FROM Bordereau b WHERE b.date = :date"),
    @NamedQuery(name = "Bordereau.findByVisaMagasigner", query = "SELECT b FROM Bordereau b WHERE b.visaMagasigner = :visaMagasigner")})
public class Bordereau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bordereau")
    private Integer idBordereau;
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
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 10)
    @Column(name = "visa_magasigner")
    private String visaMagasigner;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite_init")
    private int quantiteInit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite_apres")
    private int quantiteApres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "recepteur")
    private String recepteur;
    @JoinColumn(name = "id_founisseur", referencedColumnName = "id_founisseur")
    @ManyToOne(optional = false)
    private Founisseur idFounisseur;
    @JoinColumn(name = "magasin", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinPrincipal magasin;

    public Bordereau() {
    }

    public Bordereau(Integer idBordereau) {
        this.idBordereau = idBordereau;
    }

    public Bordereau(Integer idBordereau, String typeProduit, String codeProduit, String designation, int quantite, double prixUnitaire, double prixTotal, Date date, int quantiteInit, int quantiteApres, String recepteur) {
        this.idBordereau = idBordereau;
        this.typeProduit = typeProduit;
        this.codeProduit = codeProduit;
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixTotal = prixTotal;
        this.date = date;
        this.quantiteInit = quantiteInit;
        this.quantiteApres = quantiteApres;
        this.recepteur = recepteur;
    }

    public Integer getIdBordereau() {
        return idBordereau;
    }

    public void setIdBordereau(Integer idBordereau) {
        this.idBordereau = idBordereau;
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

    public String getVisaMagasigner() {
        return visaMagasigner;
    }

    public void setVisaMagasigner(String visaMagasigner) {
        this.visaMagasigner = visaMagasigner;
    }

    public int getQuantiteInit() {
        return quantiteInit;
    }

    public void setQuantiteInit(int quantiteInit) {
        this.quantiteInit = quantiteInit;
    }

    public int getQuantiteApres() {
        return quantiteApres;
    }

    public void setQuantiteApres(int quantiteApres) {
        this.quantiteApres = quantiteApres;
    }

    public String getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(String recepteur) {
        this.recepteur = recepteur;
    }

    public Founisseur getIdFounisseur() {
        return idFounisseur;
    }

    public void setIdFounisseur(Founisseur idFounisseur) {
        this.idFounisseur = idFounisseur;
    }

    public MagasinPrincipal getMagasin() {
        return magasin;
    }

    public void setMagasin(MagasinPrincipal magasin) {
        this.magasin = magasin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBordereau != null ? idBordereau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bordereau)) {
            return false;
        }
        Bordereau other = (Bordereau) object;
        if ((this.idBordereau == null && other.idBordereau != null) || (this.idBordereau != null && !this.idBordereau.equals(other.idBordereau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Bordereau[ idBordereau=" + idBordereau + " ]";
    }
    
}
