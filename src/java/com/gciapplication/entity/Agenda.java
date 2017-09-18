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
 * @author Administrateur
 */
@Entity
@Table(name = "agenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a")
        , @NamedQuery(name = "Agenda.findByIdPersonnel", query = "SELECT DISTINCT a FROM Agenda a WHERE a.personnel.idPersonnel = :idPersonnel")
        , @NamedQuery(name = "Agenda.findByIdPersonnelAndDate", query = "SELECT DISTINCT a FROM Agenda a WHERE a.personnel.idPersonnel = :idPersonnel AND a.date = :date")
    , @NamedQuery(name = "Agenda.findById", query = "SELECT a FROM Agenda a WHERE a.id = :id")
    , @NamedQuery(name = "Agenda.findByDate", query = "SELECT a FROM Agenda a WHERE a.date = :date")
    , @NamedQuery(name = "Agenda.findByDateInsertion", query = "SELECT a FROM Agenda a WHERE a.dateInsertion = :dateInsertion")
    , @NamedQuery(name = "Agenda.findByDetails", query = "SELECT a FROM Agenda a WHERE a.details = :details")})
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_insertion")
    @Temporal(TemporalType.DATE)
    private Date dateInsertion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "details")
    private String details;
    @JoinColumn(name = "personnel", referencedColumnName = "id_personnel")
    @ManyToOne(optional = false)
    private Personnel personnel;

    public Agenda() {
    }

    public Agenda(Integer id) {
        this.id = id;
    }

    public Agenda(Integer id, Date date, Date dateInsertion, String details) {
        this.id = id;
        this.date = date;
        this.dateInsertion = dateInsertion;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateInsertion() {
        return dateInsertion;
    }

    public void setDateInsertion(Date dateInsertion) {
        this.dateInsertion = dateInsertion;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
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
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gciapplication.entity.Agenda[ id=" + id + " ]";
    }
    
}
