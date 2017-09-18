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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "groupes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupes.findAll", query = "SELECT g FROM Groupes g"),
    @NamedQuery(name = "Groupes.findLastInsert", query = "SELECT g FROM Groupes g WHERE g.idGroupes = SELECT MAX(g.idGroupes) FROM Groupes g"),
    @NamedQuery(name = "Groupes.findByIdGroupes", query = "SELECT g FROM Groupes g WHERE g.idGroupes = :idGroupes"),
    @NamedQuery(name = "Groupes.findByNiveau", query = "SELECT g FROM Groupes g WHERE g.niveau = :niveau"),
    @NamedQuery(name = "Groupes.findByNomGroupeLike", query = "SELECT g FROM Groupes g WHERE g.nomGroupe LIKE '%Defaut%'"),
    @NamedQuery(name = "Groupes.findByNiveauAndRegion", query = "SELECT g FROM Groupes g WHERE g.niveau = :niveau AND g.region = :idregion"),
    @NamedQuery(name = "Groupes.findByNomGroupe", query = "SELECT g FROM Groupes g WHERE g.nomGroupe = :nomGroupe")})
public class Groupes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_groupes")
    private Integer idGroupes;
    @Column(name = "niveau")
    private Integer niveau;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_groupe")
    private String nomGroupe;
    @JoinColumn(name = "region", referencedColumnName = "id_region")
    @ManyToOne
    private Region region;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupes", fetch = FetchType.EAGER)
    private List<Login> loginList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupe", fetch = FetchType.EAGER)
    private List<Permissions> permissionsList;

    public Groupes() {
    }

    public Groupes(Integer idGroupes) {
        this.idGroupes = idGroupes;
    }

    public Groupes(Integer idGroupes, String nomGroupe) {
        this.idGroupes = idGroupes;
        this.nomGroupe = nomGroupe;
    }

    public Integer getIdGroupes() {
        return idGroupes;
    }

    public void setIdGroupes(Integer idGroupes) {
        this.idGroupes = idGroupes;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    @XmlTransient
    public List<Login> getLoginList() {
        return loginList;
    }

    public void setLoginList(List<Login> loginList) {
        this.loginList = loginList;
    }

    @XmlTransient
    public List<Permissions> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(List<Permissions> permissionsList) {
        this.permissionsList = permissionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroupes != null ? idGroupes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupes)) {
            return false;
        }
        Groupes other = (Groupes) object;
        if ((this.idGroupes == null && other.idGroupes != null) || (this.idGroupes != null && !this.idGroupes.equals(other.idGroupes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Groupes[ idGroupes=" + idGroupes + " ]";
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
