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
@Table(name = "affectation_magasinP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AffectationmagasinP.findAll", query = "SELECT a FROM AffectationmagasinP a"),
    @NamedQuery(name = "AffectationmagasinP.findAllByIdMP", query = "SELECT a FROM AffectationmagasinP a WHERE a.magasinP.idMagasin = :idmagasin"),
    @NamedQuery(name = "AffectationmagasinP.findaffectationByIDpersonnel", query = "SELECT a FROM AffectationmagasinP a WHERE a.personnel.idPersonnel = :id"),
    @NamedQuery(name = "AffectationmagasinP.findByIdAffectation", query = "SELECT a FROM AffectationmagasinP a WHERE a.idAffectation = :idAffectation")})
public class AffectationmagasinP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_affectation")
    private Integer idAffectation;
    @JoinColumn(name = "personnel", referencedColumnName = "id_personnel")
    @ManyToOne(optional = false)
    private Personnel personnel;
    @JoinColumn(name = "magasinP", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinPrincipal magasinP;

    public AffectationmagasinP() {
    }

    public AffectationmagasinP(Integer idAffectation) {
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

    public MagasinPrincipal getMagasinP() {
        return magasinP;
    }

    public void setMagasinP(MagasinPrincipal magasinP) {
        this.magasinP = magasinP;
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
        if (!(object instanceof AffectationmagasinP)) {
            return false;
        }
        AffectationmagasinP other = (AffectationmagasinP) object;
        if ((this.idAffectation == null && other.idAffectation != null) || (this.idAffectation != null && !this.idAffectation.equals(other.idAffectation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.AffectationmagasinP[ idAffectation=" + idAffectation + " ]";
    }
    
}
