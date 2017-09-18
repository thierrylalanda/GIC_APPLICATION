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
import javax.persistence.Lob;
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
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "Notification.findByPersonnelAndVue", query = "SELECT DISTINCT n FROM Notification n WHERE n.recepteur = :recepteur  And n.vue = :etat"),
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n WHERE n.recepteur = :idRecepteur"),
    @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id"),
    @NamedQuery(name = "Notification.findByMessage", query = "SELECT n FROM Notification n WHERE n.message = :message"),
    @NamedQuery(name = "Notification.findByExpediteur", query = "SELECT n FROM Notification n WHERE n.expediteur = :expediteur"),
    @NamedQuery(name = "Notification.findByRecepteur", query = "SELECT n FROM Notification n WHERE n.recepteur = :recepteur AND n.vue = :etat"),
    @NamedQuery(name = "Notification.findByVueAndRecepteur", query = "SELECT n FROM Notification n WHERE n.vue = :vue AND n.recepteur = :recepteur"),
    @NamedQuery(name = "Notification.findByVueAndExpediteur", query = "SELECT n FROM Notification n WHERE n.vue = :vue AND n.expediteur = :expediteur")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;
    @Column(name = "expediteur")
    private Integer expediteur;
    @Column(name = "recepteur")
    private Integer recepteur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "vue")
    private Integer vue;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

    public Notification(Integer id, String message, int expediteur, int recepteur, int vue) {
        this.id = id;
        this.message = message;
        this.expediteur = expediteur;
        this.recepteur = recepteur;
        this.vue = vue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Notification[ id=" + id + " ]";
    }

    public Integer getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Integer expediteur) {
        this.expediteur = expediteur;
    }

    public Integer getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(Integer recepteur) {
        this.recepteur = recepteur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getVue() {
        return vue;
    }

    public void setVue(Integer vue) {
        this.vue = vue;
    }

}
