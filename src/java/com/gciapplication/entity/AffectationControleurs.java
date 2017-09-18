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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "affectation_controleurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AffectationControleurs.findaffectationByIDpersonnel", query = "SELECT a FROM AffectationControleurs a WHERE a.personnel.idPersonnel = :id"),
    @NamedQuery(name = "AffectationControleurs.findAll", query = "SELECT a FROM AffectationControleurs a"),
    @NamedQuery(name = "AffectationControleurs.findLastInsert", query = "SELECT a FROM AffectationControleurs a WHERE a.idAffectation = SELECT MAX(a.idAffectation) FROM AffectationControleurs a"),
    @NamedQuery(name = "AffectationControleurs.findByidResponsable", query = "SELECT a FROM AffectationControleurs a WHERE a.responsableValidation.idResponsableValidation = :idrespo"),
    @NamedQuery(name = "AffectationControleurs.findByIdAffectation", query = "SELECT a FROM AffectationControleurs a WHERE a.idAffectation = :idAffectation")})
public class AffectationControleurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_affectation")
    private Integer idAffectation;
    @JoinColumn(name = "personnel", referencedColumnName = "id_personnel")
    @ManyToOne(optional = false)
    private Personnel personnel;
    @JoinColumn(name = "responsable_validation", referencedColumnName = "id_responsable_validation")
    @ManyToOne(optional = false)
    private Responsablevalidation responsableValidation;

    public AffectationControleurs() {
    }

    public AffectationControleurs(Integer idAffectation) {
        this.idAffectation = idAffectation;
    }

    public Integer getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Integer idAffectation) {
        this.idAffectation = idAffectation;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Responsablevalidation getResponsableValidation() {
        return responsableValidation;
    }

    public void setResponsableValidation(Responsablevalidation responsableValidation) {
        this.responsableValidation = responsableValidation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAffectation != null ? idAffectation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AffectationControleurs)) {
            return false;
        }
        AffectationControleurs other = (AffectationControleurs) object;
        if ((this.idAffectation == null && other.idAffectation != null) || (this.idAffectation != null && !this.idAffectation.equals(other.idAffectation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.AffectationControleurs[ idAffectation=" + idAffectation + " ]";
    }
    
}
