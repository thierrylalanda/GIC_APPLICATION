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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "societe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Societe.findAll", query = "SELECT s FROM Societe s"),
    @NamedQuery(name = "Societe.findById", query = "SELECT s FROM Societe s WHERE s.id = :id"),
    @NamedQuery(name = "Societe.findByNomSociete", query = "SELECT s FROM Societe s WHERE s.nomSociete = :nomSociete"),
    @NamedQuery(name = "Societe.findByAdresse", query = "SELECT s FROM Societe s WHERE s.adresse = :adresse"),
    @NamedQuery(name = "Societe.findByTelephone", query = "SELECT s FROM Societe s WHERE s.telephone = :telephone"),
    @NamedQuery(name = "Societe.findByCodePostal", query = "SELECT s FROM Societe s WHERE s.codePostal = :codePostal"),
    @NamedQuery(name = "Societe.findByLogo", query = "SELECT s FROM Societe s WHERE s.logo = :logo")})
public class Societe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_societe")
    private String nomSociete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "telephone")
    private String telephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code_postal")
    private String codePostal;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "logo")
    private String logo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "logo_base64")
    private String logoBase64;

    public Societe() {
    }

    public Societe(Integer id) {
        this.id = id;
    }

    public Societe(Integer id, String nomSociete, String adresse, String telephone, String codePostal, String logo) {
        this.id = id;
        this.nomSociete = nomSociete;
        this.adresse = adresse;
        this.telephone = telephone;
        this.codePostal = codePostal;
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Societe)) {
            return false;
        }
        Societe other = (Societe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Societe[ id=" + id + " ]";
    }

    public String getLogoBase64() {
        return logoBase64;
    }

    public void setLogoBase64(String logoBase64) {
        this.logoBase64 = logoBase64;
    }

}
