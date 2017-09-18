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
@Table(name = "Region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r"),
    @NamedQuery(name = "Region.findByNomRegion", query = "SELECT r FROM Region r WHERE r.nomRegion = :nomRegion")})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_region")
    private Integer idRegion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_region")
    private String nomRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<Site> siteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<Direction> directionList;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "region")
    private List<Groupes> groupesList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<StatistiqueArticlesRegion> statistiqueArticlesRegionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<StatistiqueCategorieRegion> statistiqueCategorieRegionList;

    public Region() {
    }

    public Region(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Region(Integer idRegion, String nomRegion) {
        this.idRegion = idRegion;
        this.nomRegion = nomRegion;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    @XmlTransient
    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    @XmlTransient
    public List<Direction> getDirectionList() {
        return directionList;
    }

    public void setDirectionList(List<Direction> directionList) {
        this.directionList = directionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Region[ idRegion=" + idRegion + " ]";
    }

    @XmlTransient
    public List<StatistiqueArticlesRegion> getStatistiqueArticlesRegionList() {
        return statistiqueArticlesRegionList;
    }

    public void setStatistiqueArticlesRegionList(List<StatistiqueArticlesRegion> statistiqueArticlesRegionList) {
        this.statistiqueArticlesRegionList = statistiqueArticlesRegionList;
    }

    @XmlTransient
    public List<StatistiqueCategorieRegion> getStatistiqueCategorieRegionList() {
        return statistiqueCategorieRegionList;
    }

    public void setStatistiqueCategorieRegionList(List<StatistiqueCategorieRegion> statistiqueCategorieRegionList) {
        this.statistiqueCategorieRegionList = statistiqueCategorieRegionList;
    }

    @XmlTransient
    public List<Groupes> getGroupesList() {
        return groupesList;
    }

    public void setGroupesList(List<Groupes> groupesList) {
        this.groupesList = groupesList;
    }
}
