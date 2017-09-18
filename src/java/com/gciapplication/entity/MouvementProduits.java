/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "mouvement_produits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MouvementProduits.findAll", query = "SELECT m FROM MouvementProduits m"),
    @NamedQuery(name = "MouvementProduits.findByIdMouvement", query = "SELECT m FROM MouvementProduits m WHERE m.idMouvement = :idMouvement"),
    @NamedQuery(name = "MouvementProduits.findByQuantiteInit", query = "SELECT m FROM MouvementProduits m WHERE m.quantiteInit = :quantiteInit"),
    @NamedQuery(name = "MouvementProduits.findByQuantiteApres", query = "SELECT m FROM MouvementProduits m WHERE m.quantiteApres = :quantiteApres"),
    @NamedQuery(name = "MouvementProduits.findByIdMP", query = "SELECT m FROM MouvementProduits m WHERE m.idMagasin = :idMagasin AND m.idTransfert.idTransfert = :idtransfert"),
    @NamedQuery(name = "MouvementProduits.findByOperateur", query = "SELECT m FROM MouvementProduits m WHERE m.operateur = :operateur"),
    @NamedQuery(name = "MouvementProduits.findByDateOperation", query = "SELECT m FROM MouvementProduits m WHERE m.dateOperation = :dateOperation")})
public class MouvementProduits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mouvement")
    private Integer idMouvement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite_init")
    private int quantiteInit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite_apres")
    private int quantiteApres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMagasin")
    private int idMagasin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Operateur")
    private String operateur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_Operation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOperation;
    @JoinColumn(name = "id_transfert", referencedColumnName = "id_transfert")
    @ManyToOne(optional = false)
    private TransfertStock idTransfert;

    public MouvementProduits() {
    }

    public MouvementProduits(Integer idMouvement) {
        this.idMouvement = idMouvement;
    }

    public MouvementProduits(Integer idMouvement, int quantiteInit, int quantiteApres, int idMagasin, String operateur, Date dateOperation) {
        this.idMouvement = idMouvement;
        this.quantiteInit = quantiteInit;
        this.quantiteApres = quantiteApres;
        this.idMagasin = idMagasin;
        this.operateur = operateur;
        this.dateOperation = dateOperation;
    }

    public Integer getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(Integer idMouvement) {
        this.idMouvement = idMouvement;
    }

    public int getQuantiteInit() {
        return quantiteInit;
    }

    public void setQuantiteInit(int quantiteInit) {
        this.quantiteInit = quantiteInit;
    }

    public int getQuantiteApres() {
        return quantiteApres;
    }

    public void setQuantiteApres(int quantiteApres) {
        this.quantiteApres = quantiteApres;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public TransfertStock getIdTransfert() {
        return idTransfert;
    }

    public void setIdTransfert(TransfertStock idTransfert) {
        this.idTransfert = idTransfert;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMouvement != null ? idMouvement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MouvementProduits)) {
            return false;
        }
        MouvementProduits other = (MouvementProduits) object;
        if ((this.idMouvement == null && other.idMouvement != null) || (this.idMouvement != null && !this.idMouvement.equals(other.idMouvement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.MouvementProduits[ idMouvement=" + idMouvement + " ]";
    }
    
}
