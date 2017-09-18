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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "Ordre_Controleur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdreControleur.findAll", query = "SELECT o FROM OrdreControleur o WHERE o.idMP.idMagasin = :id ORDER BY o.niveau"),
    @NamedQuery(name = "OrdreControleur.Delete", query = "DELETE FROM OrdreControleur o WHERE o.idResponsable = :id "),
    @NamedQuery(name = "OrdreControleur.findByIdOrdre", query = "SELECT o FROM OrdreControleur o WHERE o.idOrdre = :idOrdre"),
    @NamedQuery(name = "OrdreControleur.findIDControleur", query = "SELECT  o FROM OrdreControleur o WHERE o.controleur = :id"),
    @NamedQuery(name = "OrdreControleur.findByNiveaux", query = "SELECT DISTINCT o.niveau FROM OrdreControleur o "),
    @NamedQuery(name = "OrdreControleur.findByNiveauAnIdResponsable", query = "SELECT DISTINCT o FROM OrdreControleur o WHERE o.niveau = :niveau AND o.idResponsable.idResponsableValidation = :controleur")})
public class OrdreControleur implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdre")
    private Integer idOrdre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "niveau")
    private int niveau;
    @Basic(optional = false)
    @NotNull
    @Column(name = "controleur")
    private int controleur;
    @Column(name = "etat")
    private Integer etat;
    @JoinColumn(name = "idMP", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinPrincipal idMP;
    @JoinColumn(name = "idResponsable", referencedColumnName = "id_responsable_validation")
    @ManyToOne(optional = false)
    private Responsablevalidation idResponsable;

    public OrdreControleur() {
    }

    public OrdreControleur(Integer idOrdre) {
        this.idOrdre = idOrdre;
    }

    public OrdreControleur(Integer idOrdre, int niveau) {
        this.idOrdre = idOrdre;
        this.niveau = niveau;
    }

    public Integer getIdOrdre() {
        return idOrdre;
    }

    public void setIdOrdre(Integer idOrdre) {
        this.idOrdre = idOrdre;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public MagasinPrincipal getIdMP() {
        return idMP;
    }

    public void setIdMP(MagasinPrincipal idMP) {
        this.idMP = idMP;
    }

    public Responsablevalidation getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Responsablevalidation idResponsable) {
        this.idResponsable = idResponsable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdre != null ? idOrdre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdreControleur)) {
            return false;
        }
        OrdreControleur other = (OrdreControleur) object;
        if ((this.idOrdre == null && other.idOrdre != null) || (this.idOrdre != null && !this.idOrdre.equals(other.idOrdre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.OrdreControleur[ idOrdre=" + idOrdre + " ]";
    }

    public int getControleur() {
        return controleur;
    }

    public void setControleur(int controleur) {
        this.controleur = controleur;
    }
    
}
