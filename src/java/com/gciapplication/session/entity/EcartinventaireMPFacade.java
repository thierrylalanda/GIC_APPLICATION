/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.EcartinventaireMP;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author messi
 */
@Stateless
public class EcartinventaireMPFacade extends AbstractFacade<EcartinventaireMP> implements EcartinventaireMPFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EcartinventaireMPFacade() {
        super(EcartinventaireMP.class);
    }

    @Override
    public List<EcartinventaireMP> findByPeriode(Date d, Date d1, int id) {
        Query query = getEntityManager().createNamedQuery("EcartinventaireMP.findByPeriode");
        query.setParameter("date1", d);
        query.setParameter("date2", d1);
        query.setParameter("idMP", id);
        return query.getResultList();
    }

    @Override
    public List<EcartinventaireMP> findAllById(int id) {
        Query query = getEntityManager().createNamedQuery("EcartinventaireMP.findAllById");
        query.setParameter("idMP", id);
        return query.getResultList();
    }
}
