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
@Table(name = "model_Appariel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModelAppariel.findAll", query = "SELECT m FROM ModelAppariel m"),
    @NamedQuery(name = "ModelAppariel.findByIdModel", query = "SELECT m FROM ModelAppariel m WHERE m.idModel = :idModel"),
    @NamedQuery(name = "ModelAppariel.findByNomModel", query = "SELECT m FROM ModelAppariel m WHERE m.nomModel = :nomModel")})
public class ModelAppariel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_model")
    private Integer idModel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_model")
    private String nomModel;

    public ModelAppariel() {
    }

    public ModelAppariel(Integer idModel) {
        this.idModel = idModel;
    }

    public ModelAppariel(Integer idModel, String nomModel) {
        this.idModel = idModel;
        this.nomModel = nomModel;
    }

    public Integer getIdModel() {
        return idModel;
    }

    public void setIdModel(Integer idModel) {
        this.idModel = idModel;
    }

    public String getNomModel() {
        return nomModel;
    }

    public void setNomModel(String nomModel) {
        this.nomModel = nomModel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModel != null ? idModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelAppariel)) {
            return false;
        }
        ModelAppariel other = (ModelAppariel) object;
        if ((this.idModel == null && other.idModel != null) || (this.idModel != null && !this.idModel.equals(other.idModel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.ModelAppariel[ idModel=" + idModel + " ]";
    }
    
}
