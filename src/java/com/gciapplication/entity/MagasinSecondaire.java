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
@Table(name = "magasin_secondaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MagasinSecondaire.findAll", query = "SELECT m FROM MagasinSecondaire m"),
    @NamedQuery(name = "MagasinSecondaire.findLastInsert", query = "SELECT m FROM MagasinSecondaire m WHERE m.idMagasin = SELECT MAX(m.idMagasin) FROM MagasinSecondaire m"),
    @NamedQuery(name = "MagasinSecondaire.findByNom", query = "SELECT m FROM MagasinSecondaire m WHERE m.nomMagasin = :nom"),
    @NamedQuery(name = "MagasinSecondaire.findBySite", query = "SELECT m FROM MagasinSecondaire m WHERE m.site.idSite = :site"),
    @NamedQuery(name = "MagasinSecondaire.findByIdRegion", query = "SELECT m FROM MagasinSecondaire m WHERE m.site.region.idRegion = :idregion"),
    @NamedQuery(name = "MagasinSecondaire.findByRegion", query = "SELECT m FROM MagasinSecondaire m WHERE m.site.region.nomRegion = :region"),
    @NamedQuery(name = "MagasinSecondaire.findByIdMagasin", query = "SELECT m FROM MagasinSecondaire m WHERE  m.idMagasin = :idMagasin"), // @NamedQuery(name = "MagasinSecondaire.findByDescription", query = "SELECT m FROM MagasinSecondaire m WHERE m.affectationmagasinSList.personnel.service.site.region.nomRegion = :region")
})
public class MagasinSecondaire implements Serializable {

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
    @OneToMany(mappedBy = "magasin")
    private List<Butget> butgetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasin")
    private List<StatistiqueCategorieMagasinMs> statistiqueCategorieMagasinMsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasin")
    private List<StatistiqueArticlesMagasinMs> statistiqueArticlesMagasinMsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMS")
    private List<EcartinventaireMS> ecartinventaireMSList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinSecondaire", fetch = FetchType.EAGER)
    private List<CategorieproduitMS> categorieproduitMSList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMS")
    private List<CommandeMS> commandeMSList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ms")
    private List<TransfertStock> transfertStockList;
    @OneToMany(mappedBy = "magasin")
    private List<OperationConsommateur> operationConsommateurList;
    @OneToMany(mappedBy = "idMS")
    private List<CommandePersonnel> commandePersonnelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "magasinS")
    private List<AffectationmagasinS> affectationmagasinSList;
    @JoinColumn(name = "site", referencedColumnName = "id_site")
    @ManyToOne(optional = false)
    private Site site;

    public MagasinSecondaire() {
    }

    public MagasinSecondaire(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public MagasinSecondaire(Integer idMagasin, String description, String nomMagasin) {
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
    public List<EcartinventaireMS> getEcartinventaireMSList() {
        return ecartinventaireMSList;
    }

    public void setEcartinventaireMSList(List<EcartinventaireMS> ecartinventaireMSList) {
        this.ecartinventaireMSList = ecartinventaireMSList;
    }

    @XmlTransient
    public List<CategorieproduitMS> getCategorieproduitMSList() {
        return categorieproduitMSList;
    }

    public void setCategorieproduitMSList(List<CategorieproduitMS> categorieproduitMSList) {
        this.categorieproduitMSList = categorieproduitMSList;
    }

    @XmlTransient
    public List<CommandeMS> getCommandeMSList() {
        return commandeMSList;
    }

    public void setCommandeMSList(List<CommandeMS> commandeMSList) {
        this.commandeMSList = commandeMSList;
    }

    @XmlTransient
    public List<TransfertStock> getTransfertStockList() {
        return transfertStockList;
    }

    public void setTransfertStockList(List<TransfertStock> transfertStockList) {
        this.transfertStockList = transfertStockList;
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

    @XmlTransient
    public List<AffectationmagasinS> getAffectationmagasinSList() {
        return affectationmagasinSList;
    }

    public void setAffectationmagasinSList(List<AffectationmagasinS> affectationmagasinSList) {
        this.affectationmagasinSList = affectationmagasinSList;
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
        if (!(object instanceof MagasinSecondaire)) {
            return false;
        }
        MagasinSecondaire other = (MagasinSecondaire) object;
        if ((this.idMagasin == null && other.idMagasin != null) || (this.idMagasin != null && !this.idMagasin.equals(other.idMagasin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.MagasinSecondaire[ idMagasin=" + idMagasin + " ]";
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @XmlTransient
    public List<Butget> getButgetList() {
        return butgetList;
    }

    public void setButgetList(List<Butget> butgetList) {
        this.butgetList = butgetList;
    }

    @XmlTransient
    public List<StatistiqueCategorieMagasinMs> getStatistiqueCategorieMagasinMsList() {
        return statistiqueCategorieMagasinMsList;
    }

    public void setStatistiqueCategorieMagasinMsList(List<StatistiqueCategorieMagasinMs> statistiqueCategorieMagasinMsList) {
        this.statistiqueCategorieMagasinMsList = statistiqueCategorieMagasinMsList;
    }

    @XmlTransient
    public List<StatistiqueArticlesMagasinMs> getStatistiqueArticlesMagasinMsList() {
        return statistiqueArticlesMagasinMsList;
    }

    public void setStatistiqueArticlesMagasinMsList(List<StatistiqueArticlesMagasinMs> statistiqueArticlesMagasinMsList) {
        this.statistiqueArticlesMagasinMsList = statistiqueArticlesMagasinMsList;
    }

}
