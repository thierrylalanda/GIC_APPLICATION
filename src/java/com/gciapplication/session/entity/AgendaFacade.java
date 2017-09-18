/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.Agenda;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
@Stateless
public class AgendaFacade extends AbstractFacade<Agenda> implements AgendaFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Agenda> findByIdPersonnel(int IdPersonnel) {
        Query query = getEntityManager().createNamedQuery("Agenda.findByIdPersonnel");
        query.setParameter("idPersonnel", IdPersonnel);

        return query.getResultList();
    }

    @Override
    public List<Agenda> findByIdPersonnelAndDate(int IdPersonnel, Date date) {
    Query query = getEntityManager().createNamedQuery("Agenda.findByIdPersonnelAndDate");
        query.setParameter("idPersonnel", IdPersonnel);
        query.setParameter("date", date);

        return query.getResultList();     
    }
    

    public AgendaFacade() {
        super(Agenda.class);
    }
    
}
