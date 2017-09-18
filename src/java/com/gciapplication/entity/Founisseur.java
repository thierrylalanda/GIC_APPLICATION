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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "founisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Founisseur.findAll", query = "SELECT f FROM Founisseur f"),
    @NamedQuery(name = "Founisseur.findByIdFounisseur", query = "SELECT f FROM Founisseur f WHERE f.idFounisseur = :idFounisseur"),
    @NamedQuery(name = "Founisseur.findByNomFounisseur", query = "SELECT f FROM Founisseur f WHERE f.nomFounisseur = :nomFounisseur"),
    @NamedQuery(name = "Founisseur.findByAdresse", query = "SELECT f FROM Founisseur f WHERE f.adresse = :adresse")})
public class Founisseur implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_founisseur")
    private Integer idFounisseur;
    @Basic(optional = false)
    @Size(max = 255)
    @Column(name = "nom_founisseur")
    private String nomFounisseur;
    @Basic(optional = false)
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSA")
    private List<CommandeMP> commandeMPList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFounisseur")
    private List<Bordereau> bordereauList;

    public Founisseur() {
    }

    public Founisseur(Integer idFounisseur) {
        this.idFounisseur = idFounisseur;
    }

    public Founisseur(Integer idFounisseur, String nomFounisseur, String adresse) {
        this.idFounisseur = idFounisseur;
        this.nomFounisseur = nomFounisseur;
        this.adresse = adresse;
    }

    public Integer getIdFounisseur() {
        return idFounisseur;
    }

    public void setIdFounisseur(Integer idFounisseur) {
        this.idFounisseur = idFounisseur;
    }

    public String getNomFounisseur() {
        return nomFounisseur;
    }

    public void setNomFounisseur(String nomFounisseur) {
        this.nomFounisseur = nomFounisseur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @XmlTransient
    public List<CommandeMP> getCommandeMPList() {
        return commandeMPList;
    }

    public void setCommandeMPList(List<CommandeMP> commandeMPList) {
        this.commandeMPList = commandeMPList;
    }

    @XmlTransient
    public List<Bordereau> getBordereauList() {
        return bordereauList;
    }

    public void setBordereauList(List<Bordereau> bordereauList) {
        this.bordereauList = bordereauList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFounisseur != null ? idFounisseur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Founisseur)) {
            return false;
        }
        Founisseur other = (Founisseur) object;
        if ((this.idFounisseur == null && other.idFounisseur != null) || (this.idFounisseur != null && !this.idFounisseur.equals(other.idFounisseur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Founisseur[ idFounisseur=" + idFounisseur + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
