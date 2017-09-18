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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "frabriquant_appareil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrabriquantAppareil.findAll", query = "SELECT f FROM FrabriquantAppareil f"),
    @NamedQuery(name = "FrabriquantAppareil.findByIdFabriquant", query = "SELECT f FROM FrabriquantAppareil f WHERE f.idFabriquant = :idFabriquant"),
    @NamedQuery(name = "FrabriquantAppareil.findByNomFabriquant", query = "SELECT f FROM FrabriquantAppareil f WHERE f.nomFabriquant = :nomFabriquant")})
public class FrabriquantAppareil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fabriquant")
    private Integer idFabriquant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_fabriquant")
    private String nomFabriquant;

    public FrabriquantAppareil() {
    }

    public FrabriquantAppareil(Integer idFabriquant) {
        this.idFabriquant = idFabriquant;
    }

    public FrabriquantAppareil(Integer idFabriquant, String nomFabriquant) {
        this.idFabriquant = idFabriquant;
        this.nomFabriquant = nomFabriquant;
    }

    public Integer getIdFabriquant() {
        return idFabriquant;
    }

    public void setIdFabriquant(Integer idFabriquant) {
        this.idFabriquant = idFabriquant;
    }

    public String getNomFabriquant() {
        return nomFabriquant;
    }

    public void setNomFabriquant(String nomFabriquant) {
        this.nomFabriquant = nomFabriquant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFabriquant != null ? idFabriquant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrabriquantAppareil)) {
            return false;
        }
        FrabriquantAppareil other = (FrabriquantAppareil) object;
        if ((this.idFabriquant == null && other.idFabriquant != null) || (this.idFabriquant != null && !this.idFabriquant.equals(other.idFabriquant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.FrabriquantAppareil[ idFabriquant=" + idFabriquant + " ]";
    }
    
}
