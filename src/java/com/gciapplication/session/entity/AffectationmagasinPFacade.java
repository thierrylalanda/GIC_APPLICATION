/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.AffectationmagasinP;
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
public class AffectationmagasinPFacade extends AbstractFacade<AffectationmagasinP> implements AffectationmagasinPFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AffectationmagasinPFacade() {
        super(AffectationmagasinP.class);
    }

    @Override
    public List<AffectationmagasinP> findByIDPersonnel(int idPersonnel) {
        Query query = getEntityManager().createNamedQuery("AffectationmagasinP.findaffectationByIDpersonnel").setParameter("id", idPersonnel);
        return query.getResultList();
    }

    @Override
    public List<AffectationmagasinP> findAllByIdMS(int idMP) {
        Query query = getEntityManager().createNamedQuery("AffectationmagasinP.findAllByIdMP").setParameter("idmagasin", idMP);
        return query.getResultList();
    }

    @Override
    public AffectationmagasinP findOnByIdPersonnel(int id) {
        Query query = getEntityManager().createNamedQuery("AffectationmagasinP.findaffectationByIDpersonnel");
        query.setParameter("id", id);
        return (AffectationmagasinP) query.getSingleResult();
    }

}
