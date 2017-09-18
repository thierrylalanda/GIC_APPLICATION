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
@Table(name = "magasin_principal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MagasinPrincipal.findAll", query = "SELECT m FROM MagasinPrincipal m"),
    @NamedQuery(name = "MagasinPrincipal.findByIdMagasin", query = "SELECT m FROM MagasinPrincipal m WHERE m.idMagasin = :idMagasin"),
    @NamedQuery(name = "MagasinPrincipal.findByLastIdMagasin", query = "SELECT m FROM MagasinPrincipal m WHERE m.idMagasin = SELECT MAX(m.idMagasin) FROM MagasinPrincipal m"),
    @NamedQuery(name = "MagasinPrincipal.findByRegion", query = "SELECT m FROM MagasinPrincipal m WHERE m.site.region.nomRegion = :region"),
    @NamedQuery(name = "MagasinPrincipal.findByDescription", query = "SELECT m FROM MagasinPrincipal m WHERE m.description = :description"),
    @NamedQuery(name = "MagasinPrincipal.findByNomMagasin", query = "SELECT m FROM MagasinPrincipal m WHERE m.nomMagasin = :nomMagasin")})
public class MagasinPrincipal implements Serializable {

    
    

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_magasin")
    private Integer idMagasin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_magasin")
    private String nomMagasin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasin")
    private List<StatistiqueArticlesMagasinMp> statistiqueArticlesMagasinMpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasin")
    private List<StatistiqueCategoriMagasinMp> statistiqueCategoriMagasinMpList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMP")
    private List<EcartinventaireMP> ecartinventaireMPList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinPrincipal")
    private List<CategorieproduitMP> categorieproduitMPList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMP")
    private List<CommandeMS> commandeMSList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMP")
    private List<CommandeMP> commandeMPList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMP")
    private List<OrdreControleur> ordreControleurList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasin")
    private List<Bordereau> bordereauList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mp")
    private List<TransfertStock> transfertStockList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinP")
    private List<AffectationmagasinP> affectationmagasinPList;
    @JoinColumn(name = "site", referencedColumnName = "id_site")
    @ManyToOne(optional = false)
    private Site site;
    @OneToMany(mappedBy = "magasinP")
    private List<OperationConsommateur> operationConsommateurList;
    @OneToMany(mappedBy = "idMP")
    private List<CommandePersonnel> commandePersonnelList;
    
    public MagasinPrincipal() {
    }

    public MagasinPrincipal(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public MagasinPrincipal(Integer idMagasin, String description, String nomMagasin) {
        this.idMagasin = idMagasin;
        this.description = description;
        this.nomMagasin = nomMagasin;
    }

    public Integer getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    @XmlTransient
    public List<EcartinventaireMP> getEcartinventaireMPList() {
        return ecartinventaireMPList;
    }

    public void setEcartinventaireMPList(List<EcartinventaireMP> ecartinventaireMPList) {
        this.ecartinventaireMPList = ecartinventaireMPList;
    }

    @XmlTransient
    public List<CategorieproduitMP> getCategorieproduitMPList() {
        return categorieproduitMPList;
    }

    public void setCategorieproduitMPList(List<CategorieproduitMP> categorieproduitMPList) {
        this.categorieproduitMPList = categorieproduitMPList;
    }

    @XmlTransient
    public List<CommandeMS> getCommandeMSList() {
        return commandeMSList;
    }

    public void setCommandeMSList(List<CommandeMS> commandeMSList) {
        this.commandeMSList = commandeMSList;
    }

    @XmlTransient
    public List<CommandeMP> getCommandeMPList() {
        return commandeMPList;
    }

    public void setCommandeMPList(List<CommandeMP> commandeMPList) {
        this.commandeMPList = commandeMPList;
    }

    @XmlTransient
    public List<OrdreControleur> getOrdreControleurList() {
        return ordreControleurList;
    }

    public void setOrdreControleurList(List<OrdreControleur> ordreControleurList) {
        this.ordreControleurList = ordreControleurList;
    }

    @XmlTransient
    public List<Bordereau> getBordereauList() {
        return bordereauList;
    }

    public void setBordereauList(List<Bordereau> bordereauList) {
        this.bordereauList = bordereauList;
    }

    @XmlTransient
    public List<TransfertStock> getTransfertStockList() {
        return transfertStockList;
    }

    public void setTransfertStockList(List<TransfertStock> transfertStockList) {
        this.transfertStockList = transfertStockList;
    }

    @XmlTransient
    public List<AffectationmagasinP> getAffectationmagasinPList() {
        return affectationmagasinPList;
    }

    public void setAffectationmagasinPList(List<AffectationmagasinP> affectationmagasinPList) {
        this.affectationmagasinPList = affectationmagasinPList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMagasin != null ? idMagasin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MagasinPrincipal)) {
            return false;
        }
        MagasinPrincipal other = (MagasinPrincipal) object;
        if ((this.idMagasin == null && other.idMagasin != null) || (this.idMagasin != null && !this.idMagasin.equals(other.idMagasin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.MagasinPrincipal[ idMagasin=" + idMagasin + " ]";
    }

    @XmlTransient
    public List<OperationConsommateur> getOperationConsommateurList() {
        return operationConsommateurList;
    }

    public void setOperationConsommateurList(List<OperationConsommateur> operationConsommateurList) {
        this.operationConsommateurList = operationConsommateurList;
    }

    @XmlTransient
    public List<CommandePersonnel> getCommandePersonnelList() {
        return commandePersonnelList;
    }

    public void setCommandePersonnelList(List<CommandePersonnel> commandePersonnelList) {
        this.commandePersonnelList = commandePersonnelList;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @XmlTransient
    public List<StatistiqueArticlesMagasinMp> getStatistiqueArticlesMagasinMpList() {
        return statistiqueArticlesMagasinMpList;
    }

    public void setStatistiqueArticlesMagasinMpList(List<StatistiqueArticlesMagasinMp> statistiqueArticlesMagasinMpList) {
        this.statistiqueArticlesMagasinMpList = statistiqueArticlesMagasinMpList;
    }

    @XmlTransient
    public List<StatistiqueCategoriMagasinMp> getStatistiqueCategoriMagasinMpList() {
        return statistiqueCategoriMagasinMpList;
    }

    public void setStatistiqueCategoriMagasinMpList(List<StatistiqueCategoriMagasinMp> statistiqueCategoriMagasinMpList) {
        this.statistiqueCategoriMagasinMpList = statistiqueCategoriMagasinMpList;
    }

    
}
