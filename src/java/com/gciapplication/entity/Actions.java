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
@Table(name = "actions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actions.findAll", query = "SELECT a FROM Actions a"),
    @NamedQuery(name = "Actions.findByIdActions", query = "SELECT a FROM Actions a WHERE a.idActions = :idActions"),
    @NamedQuery(name = "Actions.findByNomAction", query = "SELECT a FROM Actions a WHERE a.nomAction = :nomAction")})
public class Actions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_actions")
    private Integer idActions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_action")
    private String nomAction;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "action")
    private List<Permissions> permissionsList;

    public Actions() {
    }

    public Actions(Integer idActions) {
        this.idActions = idActions;
    }

    public Actions(Integer idActions, String nomAction) {
        this.idActions = idActions;
        this.nomAction = nomAction;
    }

    public Integer getIdActions() {
        return idActions;
    }

    public void setIdActions(Integer idActions) {
        this.idActions = idActions;
    }

    public String getNomAction() {
        return nomAction;
    }

    public void setNomAction(String nomAction) {
        this.nomAction = nomAction;
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
        hash += (idActions != null ? idActions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actions)) {
            return false;
        }
        Actions other = (Actions) object;
        if ((this.idActions == null && other.idActions != null) || (this.idActions != null && !this.idActions.equals(other.idActions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Actions[ idActions=" + idActions + " ]";
    }
    
}
