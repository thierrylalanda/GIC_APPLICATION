/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author messi
 */
@Entity
@Table(name = "permissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p"),
     @NamedQuery(name = "Permissions.DeleteByIdGroupe", query = "DELETE FROM Permissions p WHERE p.groupe.idGroupes = :idGroupe"),
    @NamedQuery(name = "Permissions.findAllByIdGroupe", query = "SELECT p FROM Permissions p WHERE p.groupe.idGroupes = :idgroupe"),
    @NamedQuery(name = "Permissions.findByIdPermission", query = "SELECT p FROM Permissions p WHERE p.idPermission = :idPermission")})
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_permission")
    private Integer idPermission;
    @JoinColumn(name = "groupe", referencedColumnName = "id_groupes")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Groupes groupe;
    @JoinColumn(name = "page", referencedColumnName = "id_page")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Page page;
    @JoinColumn(name = "action", referencedColumnName = "id_actions")
    @ManyToOne(optional = false)
    private Actions action;

    public Permissions() {
    }

    public Permissions(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public Integer getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public Groupes getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupes groupe) {
        this.groupe = groupe;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermission != null ? idPermission.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissions)) {
            return false;
        }
        Permissions other = (Permissions) object;
        if ((this.idPermission == null && other.idPermission != null) || (this.idPermission != null && !this.idPermission.equals(other.idPermission))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Permissions[ idPermission=" + idPermission + " ]";
    }
    
}
