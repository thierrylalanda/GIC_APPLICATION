/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.AffectationmagasinS;
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
public class AffectationmagasinSFacade extends AbstractFacade<AffectationmagasinS> implements AffectationmagasinSFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AffectationmagasinSFacade() {
        super(AffectationmagasinS.class);
    }

    @Override
    public List<AffectationmagasinS> findByIDPersonnel(int idPersonnel) {
        Query query = getEntityManager().createNamedQuery("AffectationmagasinS.findAAffectationByIDPersonnel").setParameter("id", idPersonnel);
        return query.getResultList();
    }

    @Override
    public List<AffectationmagasinS> findAllByIdMS(int idMS) {
        Query query = getEntityManager().createNamedQuery("AffectationmagasinS.findAllByIdMS").setParameter("idmagasin", idMS);
        return query.getResultList();
    }

    @Override
    public AffectationmagasinS findOnByIdPersonnel(int id) {
        Query query = getEntityManager().createNamedQuery("AffectationmagasinS.findAAffectationByIDPersonnel");
        query.setParameter("id", id);
        return (AffectationmagasinS) query.getSingleResult();
    }

}
