/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "CentreCout")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentreCout.findAll", query = "SELECT c FROM CentreCout c"),
    @NamedQuery(name = "CentreCout.findByIdCout", query = "SELECT c FROM CentreCout c WHERE c.idCout = :idCout"),
    @NamedQuery(name = "CentreCout.findByLibelle", query = "SELECT c FROM CentreCout c WHERE c.libelle = :libelle")})
public class CentreCout implements Serializable {

    @OneToMany(mappedBy = "centreCout")
    private List<Service> serviceList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cout")
    private Integer idCout;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "libelle")
    private String libelle;

    public CentreCout() {
    }

    public CentreCout(Integer idCout) {
        this.idCout = idCout;
    }

    public CentreCout(Integer idCout, String libelle) {
        this.idCout = idCout;
        this.libelle = libelle;
    }

    public Integer getIdCout() {
        return idCout;
    }

    public void setIdCout(Integer idCout) {
        this.idCout = idCout;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCout != null ? idCout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentreCout)) {
            return false;
        }
        CentreCout other = (CentreCout) object;
        if ((this.idCout == null && other.idCout != null) || (this.idCout != null && !this.idCout.equals(other.idCout))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.CentreCout[ idCout=" + idCout + " ]";
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
    
}
