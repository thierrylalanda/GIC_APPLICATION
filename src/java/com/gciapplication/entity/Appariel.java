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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "appariel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appariel.findAll", query = "SELECT a FROM Appariel a")
    , @NamedQuery(name = "Appariel.findAllApparielPersonnel", query = "SELECT a FROM Appariel a WHERE a.proprietaire.idPersonnel = :idpersonnel")
    , @NamedQuery(name = "Appariel.findByNumeroParck", query = "SELECT a FROM Appariel a WHERE a.numeroParck = :numeroParck")
     , @NamedQuery(name = "Appariel.findByDefautAppareil", query = "SELECT a FROM Appariel a WHERE a.proprietaire.idPersonnel = :personnel AND a.numeroParck LIKE 'AUTCH%'")   
    , @NamedQuery(name = "Appariel.findByFabricant", query = "SELECT a FROM Appariel a WHERE a.fabricant = :fabricant")
    , @NamedQuery(name = "Appariel.findByLieu", query = "SELECT a FROM Appariel a WHERE a.lieu = :lieu")
    , @NamedQuery(name = "Appariel.findByModel", query = "SELECT a FROM Appariel a WHERE a.model = :model")
    , @NamedQuery(name = "Appariel.findByNumeroSerie", query = "SELECT a FROM Appariel a WHERE a.numeroSerie = :numeroSerie")})
public class Appariel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "numero_parck")
    private String numeroParck;
    @Size(max = 255)
    @Column(name = "fabricant")
    private String fabricant;
    @Size(max = 255)
    @Column(name = "lieu")
    private String lieu;
    @Size(max = 255)
    @Column(name = "model")
    private String model;
    @Size(max = 255)
    @Column(name = "numero_serie")
    private String numeroSerie;
    @JoinColumn(name = "proprietaire", referencedColumnName = "id_personnel")
    @ManyToOne
    private Personnel proprietaire;
    @JoinColumn(name = "type_appareil", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TypeAppareil typeAppareil;

    public Appariel() {
    }

    public Appariel(String numeroParck) {
        this.numeroParck = numeroParck;
    }

    public String getNumeroParck() {
        return numeroParck;
    }

    public void setNumeroParck(String numeroParck) {
        this.numeroParck = numeroParck;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Personnel getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Personnel proprietaire) {
        this.proprietaire = proprietaire;
    }

    public TypeAppareil getTypeAppareil() {
        return typeAppareil;
    }

    public void setTypeAppareil(TypeAppareil typeAppareil) {
        this.typeAppareil = typeAppareil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroParck != null ? numeroParck.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appariel)) {
            return false;
        }
        Appariel other = (Appariel) object;
        if ((this.numeroParck == null && other.numeroParck != null) || (this.numeroParck != null && !this.numeroParck.equals(other.numeroParck))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Appariel[ numeroParck=" + numeroParck + " ]";
    }
    
}
