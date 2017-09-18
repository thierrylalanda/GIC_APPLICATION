/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "Responsable_validation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsablevalidation.findAll", query = "SELECT r FROM Responsablevalidation r"),
    @NamedQuery(name = "Responsablevalidation.findByIdResponsableValidation", query = "SELECT r FROM Responsablevalidation r WHERE r.idResponsableValidation = :idResponsableValidation"),
    @NamedQuery(name = "Responsablevalidation.findByDescription", query = "SELECT r FROM Responsablevalidation r WHERE r.description = :description"),
    @NamedQuery(name = "Responsablevalidation.findByNiveau", query = "SELECT r FROM Responsablevalidation r WHERE r.niveau = :niveau")})
public class Responsablevalidation implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_responsable_validation")
    private Integer idResponsableValidation;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Niveau")
    private int niveau;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "validation")
    private List<VisaChef> visaChefList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsableValidation")
    private List<AffectationControleurs> affectationControleursList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idResponsable")
    private List<OrdreControleur> ordreControleurList;

    public Responsablevalidation() {
    }

    public Responsablevalidation(Integer idResponsableValidation) {
        this.idResponsableValidation = idResponsableValidation;
    }

    public Responsablevalidation(Integer idResponsableValidation, int niveau) {
        this.idResponsableValidation = idResponsableValidation;
        this.niveau = niveau;
    }

    public Integer getIdResponsableValidation() {
        return idResponsableValidation;
    }

    public void setIdResponsableValidation(Integer idResponsableValidation) {
        this.idResponsableValidation = idResponsableValidation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @XmlTransient
    public List<VisaChef> getVisaChefList() {
        return visaChefList;
    }

    public void setVisaChefList(List<VisaChef> visaChefList) {
        this.visaChefList = visaChefList;
    }

    @XmlTransient
    public List<AffectationControleurs> getAffectationControleursList() {
        return affectationControleursList;
    }

    public void setAffectationControleursList(List<AffectationControleurs> affectationControleursList) {
        this.affectationControleursList = affectationControleursList;
    }

    @XmlTransient
    public List<OrdreControleur> getOrdreControleurList() {
        return ordreControleurList;
    }

    public void setOrdreControleurList(List<OrdreControleur> ordreControleurList) {
        this.ordreControleurList = ordreControleurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResponsableValidation != null ? idResponsableValidation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsablevalidation)) {
            return false;
        }
        Responsablevalidation other = (Responsablevalidation) object;
        if ((this.idResponsableValidation == null && other.idResponsableValidation != null) || (this.idResponsableValidation != null && !this.idResponsableValidation.equals(other.idResponsableValidation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Responsablevalidation[ idResponsableValidation=" + idResponsableValidation + " ]";
    }
    
}
