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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Direction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direction.findAll", query = "SELECT d FROM Direction d"),
    @NamedQuery(name = "Direction.findByNomDirection", query = "SELECT d FROM Direction d WHERE d.nomDirection = :nomDirection")})
public class Direction implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direction")
    private List<StatistiqueArticlesDirection> statistiqueArticlesDirectionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direction")
    private List<StatistiqueCategorieDirection> statistiqueCategorieDirectionList;
    @Size(max = 255)
    @Column(name = "Directeur")
    private String directeur;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direction")
    private Integer idDirection;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "nom_direction")
    private String nomDirection;
    @JoinColumn(name = "region", referencedColumnName = "id_region")
    @ManyToOne(optional = false)
    private Region region;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direction")
    private List<Service> serviceList;

    public Direction() {
    }

    public Direction(Integer idDirection) {
        this.idDirection = idDirection;
    }

    public Direction(Integer idDirection, String nomDirection) {
        this.idDirection = idDirection;
        this.nomDirection = nomDirection;
    }

    public Integer getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(Integer idDirection) {
        this.idDirection = idDirection;
    }

    public String getNomDirection() {
        return nomDirection;
    }

    public void setNomDirection(String nomDirection) {
        this.nomDirection = nomDirection;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @XmlTransient
    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirection != null ? idDirection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direction)) {
            return false;
        }
        Direction other = (Direction) object;
        if ((this.idDirection == null && other.idDirection != null) || (this.idDirection != null && !this.idDirection.equals(other.idDirection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Direction[ idDirection=" + idDirection + " ]";
    }

    public String getDirecteur() {
        return directeur;
    }

    public void setDirecteur(String directeur) {
        this.directeur = directeur;
    }

    @XmlTransient
    public List<StatistiqueArticlesDirection> getStatistiqueArticlesDirectionList() {
        return statistiqueArticlesDirectionList;
    }

    public void setStatistiqueArticlesDirectionList(List<StatistiqueArticlesDirection> statistiqueArticlesDirectionList) {
        this.statistiqueArticlesDirectionList = statistiqueArticlesDirectionList;
    }

    @XmlTransient
    public List<StatistiqueCategorieDirection> getStatistiqueCategorieDirectionList() {
        return statistiqueCategorieDirectionList;
    }

    public void setStatistiqueCategorieDirectionList(List<StatistiqueCategorieDirection> statistiqueCategorieDirectionList) {
        this.statistiqueCategorieDirectionList = statistiqueCategorieDirectionList;
    }
}
