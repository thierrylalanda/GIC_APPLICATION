/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gciapplication.session.entity;

import com.gciapplication.entity.AffectationControleurs;
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
public class AffectationControleursFacade extends AbstractFacade<AffectationControleurs> implements AffectationControleursFacadeLocal {

    @PersistenceContext(unitName = "GCIApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AffectationControleursFacade() {
        super(AffectationControleurs.class);
    }

    @Override
    public List<AffectationControleurs> findByIDPersonnel(int idPersonnel) {
        Query query = getEntityManager().createNamedQuery("AffectationControleurs.findaffectationByIDpersonnel").setParameter("id", idPersonnel);
        return query.getResultList();
    }

    @Override
    public List<AffectationControleurs> findLastInsert() {
        Query query = getEntityManager().createNamedQuery("AffectationControleurs.findLastInsert");
        return query.getResultList();
    }

    @Override
    public List<AffectationControleurs> findByidResponsable(int idRespo) {
        Query query = getEntityManager().createNamedQuery("AffectationControleurs.findByidResponsable");
        query.setParameter("idrespo", idRespo);
        return query.getResultList();
    }

    @Override
    public AffectationControleurs findOnByIdPersonnel(int id) {
        Query query = getEntityManager().createNamedQuery("AffectationControleurs.findaffectationByIDpersonnel");
        query.setParameter("id", id);
        return (AffectationControleurs) query.getSingleResult();
    }

}
