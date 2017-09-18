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
@Table(name = "categorie_produit_MS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategorieproduitMS.findAll", query = "SELECT c FROM CategorieproduitMS c"),
    @NamedQuery(name = "CategorieproduitMS.findAl", query = "SELECT DISTINCT c.nomCategorie FROM CategorieproduitMS c"),
    @NamedQuery(name = "CategorieproduitMS.findCategorieByidMS", query = "SELECT c FROM CategorieproduitMS c WHERE c.magasinSecondaire.idMagasin = :idMS"),
    @NamedQuery(name = "CategorieproduitMS.findByIdCategorie", query = "SELECT c FROM CategorieproduitMS c WHERE c.idCategorie = :idCategorie"),
    @NamedQuery(name = "CategorieproduitMS.findByNomCategorie", query = "SELECT c FROM CategorieproduitMS c WHERE c.nomCategorie = :nomCategorie")})
public class CategorieproduitMS implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categorie")
    private Integer idCategorie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_categorie")
    private String nomCategorie;
    @JoinColumn(name = "magasin_secondaire", referencedColumnName = "id_magasin")
    @ManyToOne(optional = false)
    private MagasinSecondaire magasinSecondaire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie" , fetch = FetchType.EAGER)
    private List<StockproduitMS> stockproduitMSList;

    public CategorieproduitMS() {
    }

    public CategorieproduitMS(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public CategorieproduitMS(Integer idCategorie, String nomCategorie) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public MagasinSecondaire getMagasinSecondaire() {
        return magasinSecondaire;
    }

    public void setMagasinSecondaire(MagasinSecondaire magasinSecondaire) {
        this.magasinSecondaire = magasinSecondaire;
    }

    @XmlTransient
    public List<StockproduitMS> getStockproduitMSList() {
        return stockproduitMSList;
    }

    public void setStockproduitMSList(List<StockproduitMS> stockproduitMSList) {
        this.stockproduitMSList = stockproduitMSList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategorie != null ? idCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategorieproduitMS)) {
            return false;
        }
        CategorieproduitMS other = (CategorieproduitMS) object;
        if ((this.idCategorie == null && other.idCategorie != null) || (this.idCategorie != null && !this.idCategorie.equals(other.idCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.CategorieproduitMS[ idCategorie=" + idCategorie + " ]";
    }
    
}
