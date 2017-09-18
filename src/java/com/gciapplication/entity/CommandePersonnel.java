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
@Table(name = "commande_personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommandePersonnel.findAll", query = "SELECT c FROM CommandePersonnel c"),
    @NamedQuery(name = "CommandePersonnel.findByReception", query = "SELECT c FROM CommandePersonnel c WHERE c.reception = :reception AND c.idPersonnel.idPersonnel = :personnel"),
    @NamedQuery(name = "CommandePersonnel.findByIdCommande", query = "SELECT c FROM CommandePersonnel c WHERE c.idCommande = :idCommande"),
    @NamedQuery(name = "CommandePersonnel.findByDesignations", query = "SELECT c FROM CommandePersonnel c WHERE c.designations = :designations"),
    @NamedQuery(name = "CommandePersonnel.findByQuantite", query = "SELECT c FROM CommandePersonnel c WHERE c.quantite = :quantite"),
    @NamedQuery(name = "CommandePersonnel.findByDate", query = "SELECT c FROM CommandePersonnel c WHERE c.date = :date"),
    @NamedQuery(name = "CommandePersonnel.findByEtatCommandeByidpersonnel", query = "SELECT c FROM CommandePersonnel c WHERE c.etatCommande = :etatCommande AND c.idPersonnel.idPersonnel = :idpersonnel"),
    @NamedQuery(name = "CommandePersonnel.findByEtatCommande", query = "SELECT c FROM CommandePersonnel c WHERE c.etatCommande = :etatCommande AND c.idPersonnel.idPersonnel = :idpersonnel AND c.idMS.idMagasin = :idMagasin"),
    @NamedQuery(name = "CommandePersonnel.findByEtatCommandeReturnPersonel", query = "SELECT DISTINCT c.idPersonnel FROM CommandePersonnel c WHERE c.etatCommande = :etatCommande AND c.idMS.idMagasin = :idMagasin"),
    @NamedQuery(name = "CommandePersonnel.findByEtatCommandeMS", query = "SELECT c FROM CommandePersonnel c WHERE c.etatCommande = :etatCommande AND c.idMS.idMagasin = :idMagasin")})
public class CommandePersonnel implements Serializable {

    

    @JoinColumn(name = "id_MP", referencedColumnName = "id_magasin")
    @ManyToOne
    private MagasinPrincipal idMP;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_commande")
    private Integer idCommande;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "designations")
    private String designations;
    @Column(name = "quantite")
    private Integer quantite;
    @Size(max = 50)
    @Column(name = "reception")
    private String reception;
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
    @ManyToOne
    private MagasinSecondaire idMS;
    @JoinColumn(name = "id_personnel", referencedColumnName = "id_personnel")
    @ManyToOne(optional = false)
    private Personnel idPersonnel;
    @JoinColumn(name = "code_appareil", referencedColumnName = "numero_parck")
    @ManyToOne
    private Appariel codeAppareil;

    public CommandePersonnel() {
    }

    public CommandePersonnel(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public CommandePersonnel(Integer idCommande, String designations, int quantite, Date date, String etatCommande) {
        this.idCommande = idCommande;
        this.designations = designations;
        this.quantite = quantite;
        this.date = date;
        this.etatCommande = etatCommande;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public String getDesignations() {
        return designations;
    }

    public void setDesignations(String designations) {
        this.designations = designations;
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

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public Appariel getCodeAppareil() {
        return codeAppareil;
    }

    public void setCodeAppareil(Appariel codeAppareil) {
        this.codeAppareil = codeAppareil;
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
        if (!(object instanceof CommandePersonnel)) {
            return false;
        }
        CommandePersonnel other = (CommandePersonnel) object;
        if ((this.idCommande == null && other.idCommande != null) || (this.idCommande != null && !this.idCommande.equals(other.idCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.CommandePersonnel[ idCommande=" + idCommande + " ]";
    }

    public MagasinPrincipal getIdMP() {
        return idMP;
    }

    public void setIdMP(MagasinPrincipal idMP) {
        this.idMP = idMP;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getReception() {
        return reception;
    }

    public void setReception(String reception) {
        this.reception = reception;
    }
    
}
