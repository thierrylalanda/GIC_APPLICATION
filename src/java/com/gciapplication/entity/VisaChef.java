/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "visa_chef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisaChef.findAll", query = "SELECT v FROM VisaChef v"),
    @NamedQuery(name = "VisaChef.findByIdValidation", query = "SELECT v FROM VisaChef v WHERE v.idValidation = :idValidation"),
    @NamedQuery(name = "VisaChef.findByIdMP", query = "SELECT DISTINCT v FROM VisaChef v WHERE v.commande.idCommande = :id"),
    //@NamedQuery(name = "VisaChef.findNameByIdMP", query = "SELECT DISTINCT v.validation.idResponsable.nomPrenom FROM VisaChef v WHERE v.commande.idMP.idMagasin = :id"),
    @NamedQuery(name = "VisaChef.findByDecision", query = "SELECT v FROM VisaChef v WHERE v.decision = :decision"),
    @NamedQuery(name = "VisaChef.findByObservation", query = "SELECT v FROM VisaChef v WHERE v.observation = :observation")})
public class VisaChef implements Serializable {

    @JoinColumn(name = "controleur", referencedColumnName = "id_personnel", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Personnel controleur;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_validation")
    private Integer idValidation;
    @Size(max = 50)
    @Column(name = "decision")
    private String decision;
    @Size(max = 50)
    @Column(name = "observation")
    private String observation;
    @JoinColumn(name = "commande", referencedColumnName = "id_commande")
    @ManyToOne(optional = false)
    private CommandeMP commande;
    @JoinColumn(name = "validation", referencedColumnName = "id_responsable_validation")
    @ManyToOne(optional = false)
    private Responsablevalidation validation;

    public VisaChef() {
    }

    public VisaChef(Integer idValidation) {
        this.idValidation = idValidation;
    }

    public Integer getIdValidation() {
        return idValidation;
    }

    public void setIdValidation(Integer idValidation) {
        this.idValidation = idValidation;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public CommandeMP getCommande() {
        return commande;
    }

    public void setCommande(CommandeMP commande) {
        this.commande = commande;
    }

    public Responsablevalidation getValidation() {
        return validation;
    }

    public void setValidation(Responsablevalidation validation) {
        this.validation = validation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValidation != null ? idValidation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VisaChef)) {
            return false;
        }
        VisaChef other = (VisaChef) object;
        if ((this.idValidation == null && other.idValidation != null) || (this.idValidation != null && !this.idValidation.equals(other.idValidation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.VisaChef[ idValidation=" + idValidation + " ]";
    }

    public Personnel getControleur() {
        return controleur;
    }

    public void setControleur(Personnel controleur) {
        this.controleur = controleur;
    }
    
}
