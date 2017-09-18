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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "Site")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Site.findAll", query = "SELECT s FROM Site s"),
    @NamedQuery(name = "Site.findByNomSite", query = "SELECT s FROM Site s WHERE s.nomSite = :nomSite")})
public class Site implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<StatistiqueCategorieSite> statistiqueCategorieSiteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<StatistiqueArticlesSite> statistiqueArticlesSiteList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_site")
    private Integer idSite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_site")
    private String nomSite;
    @JoinColumn(name = "region", referencedColumnName = "id_region")
    @ManyToOne(optional = false)
    private Region region;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<Service> serviceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<MagasinSecondaire> magasinSecondaireList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "site")
    private List<MagasinPrincipal> magasinPrincipalList;

    public Site() {
    }

    public Site(Integer idSite) {
        this.idSite = idSite;
    }

    public Site(Integer idSite, String nomSite) {
        this.idSite = idSite;
        this.nomSite = nomSite;
    }

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }

    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
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
        hash += (idSite != null ? idSite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Site)) {
            return false;
        }
        Site other = (Site) object;
        if ((this.idSite == null && other.idSite != null) || (this.idSite != null && !this.idSite.equals(other.idSite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Site[ idSite=" + idSite + " ]";
    }

    @XmlTransient
    public List<MagasinSecondaire> getMagasinSecondaireList() {
        return magasinSecondaireList;
    }

    public void setMagasinSecondaireList(List<MagasinSecondaire> magasinSecondaireList) {
        this.magasinSecondaireList = magasinSecondaireList;
    }

    @XmlTransient
    public List<MagasinPrincipal> getMagasinPrincipalList() {
        return magasinPrincipalList;
    }

    public void setMagasinPrincipalList(List<MagasinPrincipal> magasinPrincipalList) {
        this.magasinPrincipalList = magasinPrincipalList;
    }
    @XmlTransient
    public List<StatistiqueCategorieSite> getStatistiqueCategorieSiteList() {
        return statistiqueCategorieSiteList;
    }

    public void setStatistiqueCategorieSiteList(List<StatistiqueCategorieSite> statistiqueCategorieSiteList) {
        this.statistiqueCategorieSiteList = statistiqueCategorieSiteList;
    }

    @XmlTransient
    public List<StatistiqueArticlesSite> getStatistiqueArticlesSiteList() {
        return statistiqueArticlesSiteList;
    }

    public void setStatistiqueArticlesSiteList(List<StatistiqueArticlesSite> statistiqueArticlesSiteList) {
        this.statistiqueArticlesSiteList = statistiqueArticlesSiteList;
    }

}
